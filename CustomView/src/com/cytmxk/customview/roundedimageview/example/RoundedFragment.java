package com.cytmxk.customview.roundedimageview.example;

import com.cytmxk.customview.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RoundedFragment extends Fragment implements
		AdapterView.OnItemSelectedListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_rounded, container,
				false);
		Spinner navSpinner = (Spinner) view.findViewById(R.id.spinner_nav);
		navSpinner.setAdapter(ArrayAdapter.createFromResource(
				navSpinner.getContext(), R.array.action_list,
				android.R.layout.simple_spinner_dropdown_item));
		navSpinner.setOnItemSelectedListener(this);

		if (savedInstanceState == null) {
			navSpinner.setSelection(0);
		}
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Fragment newFragment;
		switch (position) {
		default:
		case 0:
			// RoundedBitmap default
			newFragment = RoundedBitmapFragment
					.getInstance(RoundedBitmapFragment.RoundedType.DEFAULT);
			break;
		case 1:
			// RoundedBitmap oval
			newFragment = RoundedBitmapFragment
					.getInstance(RoundedBitmapFragment.RoundedType.OVAL);
			break;
		case 2:
			// RoundedBitmap select
			newFragment = RoundedBitmapFragment
					.getInstance(RoundedBitmapFragment.RoundedType.SELECT_CORNERS);
			break;
		case 3:
			// Rounded picasso
			newFragment = new RoundedPicassoFragment();
			break;
		case 4:
			// Rounded color
			newFragment = new RoundedColorFragment();
			break;
		}

		getFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, newFragment).commit();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
