package com.cytmxk.customview.test.scroll;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DragView extends View {

	private int mLastX = 0;
	private int mLastY = 0;

	public DragView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		ininView();
	}
	
    private void ininView() {
        // 给View设置背景颜色，便于观察
        setBackgroundColor(Color.BLUE);
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
            // 记录触摸点坐标
			android.util.Log.i("chenyang", "onTouchEvent ACTION_DOWN x = " + x + ",  y = " + y);
			mLastX = x;
			mLastY = y;
			break;
		case MotionEvent.ACTION_HOVER_MOVE:
			// 计算偏移量
			android.util.Log.i("chenyang", "onTouchEvent ACTION_HOVER_MOVE x = " + x + ",  y = " + y);
			int offsetX = x - mLastX;
			int offsetY = y - mLastY;
			// 在当前left、top、right、bottom的基础上加上偏移量
			layout(getLeft() + offsetX, getTop() + offsetY, getRight()
					+ offsetX, getBottom() + offsetY);
			break;

		default:
			break;
		}
		return true;
	}
}
