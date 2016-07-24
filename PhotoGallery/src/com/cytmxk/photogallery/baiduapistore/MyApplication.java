package com.cytmxk.photogallery.baiduapistore;

import com.baidu.apistore.sdk.ApiStoreSDK;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		ApiStoreSDK.init(getApplicationContext(), "fdd2090d2c6aed731787e2cdff4e5991");
		super.onCreate();
	}
}
