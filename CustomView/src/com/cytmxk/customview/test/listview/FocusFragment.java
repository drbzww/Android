package com.cytmxk.customview.test.listview;

import java.util.ArrayList;
import java.util.List;

import com.cytmxk.customview.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FocusFragment extends Fragment {
	
	private ListView mListView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_focus, container, false);
		mListView = (ListView) view.findViewById(R.id.focus_listView);
		List<String> data = new ArrayList<String>();
        data.add("I am item 1");
        data.add("I am item 2");
        data.add("I am item 3");
        data.add("I am item 4");
        data.add("I am item 5");
		final FocusListViewAdapter focusListViewAdapter = new FocusListViewAdapter(getActivity(), data);
		mListView.setAdapter(focusListViewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				focusListViewAdapter.setClickedItem(position);
				focusListViewAdapter.notifyDataSetChanged();
			}
		});
		return view;
	}
}
