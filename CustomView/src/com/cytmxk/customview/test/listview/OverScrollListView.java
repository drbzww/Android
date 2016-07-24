package com.cytmxk.customview.test.listview;

import com.cytmxk.utils.common.DisplayUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class OverScrollListView extends ListView {
	
	private int mMaxOverScrollDistance = 20;

	public OverScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initData();
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		// TODO Auto-generated method stub
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, mMaxOverScrollDistance, isTouchEvent);
	}
	
	private void initData() {
		mMaxOverScrollDistance = DisplayUtils.dip2px(getContext(), mMaxOverScrollDistance);
	}
}
