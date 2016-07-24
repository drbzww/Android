package com.cytmxk.testdrawable.imagebutton;

import com.cytmxk.testdrawable.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ImageButtonFragment extends Fragment {

	private ImageButton mShowDisableBtn = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_imagebutton, container, false);
		mShowDisableBtn = (ImageButton) view.findViewById(R.id.ib_show_disable);
		mShowDisableBtn.setEnabled(false);
		return view;
	}
}
