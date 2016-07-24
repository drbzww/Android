package com.cytmxk.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyViewGroupA extends LinearLayout implements View.OnTouchListener {

	public MyViewGroupA(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnTouchListener(this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.d("chenyang", "ViewGroupA dispatchTouchEvent" + ev.getAction());
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.d("chenyang", "ViewGroupA onInterceptTouchEvent" + ev.getAction());
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("chenyang", "ViewGroupA onTouchEvent" + event.getAction());
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("chenyang", "ViewGroupA onTouch" + event.getAction());
		return false;
	}
}
