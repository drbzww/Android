package com.cytmxk.customview.test.listview;

import java.util.ArrayList;
import java.util.List;

import com.cytmxk.customview.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;

public class ViewHolderFragment extends Fragment implements
		View.OnClickListener {

	protected static final String TAG = "ViewHolderFragment";
	private ListView mListView = null;
	private Button mBtnAddItem = null;
	private List<String> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_view_holder, container,
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
		mListView.setOverscrollHeader(getResources().getDrawable(R.drawable.beauty4));
		mListView.setOverscrollFooter(getResources().getDrawable(R.drawable.beauty4));
		mListView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 触摸时
					android.util.Log.i(TAG, "触摸时 ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					// 移动时
					android.util.Log.i(TAG, "移动时 ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:
					// 离开时
					android.util.Log.i(TAG, "离开时 ACTION_UP");
					break;

				default:
					break;
				}
				return false;
			}
		});
		mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) {
				case OnScrollListener.SCROLL_STATE_IDLE:
					// 滑动停止时
					android.util.Log.i(TAG, "滑动停止 SCROLL_STATE_IDLE");
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
					// 正在滚动
					android.util.Log.i(TAG, "正在滚动 SCROLL_STATE_TOUCH_SCROLL");
					break;
				case OnScrollListener.SCROLL_STATE_FLING:
					// 手指抛动时 即手指用力滑动 在离开后ListView由于惯性继续滑动
					android.util.Log.i(TAG, "手指抛动 SCROLL_STATE_FLING");
					break;

				default:
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				// 滚动时一直调用
				android.util.Log.i(TAG, "滚动时一直调用 onScroll");
			}
		});

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
