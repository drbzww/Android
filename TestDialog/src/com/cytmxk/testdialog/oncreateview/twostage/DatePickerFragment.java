package com.cytmxk.testdialog.oncreateview.twostage;

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
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment {

	public static final String EXTRA_DATE_KEY = "com.cytmxk.testdialog.alertdialog.date";
	private DatePicker mDatePicker = null;
	private Date mDate = null;
	private Button mBtOk = null;
	private Button mBtCancle = null;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		android.util.Log.i("chenyang", "onCreateDialog");
		mDate = (Date) getArguments().getSerializable(EXTRA_DATE_KEY);
		android.util.Log.i("chenyang","onCreateView " + DateFormat.format("EEEE, MMMM dd,yyyy", mDate).toString());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date_oncreateview, null);

		mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_dp);
		mDatePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				new DatePicker.OnDateChangedListener() {

					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mDate = new GregorianCalendar(year, monthOfYear,
								dayOfMonth).getTime();
						android.util.Log.i("chenyang","onDateChanged " + DateFormat.format("EEEE, MMMM dd,yyyy", mDate).toString());
						getArguments().putSerializable(EXTRA_DATE_KEY, mDate);

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
				R.layout.title_dialog_date);
	}

	private void sendResult(int resultCode) {
		if (null == getTargetFragment()) {
			return;
		}
		Intent data = new Intent();
		data.putExtra(EXTRA_DATE_KEY, mDate);
		android.util.Log.i("chenyang","sendResult " + DateFormat.format("EEEE, MMMM dd,yyyy", mDate).toString());
		getTargetFragment().onActivityResult(getTargetRequestCode(),
				resultCode, data);
	}

	public static DatePickerFragment newInstance(Date date) {
		android.util.Log.i("chenyang","newInstance " + DateFormat.format("EEEE, MMMM dd,yyyy", date).toString());
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE_KEY, date);
		datePickerFragment.setArguments(args);
		return datePickerFragment;
	}
}
