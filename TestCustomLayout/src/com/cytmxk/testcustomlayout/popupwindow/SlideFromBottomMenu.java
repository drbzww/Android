package com.cytmxk.testcustomlayout.popupwindow;

import java.util.ArrayList;

import com.cytmxk.testcustomlayout.R;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SlideFromBottomMenu {

	private static final String TAG = "SlideFromBottomMenu";
	private FragmentActivity mContext;
	private PopupWindow mPopupWindow = null;
	private View mAnimaView;
	private View mDismissView;
	
    //anima
    private Animation curExitAnima;
    private Animator curExitAnimator;
    private Animation curEnterAnima;
    private Animator curEnterAnimator;
	
	public interface Callbacks {
		public ArrayList<String> buildPopmenuItems(String[] popmenuItems);
	}

	public SlideFromBottomMenu(FragmentActivity context, View contentView) {
		initView(context, contentView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);//默认占满全屏
	}
    
	public SlideFromBottomMenu(FragmentActivity context, View contentView, int w, int h) {
		initView(context, contentView, w, h);
	}
    
    private void initView(FragmentActivity context, View contentView, int w,int h){

    	mContext = context;
		mPopupWindow = new PopupWindow(contentView, w, h);
        // 设置PopupWindow弹出窗体可点击
        mPopupWindow.setFocusable(true);
        
        mAnimaView = contentView.findViewById(R.id.popup_anima);
        mDismissView = contentView.findViewById(R.id.click_to_dismiss);
        if (mDismissView!=null) {
            mDismissView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            if (mAnimaView!=null) {
                mAnimaView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
        
        initAnimation();
    }
    
    private void initAnimation() {
    	curEnterAnima=AnimationUtils.loadAnimation(mContext, R.anim.slide_bottom_in);
        curEnterAnimator=null;
        curExitAnima=AnimationUtils.loadAnimation(mContext, R.anim.slide_bottom_out);
        curExitAnimator=null;
    }
    
	public void dismiss() {
        try {
            if (curExitAnima!=null){
                curExitAnima.setAnimationListener(mExitAnimationListener);
                mAnimaView.clearAnimation();
                mAnimaView.startAnimation(curExitAnima);
            }else if (curExitAnimator!=null){
                curExitAnimator.removeListener(mExitAnimatorListener);
                curExitAnimator.addListener(mExitAnimatorListener);
                curExitAnimator.start();
            }else {
                mPopupWindow.dismiss();
            }
        } catch (Exception e) {
            Log.d(TAG, "dismiss error");
        }
    }
    
    private Animator.AnimatorListener mExitAnimatorListener=new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Animation.AnimationListener mExitAnimationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
	
	public void showPopupWindow() {
		try {
			tryToShowPopup(0, null);
		} catch (Exception e) {
			Log.d(TAG, "tryToShowPopup error");
		}
	}
    
    private void showEnterAnimation() {
        if (curEnterAnima != null && mAnimaView != null) {
            mAnimaView.clearAnimation();
            mAnimaView.startAnimation(curEnterAnima);
        }
        if (curEnterAnima == null && curEnterAnimator != null && mAnimaView != null &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        	curEnterAnimator.start();
        }
    }
    
    private void tryToShowPopup(int res, View v) throws Exception {
    	
    	showEnterAnimation();
        //传递了view
        if (res == 0 && v != null) {
            mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
        }
        //传递了res
        if (res != 0 && v == null) {
            mPopupWindow.showAtLocation(mContext.findViewById(res), Gravity.NO_GRAVITY, 0, 0);
        }
        //什么都没传递，取顶级view的id
        if (res == 0 && v == null) {
			mPopupWindow.showAtLocation(
					mContext.findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, 0);
        }
    }
}
