package com.cytmxk.testdialog.alertdialog;

import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.cytmxk.testdialog.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_TIME_KEY = "com.cytmxk.testdialog.alertdialog.TimePickerFragment.time";
	private TimePicker mTimePicker = null;
	private Time mTime = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mTime = (Time) getArguments().getSerializable(EXTRA_TIME_KEY);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mTime);
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_time, null);
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

		return new AlertDialog.Builder(getActivity())
				.setTitle(R.string.dialog_time_title)
				.setView(view)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								sendResult(Activity.RESULT_OK);

							}
						}).setNegativeButton(android.R.string.cancel, null)
				.create();
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
