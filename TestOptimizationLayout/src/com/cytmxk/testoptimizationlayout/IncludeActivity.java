package com.cytmxk.testoptimizationlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IncludeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_include);
		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setText("chenyang");
		View view = findViewById(R.id.include_import_layout);
		view.setBackgroundColor(Color.GREEN);
	}
}
