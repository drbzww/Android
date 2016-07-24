package com.cytmxk.teststring.style.spannables;

import com.cytmxk.teststring.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpannablesFragment extends Fragment {

	private TextView mTvSpannable = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_spannables, container,
				false);
		mTvSpannable = (TextView) view
				.findViewById(R.id.tv_show_spannable_string);
		CharSequence text = SpannablesUtils.bold(SpannablesUtils
				.italic(getResources().getString(R.string.hello)),
				SpannablesUtils.color(Color.RED,
						getResources().getString(R.string.world)));
		mTvSpannable.setText(text);
		return view;
	}
}
