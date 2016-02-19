package itp341.mai.johnathan.a4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Widget Variables
    EditText mEditTextBillAmount;
    TextView mTextViewTipPercent;
    SeekBar mSeekBarTipPercent;
    TextView mTextViewTipTotal;
    TextView mTextViewBillTotal;
    Spinner mSpinnerSplitAmount;
    TextView mTextViewPersonAmount;
    TextView mTextViewPersonAmountDescriptor;

    // Instance Variables
    double currentBillAmount = 0.0;
    double currentTipAmount = 15.0;
    double currentPersonAmount = 0.0;
    double currentBillIncludeTip = 0.0;
    int splitBy = 1;
    final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextBillAmount = (EditText) findViewById(R.id.editTextBillAmount);
        mTextViewTipPercent = (TextView) findViewById(R.id.textViewTipPercent);
        mSeekBarTipPercent = (SeekBar) findViewById(R.id.seekBarTipPercent);
        mTextViewTipTotal = (TextView) findViewById(R.id.textViewTipTotal);
        mTextViewBillTotal = (TextView) findViewById(R.id.textViewBillTotal);
        mSpinnerSplitAmount = (Spinner) findViewById(R.id.spinnerSplitAmount);
        mTextViewPersonAmountDescriptor = (TextView) findViewById(R.id.textviewPersonAmountDescriptor);
        mTextViewPersonAmount = (TextView) findViewById(R.id.textViewPersonAmount);

        String spinnerChoices[] = getResources().getStringArray(R.array.per_person_options);
        mTextViewPersonAmount.setVisibility(View.GONE);
        mTextViewPersonAmountDescriptor.setVisibility(View.GONE);

        // Update bill amount after every time the EditText is touched
        mEditTextBillAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((mEditTextBillAmount.getText()) == null || mEditTextBillAmount.getText().toString().isEmpty()) {
                    currentBillAmount = 0.0;
                    updateInfo();
                } else {
                    currentBillAmount = Double.parseDouble(mEditTextBillAmount.getText().toString());
                    updateInfo();
                }
                return false;
            }
        });

        // Seekbar Listener
        mSeekBarTipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentTipAmount = (double)progress;
                mTextViewTipPercent.setText(String.valueOf(progress));
                updateInfo();
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        mSpinnerSplitAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mTextViewPersonAmount.setVisibility(View.GONE);
                        mTextViewPersonAmountDescriptor.setVisibility(View.GONE);
                        break;
                    case 1:
                        splitBy = 2;
                        updateInfo();
                        mTextViewPersonAmount.setVisibility(View.VISIBLE);
                        mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        splitBy = 3;
                        updateInfo();
                        mTextViewPersonAmount.setVisibility(View.VISIBLE);
                        mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        splitBy = 4;
                        updateInfo();
                        mTextViewPersonAmount.setVisibility(View.VISIBLE);
                        mTextViewPersonAmountDescriptor.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    // Function to update all areas that are update-able
    private void updateInfo() {
        currentBillIncludeTip = currentBillAmount + ( currentTipAmount / 100 ) * currentBillAmount;
        mTextViewTipTotal.setText(String.valueOf(df.format(( currentTipAmount / 100 ) * currentBillAmount )));
        mTextViewBillTotal.setText(String.valueOf(df.format(currentBillIncludeTip)));
        mTextViewPersonAmount.setText(String.valueOf(df.format( currentBillIncludeTip / splitBy )));
    }
}
