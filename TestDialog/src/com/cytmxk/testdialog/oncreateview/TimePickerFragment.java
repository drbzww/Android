package com.cytmxk.testdialog.oncreateview;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.cytmxk.testdialog.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_TIME_KEY = "com.cytmxk.testdialog.alertdialog.TimePickerFragment.time";
	private TimePicker mTimePicker = null;
	private Time mTime = null;
	private Button mBtOk = null;
	private Button mBtCancle = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		mTime = (Time) getArguments().getSerializable(EXTRA_TIME_KEY);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mTime);
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_time_oncreateview, null);
		mTimePicker = (TimePicker) view.findViewById(R.id.tp_dialog_time);
		mTimePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		mTimePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
		mTimePicker
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						GregorianCalendar gregorianCalendar = new GregorianCalendar();
						gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY,
								hourOfDay);
						gregorianCalendar.set(GregorianCalendar.MINUTE, minute);
						mTime.setTime(gregorianCalendar.getTimeInMillis());
						getArguments().putSerializable(EXTRA_TIME_KEY, mTime);
					}
				});
		
		mBtOk = (Button) view.findViewById(R.id.bt_ok);
		mBtOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendResult(Activity.RESULT_OK);
				dismiss();
			}
		});
		
		mBtCancle = (Button) view.findViewById(R.id.bt_cancle);
		mBtCancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
		getDialog().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.title_dialog_time);
	}

	public static TimePickerFragment newInstance(Time time) {
		TimePickerFragment timePickerFragment = new TimePickerFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TIME_KEY, time);
		timePickerFragment.setArguments(args);
		return timePickerFragment;
	}

	private void sendResult(int resultCode){
		if (null == getTargetFragment()) {
			return;
		}
		
		Intent data = new Intent();
		data.putExtra(EXTRA_TIME_KEY, mTime);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
	}
}
