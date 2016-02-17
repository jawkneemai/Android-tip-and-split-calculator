package itp341.mai.johnathan.a4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextBillAmount;
    TextView textViewTipPercent;
    SeekBar seekBarTipPercent;
    TextView textViewTipTotal;
    TextView textViewBillTotal;
    Spinner spinnerSplitAmount;
    TextView textViewPersonAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBillAmount = (EditText) findViewById(R.id.editTextBillAmount);
        textViewTipPercent = (TextView) findViewById(R.id.textViewTipPercent);
        seekBarTipPercent = (SeekBar) findViewById(R.id.seekBarTipPercent);
        textViewTipTotal = (TextView) findViewById(R.id.textViewTipTotal);
        textViewBillTotal = (TextView) findViewById(R.id.textViewBillTotal);
        spinnerSplitAmount = (Spinner) findViewById(R.id.spinnerSplitAmount);
        textViewPersonAmount = (TextView) findViewById(R.id.textViewPersonAmount);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
