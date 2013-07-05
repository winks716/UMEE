package com.example.umee;

import java.text.DecimalFormat;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_QUIT = Menu.FIRST + 1;
	private Button button_calculate;
	private EditText input_height;
	private EditText input_weight;
	private TextView text_result;
	private TextView text_advice;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }
    
    private void findViews(){
    	button_calculate = (Button)findViewById(R.id.mainActivity_buttonCalculate);
		input_height = (EditText)findViewById(R.id.mainActivity_inputHeight);
		input_weight = (EditText)findViewById(R.id.mainActivity_inputWeight);
		text_result = (TextView)findViewById(R.id.mainActivity_textResult);
		text_advice = (TextView)findViewById(R.id.mainActivity_textAdvice);
    }
    
    private void setListeners(){
        button_calculate.setOnClickListener(calculateBMI);
    }

    private Button.OnClickListener calculateBMI = new Button.OnClickListener(){
		public void onClick(View v){
			DecimalFormat nf = new DecimalFormat("0.00");
			double height = Double.parseDouble(input_height.getText().toString())/100;
			double weight = Double.parseDouble(input_weight.getText().toString());
			double bmi = weight / (height * height);
			
			text_result.setText(getText(R.string.mainActivity_resultBMI) + nf.format(bmi));
			
			//give advice
			if(bmi > 25){
				text_advice.setText(R.string.mainActivity_adviceHeavy);
			}else if(bmi < 20){
				text_advice.setText(R.string.mainActivity_adviceLight);
			}else{
				text_advice.setText(R.string.mainActivity_adviceAverage);
			}
		}
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, R.string.mainActivity_aboutPositiveButtonText);
		menu.add(0, MENU_QUIT, 0, R.string.mainActivity_aboutNagtiveButtonText);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case MENU_ABOUT:
			openOptionsDialog();
			break;
		case MENU_QUIT:
			finish();
			break;
		}
		return true;
	}
    
    private void openOptionsDialog(){
    	//Toast.makeText(MainActivity.this, "BMI Calculator", Toast.LENGTH_SHORT).show();
    	new AlertDialog.Builder(MainActivity.this)
    	.setTitle(getText(R.string.mainActivity_aboutTitle))
    	.setMessage(getText(R.string.mainActivity_aboutContent))
    	.setPositiveButton(R.string.mainActivity_aboutPositiveButtonText,
    			new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
		.setNegativeButton(R.string.mainActivity_aboutNagtiveButtonText, 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Uri uri = Uri.parse(getString(R.string.mainActivity_aboutUri));
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
				})
    	.show();
    }
    
}
