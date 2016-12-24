package com.seriyalexandrov.traveldepts;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seriyalexandrov.traveldepts.dao.DbHelper;
import com.seriyalexandrov.traveldepts.dao.Queries;
import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeptsActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depts);

        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();

        final Button addDeptButton = (Button) findViewById(R.id.addDebtButton);
        addDeptButton.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshDeptsList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addDebtButton : {
                Intent addDeptIntent = new Intent(this, AddDeptActivity.class);
                startActivity(addDeptIntent);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.depts_menu, menu);
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

    private void refreshDeptsList() {
        final LinearLayout deptsLayout = (LinearLayout)findViewById(R.id.deptsList);
        deptsLayout.removeAllViews();

        final LayoutInflater inflater = getLayoutInflater();
        List<Dept> depts = getDepts();

        for(Dept dept : depts) {
            View deptView = inflater.inflate(R.layout.dept, deptsLayout, false);

            TextView deptor = (TextView) deptView.findViewById(R.id.deptor);
            deptor.setText(dept.deptorName);

            TextView creditor = (TextView) deptView.findViewById(R.id.creditor);
            creditor.setText(dept.creditorName);

            TextView deptName = (TextView) deptView.findViewById(R.id.deptComment);
            deptName.setText(dept.comment);

            TextView deptSumm = (TextView) deptView.findViewById(R.id.deptSumm);
            deptSumm.setText(String.valueOf(dept.summ));

            TextView deptCurr = (TextView) deptView.findViewById(R.id.deptCurr);
            deptCurr.setText(dept.currency);

            final String id = dept.id;
            ImageButton button = (ImageButton) deptView.findViewById(R.id.removeDeptButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeDeptById(id);
                    refreshDeptsList();
                }
            });

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

    private List<Dept> getDepts() {

        List<Dept> depts = new ArrayList<>();

        Cursor c = db.rawQuery(Queries.SELECT_ALL_DEPTS_QUERY, null);
        if (c == null) return Collections.EMPTY_LIST;
        if (c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String deptor = c.getString(1);
                String creditor = c.getString(2);
                String summ = c.getString(3);
                String currency = c.getString(4);
                String comment = c.getString(5);
                depts.add(new Dept(id, deptor, creditor, summ, currency, comment));
            } while (c.moveToNext());
        }
        c.close();
        return depts;
    }

    private void removeDeptById(String id) {
        Log.i(Constants.LOG_TAG, "id = " + id);
        String params[] = {id};
        db.delete(Constants.DEPTS_TABLE, Queries.DELETE_DEPT_BY_ID_CLAUSE, params);
    }
}
