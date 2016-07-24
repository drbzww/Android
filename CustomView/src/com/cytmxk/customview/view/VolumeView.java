package com.cytmxk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class VolumeView extends View {

	private int mRectCount = 12;

	private int mWidth = 0;
	private int mRectWidth = 0;
	private int mRectHeight = 0;
	private int offset = 5;
	private Paint mPaint = null;
	private LinearGradient mLinearGradient = null;

	public VolumeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int i = 0; i < mRectCount; i++) {
			canvas.drawRect((float) (mWidth * 0.2 + mRectWidth * i + offset),
					(float) (mRectHeight * Math.random()),
					(float) (mWidth * 0.2 + mRectWidth * (i + 1)), mRectHeight,
					mPaint);
		}
		postInvalidateDelayed(100);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = getWidth();
		mRectHeight = getHeight();
		mRectWidth = (int) (mWidth * 0.6 / mRectCount);
		mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight,
				Color.YELLOW, Color.BLUE, Shader.TileMode.MIRROR);
		mPaint.setShader(mLinearGradient);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = 600;
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = 600;
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}
}
