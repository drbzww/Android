package com.cytmxk.testcustomlayout.attrs;

import com.cytmxk.testcustomlayout.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MyTextView1 extends TextView {

	public MyTextView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            Log.i("chenyang", "attrName = " + attrName + " , attrVal = " + attrVal);
        }
        
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.mytestviewattrs);
        int textColor = obtainStyledAttributes.getColor(R.styleable.mytestviewattrs_textColor, 0);
        float hight = obtainStyledAttributes.getDimension(R.styleable.mytestviewattrs_hight, 0);
        String textStr = obtainStyledAttributes.getString(R.styleable.mytestviewattrs_android_text);
        obtainStyledAttributes.recycle();
        android.util.Log.i("chenyang", "textColor = " + textColor + ", hight = " + hight + ", textStr = " + textStr);
        this.setTextColor(textColor);
        this.setHeight((int) hight);
	}
}
