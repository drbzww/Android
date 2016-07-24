package com.cytmxk.testcustomlayout.popupwindow;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
	
	public static int getScreenWidth(WindowManager windowManager){
		
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	
	public static int getScreenHeight(WindowManager windowManager){
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	
}
