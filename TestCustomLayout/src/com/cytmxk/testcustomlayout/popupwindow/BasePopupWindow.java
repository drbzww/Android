package com.cytmxk.testcustomlayout.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.PopupWindow;

public abstract class BasePopupWindow {

    protected Activity mContext;
	protected PopupWindow mPopupWindow;
    protected View mPopupView;
    protected View mAnimaView;
    protected View mDismissView;

    public BasePopupWindow(Activity context) {
        initView(context,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public BasePopupWindow(Activity context, int w, int h) {
        initView(context,w,h);
    }
    
    private void initView(Activity context,int w,int h){
        mContext = context;

        mPopupView = getPopupView();
        mPopupView.setFocusableInTouchMode(true);
        //默认占满全屏
        mPopupWindow = new PopupWindow(mPopupView, w, h);
        //指定透明背景，back键相关
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        //无需动画
        mPopupWindow.setAnimationStyle(0);
        
        mAnimaView = getAnimaView();
    }
	
    //------------------------------------------抽象-----------------------------------------------
    protected abstract View getPopupView();
    protected abstract View getAnimaView();
    
    /**
     * 生成ScaleAnimation
     */
    protected Animation getScaleAnimation(float fromX, float toX, float fromY, float toY,
        int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        Animation scaleAnimation =
            new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
                               pivotYValue);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        Animation scaleAnimation =
            new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                               Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }
	
}
