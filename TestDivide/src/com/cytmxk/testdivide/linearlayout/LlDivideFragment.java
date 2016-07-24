package com.cytmxk.testdivide.linearlayout;

import com.cytmxk.testdivide.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LlDivideFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_ll_divide, container, false);
		
		int number = 1;
		int childCount = linearLayout.getChildCount();
		for (int i = 0; i < childCount; i++) {
			LinearLayout childLinearLayout = (LinearLayout) linearLayout.getChildAt(i);
			int childCount2 = childLinearLayout.getChildCount();
			for (int j = 0; j < childCount2; j++) {
				TextView numberTv = (TextView) childLinearLayout.getChildAt(j);
				numberTv.setText("" + number);
				number ++;
			}
		}
		return linearLayout;
	}
}
