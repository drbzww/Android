package com.cytmxk.testdivide.listview;

import java.util.ArrayList;

import com.cytmxk.testdivide.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LvDivideFragment extends Fragment {

	private ListView mListview = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_lv_divide, container,
				false);
		mListview = (ListView) view
				.findViewById(R.id.lv_test_divide);

		android.util.Log.i("chenyang",
				"listview.getDividerHeight() = " + mListview.getDividerHeight()
						+ ", listview.getDivider()" + mListview.getDivider());
		ArrayList<String> listItemInfos = new ArrayList<String>();
		listItemInfos.add("13287980987");
		listItemInfos.add("15467687657");
		listItemInfos.add("13589768765");
		listItemInfos.add("13678987654");
		listItemInfos.add("16699764468");
		mListview.setAdapter(new ListViewAdapter(getActivity(), inflater, listItemInfos));
		return view;
	}
}
