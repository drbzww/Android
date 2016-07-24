package com.cytmxk.testactivitylife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {

	private TextView mActivityText = null;
	private Button mShowDialogButton = null;
	private Button mShowActivityDialogButton = null;
	private Button mBtStartThreeActivity = null;

	private final static String TAG = "FirstActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);
		initViews();

		android.util.Log.i(TAG, "onCreate()方法被调用");
	}

	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.text_activity);
		mShowDialogButton = (Button) findViewById(R.id.show_dialog);
		mShowActivityDialogButton = (Button) findViewById(R.id.show_activity_dialog);
		mBtStartThreeActivity = (Button) findViewById(R.id.start_three_activity);
		mActivityText.setText(this.toString());
		mShowDialogButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(FirstActivity.this)
						.setTitle(R.string.show_dialog_title)
						.setMessage(R.string.show_dialog_message)
						.setPositiveButton(R.string.show_dialog_positive,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

										return;
									}
								})
						.setNegativeButton(R.string.show_dialog_negative,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

										return;
									}
								}).create().show();
			}
		});
		
		mShowActivityDialogButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FirstActivity.this,SecondActivity.class));
			}
		});
		
		mBtStartThreeActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FirstActivity.this,ThreeActivity.class));
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		android.util.Log.i(TAG, "onStart()方法被调用");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		android.util.Log.i(TAG, "onResume()方法被调用");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		android.util.Log.i(TAG, "onPause()方法被调用");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		android.util.Log.i(TAG, "onStop()方法被调用");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		android.util.Log.i(TAG, "onRestart()方法被调用");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		android.util.Log.i(TAG, "onDestroy()方法被调用");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		android.util.Log.i(TAG, "onSaveInstanceState()方法被调用");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		android.util.Log.i(TAG, "onRestoreInstanceState()方法被调用");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		android.util.Log.i(TAG, "onConfigurationChanged()方法被调用 newConfig.orientation = " + newConfig.orientation);
	}

}
