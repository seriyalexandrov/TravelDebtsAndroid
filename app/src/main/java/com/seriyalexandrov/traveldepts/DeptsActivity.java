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

import com.seriyalexandrov.traveldepts.dao.DBUtils;
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
                Intent addDeptIntent = new Intent(DeptsActivity.this, AddDeptActivity.class);
                addDeptIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

                Intent travellerIntent = new Intent(DeptsActivity.this, TravellersActivity.class);
                travellerIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(travellerIntent);
                break;
            }
            case R.id.balance : {

                Intent balanceIntent = new Intent(DeptsActivity.this, BalanceActivity.class);
                balanceIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
        List<Dept> depts = DBUtils.getDepts(db);

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
                    DBUtils.removeDeptById(db, id);
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
}
