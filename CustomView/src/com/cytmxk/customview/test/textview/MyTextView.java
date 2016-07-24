package com.cytmxk.customview.test.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

	private Paint mPaint1 = null;
	private Paint mPaint2 = null;
	
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint1 = new Paint();
		mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
		mPaint1.setStyle(Paint.Style.FILL);
		mPaint2 = new Paint();
		mPaint2.setColor(Color.YELLOW);
		mPaint2.setStyle(Paint.Style.FILL);
	}



	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//绘制外层矩形
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
		//绘制内层矩形
		canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, mPaint2);
		canvas.save();
		//绘制文字前平移10像素
		canvas.translate(10, 0);
		super.onDraw(canvas);
		canvas.restore();
	}
	
	
}
