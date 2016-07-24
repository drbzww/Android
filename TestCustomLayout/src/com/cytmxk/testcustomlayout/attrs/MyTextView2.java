package com.cytmxk.testcustomlayout.attrs;

import com.cytmxk.testcustomlayout.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView2 extends TextView {

	private static final int[] mAttr = {R.attr.hight2,
		R.attr.textHintColor2, R.attr.textHint2 };

	private static final int ATTR_HIGHT2 = 0;
	private static final int ATTR_TEXTHINTCOLOR2 = 1;
	private static final int ATTR_TEXTHINT2 = 2;

	public MyTextView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		TypedArray obtainStyledAttributes = context.obtainStyledAttributes(
				attrs, mAttr);
		android.util.Log.i("chenyang", "obtainStyledAttributes.getIndexCount() = " + obtainStyledAttributes.getIndexCount());
		float hight = obtainStyledAttributes.getDimension(ATTR_HIGHT2, 0);
		int textHintColor = obtainStyledAttributes.getColor(ATTR_TEXTHINTCOLOR2, 0);
		String textHintStr = obtainStyledAttributes.getString(ATTR_TEXTHINT2);
		android.util.Log.i("chenyang", "textHintColor = " + textHintColor
				+ ", hight = " + hight + ", textHintStr = " + textHintStr);
		this.setHint(textHintStr);
		this.setHintTextColor(textHintColor);
		this.setHeight((int) hight);
		obtainStyledAttributes.recycle();
	}

}
