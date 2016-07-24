package com.cytmxk.testprocess;

import java.util.List;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {

	private TextView mActivityText = null;
	private Button mShowDialogButton = null;
	private Button mShowActivityDialogButton = null;
	
	private ActivityManager mActivityManager = null;
	private List<RunningAppProcessInfo> mList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);
		initViews();
		mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onCreate\n",
				true);
		writeProcessToFile();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onStart\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onPause\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onStop\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onResume\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onRestart\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onDestroy\n",
				true);
		writeProcessToFile();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		FileUtils.writeFile(
				Environment.getExternalStorageDirectory()
						+ "/AllAPPProcessInfo.txt", "onSaveInstanceState\n",
				true);
		writeProcessToFile();
	}

	private void initViews() {
		mActivityText = (TextView) findViewById(R.id.text_activity);
		mShowDialogButton = (Button) findViewById(R.id.show_dialog);
		mShowActivityDialogButton = (Button) findViewById(R.id.show_activity_dialog);
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
	}
	
	private void writeProcessToFile() {
		
		mList = mActivityManager
				.getRunningAppProcesses();
		for (RunningAppProcessInfo processInfo : mList) {
			if(getPackageName().equals(processInfo.processName)){
				String importanceString = "";
				switch (processInfo.importance) {
				case RunningAppProcessInfo.IMPORTANCE_FOREGROUND:
					importanceString = "Foreground Process";
					break;
				case RunningAppProcessInfo.IMPORTANCE_VISIBLE:
					importanceString = "Visible Process";
					break;
				case RunningAppProcessInfo.IMPORTANCE_SERVICE:
					importanceString = "Service Process";
					break;
				case RunningAppProcessInfo.IMPORTANCE_BACKGROUND:
					importanceString = "Background Process";
					break;
				case RunningAppProcessInfo.IMPORTANCE_EMPTY:
					importanceString = "Empty Process";
					break;

				default:
					importanceString = "UnKnown Process";
					break;
				}
				String processInfoStr = "process processName " + processInfo.processName + "\n"
						+ "process importance = " + processInfo.importance + "\n"
						+ "process importanceString = " + importanceString + "\n"
						+ "process importanceReasonCode = " + processInfo.importanceReasonCode + "\n"
						+ "process importanceReasonComponent = " + processInfo.importanceReasonComponent + "\n"
						+ "process importanceReasonPid = " + processInfo.importanceReasonPid + "\n"
						+ "------------------------------------------------------------------------" + "\n";
				FileUtils.writeFile(
						Environment.getExternalStorageDirectory()
								+ "/AllAPPProcessInfo.txt", processInfoStr,
						true);
				break;
			}
		}
		mList.clear();
	}
}
