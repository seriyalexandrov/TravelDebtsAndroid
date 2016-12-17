package com.seriyalexandrov.traveldepts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class AddDeptActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept);

        loadTravellerSpinnerContent();
        loadDeptorsSpinnerContert();

        final Button addDeptButton = (Button) findViewById(R.id.addDebtDeptButton);
        addDeptButton.setOnClickListener(this);
    }

    private void loadTravellerSpinnerContent() {

        Spinner travellersSpinner = (Spinner) findViewById(R.id.travellersDeptSpinner);
        String[] items = new String[] { "Ser", "Ann", "Tag", "Kolian" };
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, items);
        travellersSpinner.setAdapter(adapter);
    }

    private void loadDeptorsSpinnerContert() {
        Spinner deptType = (Spinner) findViewById(R.id.deptTypeDeptSpinner);
        String[] items = new String[] {"All", "Ser", "Ann", "Tag", "Kolian"};
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, items);
        deptType.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDebtDeptButton : {
                Intent i = new Intent(AddDeptActivity.this, DeptsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                break;
            }
        }
    }
}

