package com.cytmxk.customview.test.textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView1 extends TextView {

	private int mViewWidth = 0;
	private Paint mPaint = null;
	private LinearGradient mLinearGradient = null;
	private Matrix mGradientMatrix = null;
	private int mTranslate = 0;

	public MyTextView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (null != mGradientMatrix) {
			mTranslate += mViewWidth / 5;
			if (mTranslate > 2 * mViewWidth) {
				mTranslate = -mViewWidth;
			}
			mGradientMatrix.setTranslate(mTranslate, 0);
			mLinearGradient.setLocalMatrix(mGradientMatrix);
			postInvalidateDelayed(100);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		if (0 == mViewWidth) {
			mViewWidth = getMeasuredWidth();
			if (mViewWidth > 0) {
				mPaint = getPaint();
				/*
				 * 其中，参数x0表示渐变的起始点x坐标；参数y0表示渐变的起始点y坐标；参数x1表示渐变的终点x坐标；
				 * 参数y1表示渐变的终点y坐标；参数colors表示渐变的颜色数组；参数positions用来指定颜色数组的
				 * 相对位置；参数tile表示平铺方式。通常，参数positions设为null，表示颜色数组以斜坡线的形式
				 * 均匀分布。
				 */
				mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0,
						new int[] { 0x33ffffff, 0xffffffff, 0x33ffffff }, null,
						Shader.TileMode.CLAMP);
				mPaint.setShader(mLinearGradient);
				mGradientMatrix = new Matrix();
			}
		}
	}
}
