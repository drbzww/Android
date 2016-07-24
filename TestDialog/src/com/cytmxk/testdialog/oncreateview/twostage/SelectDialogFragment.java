package com.cytmxk.testdialog.oncreateview.twostage;

import java.sql.Time;
import java.util.Date;
import com.cytmxk.testdialog.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SelectDialogFragment extends DialogFragment {

	private static final String DATE_TAG = "date_picker_dialog";
	protected static final String TIME_TAG = "time_picker_dialog";
	protected static final int DATE_DIALOG_RQUEST = 0;
	protected static final int TIME_DIALOG_RQUEST = 1;
	private Date mDate = null;
	private Button mBtDate = null;
	private Time mTime = null;
	private Button mBtTime = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_select_dialog, null);
		mBtDate = (Button) view.findViewById(R.id.bt_show_date);
		mBtTime = (Button) view.findViewById(R.id.bt_show_time);
		final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		mDate = new Date();
		mDate.setTime(System.currentTimeMillis());
		mBtDate.setText(DateFormat.format("EEEE, MMMM dd,yyyy", mDate));
		mBtDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePickerFragment fragment = DatePickerFragment.newInstance(mDate);
				fragment.setTargetFragment(SelectDialogFragment.this, DATE_DIALOG_RQUEST);
				fragment.show(fragmentManager, DATE_TAG);
			}
		});
		mTime = new Time(System.currentTimeMillis());
		mBtTime.setText(mTime.toString());
		mBtTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TimePickerFragment fragment = TimePickerFragment.newInstance(mTime);
				fragment.setTargetFragment(SelectDialogFragment.this, TIME_DIALOG_RQUEST);
				fragment.show(fragmentManager, TIME_TAG);
			}
		});
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == DATE_DIALOG_RQUEST) {
			mDate = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE_KEY);
			mBtDate.setText(DateFormat.format("EEEE, MMMM dd,yyyy", mDate));
		} else if (requestCode == TIME_DIALOG_RQUEST) {
			mTime = (Time) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME_KEY);
			mBtTime.setText(mTime.toString());
		}
	}
}
