package aleksandrov.com.traveldepts;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import static android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NewDept extends AppCompatActivity implements View.OnClickListener{

    private Button addDeptButton;
    private LinearLayout deptsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addDeptButton = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        addDeptButton.setText("+");
        addDeptButton.setLayoutParams(params);

        deptsLinearLayout = new LinearLayout(this);
        deptsLinearLayout.setOrientation(LinearLayout.VERTICAL);
        deptsLinearLayout.setLayoutParams(
                new LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
        deptsLinearLayout.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

        deptsLinearLayout.addView(addDeptButton);
        setContentView(deptsLinearLayout);

        addDeptButton.setOnClickListener(this);
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


                break;
            }
            case R.id.depts : {

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
