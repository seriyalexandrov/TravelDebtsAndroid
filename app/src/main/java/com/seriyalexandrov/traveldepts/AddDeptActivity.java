package com.seriyalexandrov.traveldepts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.seriyalexandrov.traveldepts.dao.DbHelper;
import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.UUID;

public class AddDeptActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner deptTypeSpinner;
    private Spinner travellersSpinner;
    private DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept);

        loadTravellerSpinnerContent();
        loadDeptorsSpinnerContert();

        dbHelper = new DbHelper(this);

        final Button addDeptButton = (Button) findViewById(R.id.addDebtDeptButton);
        addDeptButton.setOnClickListener(this);

        final Button cancelDeptButton = (Button) findViewById(R.id.cancelDebtDeptButton);
        cancelDeptButton.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {

        ((EditText) findViewById(R.id.deptDeptSumm)).setText("");
        ((EditText) findViewById(R.id.deptDeptComment)).setText("");
        super.onPostResume();

    }

    private void loadTravellerSpinnerContent() {

        travellersSpinner = (Spinner) findViewById(R.id.travellersDeptSpinner);
        String[] items = Constants.travellers;
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, items);
        travellersSpinner.setAdapter(adapter);
    }

    private void loadDeptorsSpinnerContert() {
        deptTypeSpinner = (Spinner) findViewById(R.id.deptTypeDeptSpinner);
        String[] items = Constants.travellers;
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, items);
        deptTypeSpinner.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDebtDeptButton : {
                storeNewDept();
                Intent i = new Intent(AddDeptActivity.this, DeptsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                break;
            }
            case R.id.cancelDebtDeptButton : {
                Intent i = new Intent(AddDeptActivity.this, DeptsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                break;
            }
        }
    }

    private void storeNewDept() {
        String id = UUID.randomUUID().toString();
        String creditor = travellersSpinner.getSelectedItem().toString();
        String deptor = deptTypeSpinner.getSelectedItem().toString();
        String summ = ((EditText) findViewById(R.id.deptDeptSumm)).getText().toString();
        String comment = ((EditText) findViewById(R.id.deptDeptComment)).getText().toString();
        Dept dept = new Dept(id, deptor, creditor, summ, "EUR", comment);
        Log.i(Constants.LOG_TAG, id);
        dbHelper.addNewDept(dept);
    }
}

