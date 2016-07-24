package com.cytmxk.criminalintent;

import java.util.Date;
import java.util.UUID;
import com.actionbarsherlock.app.SherlockFragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

@SuppressLint("SimpleDateFormat")
public class CrimeFragment extends SherlockFragment {
	
	public static final String EXTRA_CRIME_ID = "com.cytmxk.criminalintent.crime_id";
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	private Crime mCrime = null;
	
	private EditText mTitleField = null;
	private Button mDateButton = null;
	private CheckBox mSolvedCheckBox = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getSherlockActivity()).getCrime(crimeId);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		CrimeLab.get(getSherlockActivity()).saveCrimes();
	}
	
	public static CrimeFragment newInstance(UUID crimeId) {
		
		CrimeFragment mCrimeFragment = new CrimeFragment();
		Bundle arguments = new Bundle();
		arguments.putSerializable(EXTRA_CRIME_ID, crimeId);
		mCrimeFragment.setArguments(arguments);
		return mCrimeFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater.inflate(R.layout.fragment_crime, container, false);
		if (NavUtils.getParentActivityName(getSherlockActivity()) != null) {
			getSherlockActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		mTitleField = (EditText) v.findViewById(R.id.crime_title);
		mDateButton = (Button) v.findViewById(R.id.crime_date);
		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				mCrime.setTitle(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		updateDate();
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getSherlockActivity().getSupportFragmentManager();
				DatePickerFragment dateDialog = DatePickerFragment.newInstance(mCrime.getDate());
				dateDialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dateDialog.show(fm, DIALOG_DATE);
			}
		});
		
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				mCrime.setSolved(isChecked);
			}
		});
		
		return v;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		
		if (requestCode == REQUEST_DATE) {
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
		}
	}
	
	private void updateDate(){
		mDateButton.setText(DateFormat.format("EEEE, MMMM dd, yyyy", mCrime.getDate()));
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_crime, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getSherlockActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getSherlockActivity());
			}
			return true;
		case R.id.menu_item_delete_crime:
			CrimeLab.get(getSherlockActivity()).deleteCrime(mCrime);
			getSherlockActivity().finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
}
