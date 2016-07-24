package com.cytmxk.customview.topbar;

import com.cytmxk.customview.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout {
	
	private int mLeftTextColor = 0;
	private Drawable mLeftBackground = null;
	private String mLeftText = null;
	private int mRightTextColor = 0;
	private Drawable mRightBackground = null;
	private String mRightText = null;
	private String mTitle = null;
	private float mTitleTextSize = 0;
	private int mTitleTextColor = 0;
	
	private Button mLeftButton = null;
	private Button mRightButton = null;
	private TextView mTitleView = null;
	private LayoutParams mLeftParams = null;
	private LayoutParams mRightParams = null;
	private LayoutParams mTitleParams = null;
	private topbarClickListener mListener = null;

	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
		mLeftTextColor = typedArray.getColor(R.styleable.TopBar_tb_leftTextColor, 0);
		mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_tb_leftBackground);
		mLeftText = typedArray.getString(R.styleable.TopBar_tb_leftText);
		
		mRightTextColor = typedArray.getColor(R.styleable.TopBar_tb_rightTextColor, 0);
		mRightBackground = typedArray.getDrawable(R.styleable.TopBar_tb_rightBackground);
		mRightText = typedArray.getString(R.styleable.TopBar_tb_rightText);
		
		mTitle = typedArray.getString(R.styleable.TopBar_tb_title);
		mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_tb_titleTextSize, 10);
		mTitleTextColor = typedArray.getColor(R.styleable.TopBar_tb_titleTextColor, 0);
		typedArray.recycle();
		
		mLeftButton = new Button(getContext());
		mRightButton = new Button(getContext());
		mTitleView = new TextView(getContext());
		
		mLeftButton.setTextColor(mLeftTextColor);
		mLeftButton.setBackground(mLeftBackground);
		mLeftButton.setText(mLeftText);
		mRightButton.setTextColor(mRightTextColor);
		mRightButton.setBackground(mRightBackground);
		mRightButton.setText(mRightText);
		mTitleView.setText(mTitle);
		mTitleView.setTextSize(mTitleTextSize);
		mTitleView.setTextColor(mTitleTextColor);
		mTitleView.setGravity(Gravity.CENTER);
		
		mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
		addView(mLeftButton, mLeftParams);
		
		mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
		addView(mRightButton, mRightParams);
		
		mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
		addView(mTitleView, mTitleParams);
		
		mLeftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.leftClick();
			}
		});
		mRightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListener.rigntClick();
			}
		});
	}
	
	public interface topbarClickListener {
		void leftClick();
		void rigntClick();
	}

	public void setOnTopbarClickListener(topbarClickListener listener) {
		this.mListener = listener;
	}
	
	/**
	 * 设置按钮的显示与否
	 * 
	 * @param id 区分按钮
	 * @param flag 是否显示
	 */
	public void setButtonVisable(int id, boolean flag) {
		if (flag) {
			if (0 == id) {
				mLeftButton.setVisibility(View.VISIBLE);
			} else {
				mRightButton.setVisibility(View.VISIBLE);
			}
		} else {
			if (0 == id) {
				mLeftButton.setVisibility(View.GONE);
			} else {
				mRightButton.setVisibility(View.GONE);
			}
		}
	}
}
