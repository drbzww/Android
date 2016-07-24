package com.cytmxk.utils.view;

import java.util.ArrayList;

import com.cytmxk.utils.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewUtils extends ListView {

	private ArrayAdapter<String> lAdapter = null;
	
	public ListViewUtils(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ListViewUtils(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ListViewUtils(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public void initArrayAdapter(ArrayList<String> dates) {

		lAdapter = new ArrayAdapter<String>(getContext(),
				R.layout.listview_activity_item, dates);
		this.setAdapter(lAdapter);
	}
	
	public void setListViewHeight() {
		ListAdapter listAdapter = this.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		android.util.Log.i("chenyang", "listAdapter.getCount() = "
				+ listAdapter.getCount());
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, this);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
//			android.util.Log.i("chenyang", "listItem.getMeasuredHeight() = "
//					+ listItem.getMeasuredHeight());
		}

		ViewGroup.LayoutParams params = this.getLayoutParams();
		params.height = totalHeight
				+ (this.getDividerHeight() * (listAdapter.getCount() - 1));
		android.util.Log.i("chenyang", "params.height = " + params.height);
		android.util.Log.i("chenyang",
				"lvu.getDividerHeight() = " + this.getDividerHeight());
		if(params.height > 500){
			params.height = 500;
		}
		this.setLayoutParams(params);
	}
	

}
