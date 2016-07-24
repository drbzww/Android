package com.cytmxk.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class DisplayUtils {

	public static int getScreenWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	public static Rect getApplicationArea(Activity context) {
		Rect outRect = new Rect();
		context.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(outRect);
		return outRect;
	}

	public static Rect getApplicationArea(View view) {
		Rect outRect = new Rect();
		view.getRootView().getWindowVisibleDisplayFrame(outRect);
		return outRect;
	}

	public static Rect getViewArea(Activity context) {
		Rect outRect = new Rect();
		context.getWindow().findViewById(Window.ID_ANDROID_CONTENT)
				.getDrawingRect(outRect);
		return outRect;
	}

	public static Rect getViewArea(View view) {
		Rect outRect = new Rect();
		view.getRootView().findViewById(Window.ID_ANDROID_CONTENT)
				.getDrawingRect(outRect);
		return outRect;
	}

	public static int getStatusBarHeight(Activity context) {
		Rect outRect = new Rect();
		context.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(outRect);
		return outRect.top;
	}

	public static int getStatusBarHeight(View view) {
		Rect outRect = new Rect();
		view.getRootView().getWindowVisibleDisplayFrame(outRect);
		return outRect.top;
	}

	public static int getActionbarHeight(Activity context) {
		return context.getWindow().findViewById(Window.ID_ANDROID_CONTENT)
				.getTop()
				- getStatusBarHeight(context);
	}

	public static int getActionbarHeight(View view) {
		return view.getRootView().findViewById(Window.ID_ANDROID_CONTENT)
				.getTop()
				- getStatusBarHeight(view);
	}

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * 
     * @param pxValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */ 
    public static int px2dip(Context context, float pxValue) { 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int) (pxValue / scale + 0.5f); 
    } 
   
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * 
     * @param dipValue
     * @param scale
     *            （DisplayMetrics类中属性density）
     * @return
     */ 
    public static int dip2px(Context context, float dipValue) { 
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f); 
    } 
   
    /**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */ 
    public static int px2sp(Context context, float pxValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (pxValue / fontScale + 0.5f); 
    } 
   
    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */ 
    public static int sp2px(Context context, float spValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
    }  
}
