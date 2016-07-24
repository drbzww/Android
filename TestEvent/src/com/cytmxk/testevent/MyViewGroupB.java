package com.cytmxk.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyViewGroupB extends LinearLayout implements View.OnTouchListener {

	public MyViewGroupB(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnTouchListener(this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.d("chenyang", "ViewGroupB dispatchTouchEvent" + ev.getAction());
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d("chenyang", "ViewGroupB onInterceptTouchEvent" + ev.getAction());
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("chenyang", "ViewGroupB onTouchEvent" + event.getAction());
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("chenyang", "ViewGroupB onTouch" + event.getAction());
		return false;
	}
}
