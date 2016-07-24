package com.cytmxk.testoptimizationlayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MergeAcyivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_merge);

		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setText("chenyang");
	}
}
