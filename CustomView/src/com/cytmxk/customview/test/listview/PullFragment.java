package com.cytmxk.customview.test.listview;

import java.util.ArrayList;
import java.util.List;

import com.cytmxk.customview.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

public class PullFragment extends Fragment implements
		View.OnClickListener {

	protected static final String TAG = "ViewHolderFragment";
	private ListView mListView = null;
	private Button mBtnAddItem = null;
	private List<String> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_pull, container,
				false);
		mBtnAddItem = (Button) view.findViewById(R.id.bt_add_item);
		mListView = (ListView) view.findViewById(R.id.lv_show);
		mData = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			mData.add("" + i);
		}
		mListView.setAdapter(new ViewHolderAdapter(getActivity(), mData));
		mListView.setSelection(6);
		mListView.setEmptyView(view.findViewById(R.id.tv_empty));

		mBtnAddItem.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mData.add("new");
		if (mListView.getAdapter() instanceof HeaderViewListAdapter) {
			HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) mListView.getAdapter();
			((ViewHolderAdapter)headerViewListAdapter.getWrappedAdapter()).notifyDataSetChanged();
			mListView.smoothScrollToPosition(mData.size());//headerview也占用一个位置
		} else {
			((ViewHolderAdapter) mListView.getAdapter()).notifyDataSetChanged();
			mListView.smoothScrollToPosition(mData.size() - 1);
		}
		// mListView.smoothScrollByOffset(-1);
	}
}
