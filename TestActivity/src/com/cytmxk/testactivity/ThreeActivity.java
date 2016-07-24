package com.cytmxk.testactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.widget.TextView;

public class ThreeActivity extends Activity {
	
	private TextView mActivityText = null;
	private TextView mTaskText = null;
	private TextView mProcessText = null;
	private TextView mThreadText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_three);
		initViews();
	}
	
	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.edit_text_activity);
		mTaskText = (TextView) findViewById(R.id.edit_text_task);
		mProcessText = (TextView) findViewById(R.id.edit_text_process);
		mThreadText = (TextView) findViewById(R.id.edit_text_thread);
		
		mActivityText.setText(this.toString());
		mTaskText.setText("current task id = " + this.getTaskId());
		mProcessText.setText("current process id = "
				+ Process.myPid());
		mThreadText.setText("current thread id = " + Thread.currentThread().getId());
	}
}
