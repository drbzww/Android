package com.cytmxk.testactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EightActivity extends Activity {

	private TextView mActivityText = null;
	private TextView mTaskText = null;
	private TextView mProcessText = null;
	private TextView mThreadText = null;
	private Button mGoToOtherThreeButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_eight);
		initViews();
	}

	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.edit_text_activity);
		mTaskText = (TextView) findViewById(R.id.edit_text_task);
		mProcessText = (TextView) findViewById(R.id.edit_text_process);
		mThreadText = (TextView) findViewById(R.id.edit_text_thread);
		mGoToOtherThreeButton = (Button) findViewById(R.id.go_to_other_second_activity);

		mActivityText.setText(this.toString());
		mTaskText.setText("current task id = " + this.getTaskId());
		mProcessText.setText("current process id = "
				+ Process.myPid());
		mThreadText.setText("current thread id = " + Thread.currentThread().getId());
		mGoToOtherThreeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent().setComponent(new ComponentName(
						"com.cytmxk.testotheractivity",
						"com.cytmxk.testotheractivity.SecondActivity"));
				startActivity(intent);
			}
		});
	}
}
