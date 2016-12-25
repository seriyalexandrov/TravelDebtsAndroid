package com.seriyalexandrov.traveldepts;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seriyalexandrov.traveldepts.balance.DebtsApi;
import com.seriyalexandrov.traveldepts.balance.DebtsGraph;
import com.seriyalexandrov.traveldepts.balance.DeptsResultEntry;
import com.seriyalexandrov.traveldepts.balance.Utils;
import com.seriyalexandrov.traveldepts.balance.Vertex;
import com.seriyalexandrov.traveldepts.dao.DBUtils;
import com.seriyalexandrov.traveldepts.dao.DbHelper;
import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.ArrayList;
import java.util.List;

public class BalanceActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        DbHelper dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();

        setContentView(R.layout.activity_balance);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.balance_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.depts : {

                Intent deptsIntent = new Intent(BalanceActivity.this, DeptsActivity.class);
                deptsIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(deptsIntent);
                break;
            }
            case R.id.travellers : {

                Intent travellersIntent = new Intent(BalanceActivity.this, TravellersActivity.class);
                travellersIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(travellersIntent);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        refreshBalance();
    }

    private void refreshBalance() {
        final LinearLayout balanceLayout = (LinearLayout)findViewById(R.id.balanceList);
        balanceLayout.removeAllViews();

        final LayoutInflater inflater = getLayoutInflater();
        List<DeptsResultEntry> balanceDepts = calculateBalanceDepts();

        for(DeptsResultEntry dept : balanceDepts) {
            View deptView = inflater.inflate(R.layout.balance_dept, balanceLayout, false);

            TextView deptor = (TextView) deptView.findViewById(R.id.deptor);
            deptor.setText(dept.debtor.name);

            TextView creditor = (TextView) deptView.findViewById(R.id.creditor);
            creditor.setText(dept.creditor.name);

            TextView deptSumm = (TextView) deptView.findViewById(R.id.deptSumm);
            deptSumm.setText(String.valueOf(dept.debt));

            TextView deptCurr = (TextView) deptView.findViewById(R.id.deptCurr);
            deptCurr.setText("EUR");

            balanceLayout.addView(deptView);
        }
    }

    private List<DeptsResultEntry> calculateBalanceDepts() {

        List<Dept> deptsList = DBUtils.getDepts(db);
        DebtsGraph graph = new DebtsGraph();

        for(Dept dept : deptsList) {
            DebtsApi.debtOneToOne(graph, dept.creditorName, dept.deptorName, Double.parseDouble(dept.summ));
        }

        Utils.simplifyGraph(graph);

        Utils.calculateVertexBalance(graph);

        return Utils.calculateDebtsOnNormallizedGraph(graph);
    }
}
