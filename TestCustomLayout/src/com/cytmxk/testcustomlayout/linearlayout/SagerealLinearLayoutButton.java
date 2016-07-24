package com.cytmxk.testcustomlayout.linearlayout;

import com.cytmxk.testcustomlayout.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.os.Build;
import android.util.AttributeSet;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SagerealLinearLayoutButton extends LinearLayout {
    
    public SagerealLinearLayoutButton(Context context) {
        this(context, null);
    }

    public SagerealLinearLayoutButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SagerealLinearLayoutButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void addButtons(int[] btnIcons, int[] btnIds) {
        this.removeAllViews();

        final int count = btnIcons.length;
        for (int i = 0; i < count; i++) {
        	addButton(btnIcons[i], btnIds[i], i);
        }
    }
    
    private void addButton(int iconResId, int btnId, final int position) {
        
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setId(btnId);
        imageButton.setPadding(48, 24, 48, 24);
        imageButton.setBackgroundResource(R.drawable.btn_dialpad_key);
        imageButton.setImageResource(iconResId);

        addView(imageButton, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 0));
    }

}
