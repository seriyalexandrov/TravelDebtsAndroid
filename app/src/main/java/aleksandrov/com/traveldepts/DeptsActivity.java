package aleksandrov.com.traveldepts;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aleksandrov.com.traveldepts.dao.DbHelper;
import aleksandrov.com.traveldepts.dao.Queries;
import aleksandrov.com.traveldepts.model.Dept;

public class DeptsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depts);

        showDeptsList();

        final Button addDeptButton = (Button) findViewById(R.id.addDebtButton);
        addDeptButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addDebtButton : {
                addNewDept(new Dept("deptor", "creditor", 4.5, "EUR", "comment"));
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.travellers : {

                Intent travellerIntent = new Intent(this, TravellersActivity.class);
                startActivity(travellerIntent);
                break;
            }
            case R.id.balance : {

                Intent balanceIntent = new Intent(this, BalanceActivity.class);
                startActivity(balanceIntent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNewDept(Dept dept) {

        final DbHelper dbHelper = new DbHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("deptor", dept.deptorName);
        cv.put("creditor", dept.creditorName);
        cv.put("summ", dept.summ);
        cv.put("currency", dept.currency);
        cv.put("commnet", dept.comment);
        db.insert(Constants.DEPTS_TABLE, null, cv);
        db.close();
        dbHelper.close();
    }

    private List<Dept> getDepts() {

        List<Dept> depts = new ArrayList<>();
        final DbHelper dbHelper = new DbHelper(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery(Queries.SELECT_ALL_DEPTS_QUERY, null);
        if (c == null) return Collections.EMPTY_LIST;
        if (c.moveToFirst()) {
            do {
               String deptor = c.getString(0);

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        dbHelper.close();
        return depts;
    }

    private void showDeptsList() {
        LinearLayout deptsLayout = (LinearLayout)findViewById(R.id.deptsList);
        LayoutInflater inflater = getLayoutInflater();
        List<Dept> depts = getDepts();

        for(Dept dept : depts) {
            View deptView = inflater.inflate(R.layout.dept, deptsLayout, false);

            TextView deptName = (TextView) deptView.findViewById(R.id.deptComment);
            deptName.setText(dept.comment);

            TextView deptSumm = (TextView) deptView.findViewById(R.id.deptSumm);
            deptSumm.setText(String.valueOf(dept.summ));

            TextView deptCurr = (TextView) deptView.findViewById(R.id.deptCurr);
            deptCurr.setText(dept.currency);

            deptsLayout.addView(deptView);
        }
    }

    private void saveText(String text) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString("saved", text);
        ed.commit();
    }

    private String loadText() {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        return sPref.getString("saved", "");
    }


}
