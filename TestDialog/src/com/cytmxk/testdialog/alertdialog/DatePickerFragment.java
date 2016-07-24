package com.cytmxk.testdialog.alertdialog;

import java.util.Calendar;
import java.util.Date;
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
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment {

	public static final String EXTRA_DATE_KEY = "com.cytmxk.testdialog.alertdialog.date";
	private DatePicker mDatePicker = null;
	private Date mDate = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		android.util.Log.i("chenyang","onCreateDialog");
		mDate = (Date) getArguments().getSerializable(EXTRA_DATE_KEY);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);

		mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_dp);
		mDatePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
						getArguments().putSerializable(EXTRA_DATE_KEY, mDate);

					}
				});
		return new AlertDialog.Builder(getActivity())
				.setTitle(R.string.dialog_date_title).setView(view)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
					}
				})
				.setNegativeButton(android.R.string.cancel, null).create();
	}

	private void sendResult(int resultCode) {
		if (null == getTargetFragment()) {
			return;
		}
		Intent data = new Intent();
		data.putExtra(EXTRA_DATE_KEY, mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
	}
	public static DatePickerFragment newInstance(Date date) {
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE_KEY, date);
		datePickerFragment.setArguments(args);
		return datePickerFragment;
	}
}
