package com.cytmxk.customview.test.scroll;

import com.cytmxk.customview.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScrollListFragment extends Fragment {

	private ListView mListView = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.fragment_list_scroll, container, false);
		mListView = (ListView) view.findViewById(R.id.lv_show_scroll_way);
		mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,
				new String[] { "Layout Scroll" }));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), ScrollActivity.class);
                intent.putExtra("Scroll Way", position);
                startActivity(intent);
			}
		});
		return view;
	}
}
