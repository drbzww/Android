package com.cytmxk.testactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SixActivity extends Activity {
	
	private TextView mActivityText = null;
	private TextView mTaskText = null;
	private TextView mProcessText = null;
	private TextView mThreadText = null;
	private Button mGoToSevenButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_six);
		initViews();
	}
	
	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.edit_text_activity);
		mTaskText = (TextView) findViewById(R.id.edit_text_task);
		mProcessText = (TextView) findViewById(R.id.edit_text_process);
		mThreadText = (TextView) findViewById(R.id.edit_text_thread);
		mGoToSevenButton = (Button) findViewById(R.id.go_to_seven_activity);
		
		mActivityText.setText(this.toString());
		mTaskText.setText("current task id = " + this.getTaskId());
		mProcessText.setText("current process id = "
				+ Process.myPid());
		mThreadText.setText("current thread id = " + Thread.currentThread().getId());
		mGoToSevenButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SixActivity.this,SevenActivity.class);
				startActivity(intent);
			}
		});
	}
}
