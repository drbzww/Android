package com.cytmxk.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.cytmxk.utils.R;
import com.cytmxk.utils.view.ListViewUtils;

public class ListViewActivity extends Activity {

	private ListViewUtils lvu = null;

	ArrayList<String> dates = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listview_activity);

		initDates();
		initViews();
	}

	private void initDates() {
		dates = new ArrayList<String>();
		dates.add("1 apple");
		dates.add("2 apple");
		dates.add("3 apple");
		dates.add("4 apple");
		dates.add("5 apple");
		dates.add("6 apple");
//		dates.add("1 apple");
//		dates.add("2 apple");
//		dates.add("3 apple");
//		dates.add("4 apple");
//		dates.add("5 apple");
//		dates.add("6 apple");
//		dates.add("1 apple");
//		dates.add("2 apple");
//		dates.add("3 apple");
//		dates.add("4 apple");
//		dates.add("5 apple");
//		dates.add("6 apple");
//		dates.add("1 apple");
//		dates.add("2 apple");
//		dates.add("3 apple");
//		dates.add("4 apple");
//		dates.add("5 apple");
//		dates.add("6 apple");
	}

	private void initViews() {
		lvu = (ListViewUtils) findViewById(R.id.lv_users);
		lvu.initArrayAdapter(dates);
		lvu.setListViewHeight();
	}

}
