package com.cytmxk.customview.viewgroup;

import com.cytmxk.customview.R;
import com.cytmxk.utils.common.DisplayUtils;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyScrollView extends ViewGroup {

	private int mScreenHeight = 0;
	private Scroller mScroller = null;
	private int mLastY = 0;
	private int mStart = 0;
	private int mEnd = 0;

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mScroller = new Scroller(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View childView = getChildAt(i);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		mScreenHeight = DisplayUtils.getViewArea(this).height()
				- (int) getResources().getDimension(R.dimen.tab_height);

		android.util.Log.i(
				"chenyang",
				"1Area one  Width: "
						+ DisplayUtils.getScreenWidth(getContext())
						+ "  Height: "
						+ DisplayUtils.getScreenHeight(getContext()));
		android.util.Log.i(
				"chenyang",
				"1Area two  Width: "
						+ DisplayUtils.getApplicationArea(this).width()
						+ "  Height: "
						+ DisplayUtils.getApplicationArea(this).height()
						+ "  status bar height : "
						+ DisplayUtils.getStatusBarHeight(this));
		android.util.Log.i(
				"chenyang",
				"1Area three  Width: " + DisplayUtils.getViewArea(this).width()
						+ "  Height: "
						+ DisplayUtils.getViewArea(this).height()
						+ "  action bar height : "
						+ DisplayUtils.getActionbarHeight(this));

		int childCount = getChildCount();

		LayoutParams mlp = getLayoutParams();
		mlp.height = mScreenHeight * childCount;
		setLayoutParams(mlp);
		for (int i = 0; i < childCount; i++) {
			View childView = getChildAt(i);
			if (childView.getVisibility() != View.GONE) {
				childView.layout(l, mScreenHeight * i, r, mScreenHeight
						* (i + 1));
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = y;
			mStart = getScrollY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			int dy = mLastY - y;
			if (getScrollY() < 0) {
				dy = 0;
			}
			if (getScrollY() > getHeight() - mScreenHeight) {
				dy = 0;
			}
			scrollBy(0, dy);
			mLastY = y;
			break;
		case MotionEvent.ACTION_UP:
			int dScrollY = checkAlignment();
			if (dScrollY > 0) {
				if (dScrollY < mScreenHeight / 3) {
					mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
				} else {
					mScroller.startScroll(0, getScrollY(), 0, mScreenHeight
							- dScrollY);
				}
			} else {
				if (-dScrollY < mScreenHeight / 3) {
					mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
				} else {
					mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight
							- dScrollY);
				}
			}
			break;
		}
		postInvalidate();
		return true;
	}

	private int checkAlignment() {
		mEnd = getScrollY();
		boolean isUp = ((mEnd - mStart) > 0) ? true : false;
		int lastPrev = mEnd % mScreenHeight;
		int lastNext = mScreenHeight - lastPrev;
		if (isUp) {
			// 向上的
			return lastPrev;
		} else {
			return -lastNext;
		}
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			scrollTo(0, mScroller.getCurrY());
			postInvalidate();
		}
	}
}
