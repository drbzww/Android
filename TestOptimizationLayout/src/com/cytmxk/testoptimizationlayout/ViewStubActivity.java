package com.cytmxk.testoptimizationlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;

public class ViewStubActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_view_stub);
		
		ViewStub view = (ViewStub) findViewById(R.id.stub_import_layout);
		view.setVisibility(View.VISIBLE);
		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setText("chenyang");
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.stub_import_layout_root);
		linearLayout.setBackgroundColor(Color.GREEN);
	}
}
