package com.cytmxk.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircleProgressView extends View {

	private int mMeasureHeigth = 0;
	private int mMeasureWidth = 0;

	private Paint mCirclePaint = null;
	private float mCircleXY = 0;
	private float mRadius = 0;

	private Paint mArcPaint = null;
	private RectF mArcRectF = null;
	private float mSweepAngle = 0;
	private float mSweepValue = 66;

	private Paint mTextPaint = null;
	private String mShowText = null;
	private float mShowTextSize = 0;
	private FontMetricsInt mFontMetricsInt = null;

	public CircleProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		mMeasureWidth = measureWidth(widthMeasureSpec);
		mMeasureHeigth = measureHeight(heightMeasureSpec);
		setMeasuredDimension(mMeasureWidth, mMeasureHeigth);
		initView();
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

	private void initView() {
		float length = 0;
		if (mMeasureHeigth >= mMeasureWidth) {
			length = mMeasureWidth;
		} else {
			length = mMeasureHeigth;
		}

		mCircleXY = length / 2;
		mRadius = (float) (length * 0.5 / 2);
		mCirclePaint = new Paint();
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setColor(getResources().getColor(
				android.R.color.holo_blue_bright));

		mArcRectF = new RectF((float) (length * 0.1), (float) (length * 0.1),
				(float) (length * 0.9), (float) (length * 0.9));
		mSweepAngle = (mSweepValue / 100f) * 360f;
		mArcPaint = new Paint();
		mArcPaint.setAntiAlias(true);
		mArcPaint.setColor(getResources().getColor(
				android.R.color.holo_blue_bright));
		mArcPaint.setStrokeWidth((float) (length * 0.1));
		mArcPaint.setStyle(Paint.Style.STROKE);

		mShowText = setShowText();
		mShowTextSize = setShowTextSize();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mShowTextSize);
		mTextPaint.setTextAlign(Paint.Align.CENTER);
		mFontMetricsInt = mTextPaint.getFontMetricsInt();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// 绘制圆
		canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
		// 绘制弧线
		canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
		// 绘制文字
		canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY
				- (mFontMetricsInt.bottom - mFontMetricsInt.top) / 2
				- mFontMetricsInt.top, mTextPaint);
	}

	private float setShowTextSize() {
		this.invalidate();
		return 40;
	}

	private String setShowText() {
		this.invalidate();
		return "Android Skill";
	}

	public void forceInvalidate() {
		this.invalidate();
	}

	public void setSweepValue(float sweepValue) {
		if (sweepValue != 0) {
			mSweepValue = sweepValue;
		} else {
			mSweepValue = 25;
		}
		this.invalidate();
	}
}
