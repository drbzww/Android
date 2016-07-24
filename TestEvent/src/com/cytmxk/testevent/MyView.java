package com.cytmxk.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View implements View.OnTouchListener {

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnTouchListener(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d("chenyang", "View onTouchEvent" + event.getAction());
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.d("chenyang", "View dispatchTouchEvent" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d("chenyang", "View onTouch" + event.getAction());
		return false;
	}

}
