package com.cytmxk.testbroadcast;

import android.os.Handler;

public class MyGlobalVariables{
	
	private static Handler sHander = null;

	public static Handler getHander() {
		return sHander;
	}

	public static void setHander(Handler hander) {
		sHander = hander;
	}

}
