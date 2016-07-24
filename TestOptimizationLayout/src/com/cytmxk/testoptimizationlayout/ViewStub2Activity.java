package com.cytmxk.testoptimizationlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewStub2Activity extends Activity {
	
	LinearLayout linearLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_stub);
		
		ViewStub view = (ViewStub) findViewById(R.id.stub_import_layout);
		if(null == linearLayout){
			linearLayout = (LinearLayout) view.inflate();
		}
		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setText("chenyang");
		linearLayout.setBackgroundColor(Color.GREEN);
	}
}
