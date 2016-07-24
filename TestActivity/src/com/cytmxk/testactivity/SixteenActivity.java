package com.cytmxk.testactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SixteenActivity extends Activity {

	private TextView mActivityText = null;
	private TextView mTaskText = null;
	private TextView mProcessText = null;
	private TextView mThreadText = null;
	private Button mGoToOtherFourButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_sixteen);
		initViews();
	}

	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.edit_text_activity);
		mTaskText = (TextView) findViewById(R.id.edit_text_task);
		mProcessText = (TextView) findViewById(R.id.edit_text_process);
		mThreadText = (TextView) findViewById(R.id.edit_text_thread);
		mGoToOtherFourButton = (Button) findViewById(R.id.go_to_other_four_activity);

		mActivityText.setText(this.toString());
		mTaskText.setText("current task id = " + this.getTaskId());
		mProcessText.setText("current process id = "
				+ Process.myPid());
		mThreadText.setText("current thread id = " + Thread.currentThread().getId());
		mGoToOtherFourButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent().setComponent(new ComponentName(
						"com.cytmxk.testotheractivity",
						"com.cytmxk.testotheractivity.FourActivity"));
				startActivity(intent);
			}
		});
	}
}
