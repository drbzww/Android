package com.cytmxk.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import com.actionbarsherlock.app.SherlockDialogFragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class DatePickerFragment extends SherlockDialogFragment {

	public static final String EXTRA_DATE = "com.cytmxk.criminalintent.crime_date";
	
	private Date mDate = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(mDate);

		View view = getSherlockActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);
		DatePicker mDatePicker = (DatePicker) view
				.findViewById(R.id.dialog_date_datePicker);
		mDatePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
						getArguments().putSerializable(EXTRA_DATE, mDate);

					}
				});

		return new AlertDialog.Builder(getSherlockActivity()).setView(view)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						sendResult(Activity.RESULT_OK);
					}
				}).create();
	}

	private void sendResult(int resultCode) {
		if (getTargetFragment() == null) {
			return;
		}
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATE, mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
	
	public static DatePickerFragment newInstance(Date date) {
		DatePickerFragment mDatePickerFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		mDatePickerFragment.setArguments(args);
		return mDatePickerFragment;
	}
}
