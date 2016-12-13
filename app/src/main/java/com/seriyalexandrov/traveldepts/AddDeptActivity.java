package com.seriyalexandrov.traveldepts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddDeptActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept);

        loadTravellerSpinnerContent();

        final Button addDeptButton = (Button) findViewById(R.id.addDebtButton);
//        addDeptButton.setOnClickListener(this);
    }

    private void loadTravellerSpinnerContent() {

        Spinner spinner = (Spinner) findViewById(R.id.travellersSpinner);
        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

    }
}

