package aleksandrov.com.traveldepts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DeptsActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dept);

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
