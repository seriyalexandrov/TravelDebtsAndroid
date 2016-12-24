package com.seriyalexandrov.traveldepts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.Collections;
import java.util.List;

public class BalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
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

                Intent deptsIntent = new Intent(this, DeptsActivity.class);
                startActivity(deptsIntent);
                break;
            }
            case R.id.travellers : {

                Intent travellersIntent = new Intent(this, TravellersActivity.class);
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
        List<Dept> balanceDepts = calculateBalanceDepts();

        for(Dept dept : balanceDepts) {
            View deptView = inflater.inflate(R.layout.balance_dept, balanceLayout, false);

            TextView deptor = (TextView) deptView.findViewById(R.id.deptor);
            deptor.setText(dept.deptorName);

            TextView creditor = (TextView) deptView.findViewById(R.id.creditor);
            creditor.setText(dept.creditorName);

            TextView deptSumm = (TextView) deptView.findViewById(R.id.deptSumm);
            deptSumm.setText(String.valueOf(dept.summ));

            TextView deptCurr = (TextView) deptView.findViewById(R.id.deptCurr);
            deptCurr.setText(dept.currency);

            balanceLayout.addView(deptView);
        }
    }

    private List<Dept> calculateBalanceDepts() {
        return Collections.EMPTY_LIST;
    }
}
