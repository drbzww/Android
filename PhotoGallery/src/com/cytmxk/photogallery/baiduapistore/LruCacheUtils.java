package com.cytmxk.photogallery.baiduapistore;

import android.support.v4.util.LruCache;

public class LruCacheUtils<CacheTye,User> {

	private LruCache<String, CacheTye> mLruCache = null;
	private Callbacks<CacheTye> mCallbacks = null;

	public LruCacheUtils(User user) {
		super();
		// TODO Auto-generated constructor stub
		this.mCallbacks = (Callbacks<CacheTye>) user;
		// 获取应用程序最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		// 设置图片缓存大小为程序最大可用内存的1/8
		mLruCache = new LruCache<String, CacheTye>(cacheSize) {

			@Override
			protected int sizeOf(String key, CacheTye value) {
				// TODO Auto-generated method stub
				return mCallbacks.onGetItemSize(value);
			}
		};
	}
	
	public interface Callbacks<CacheTye>{
		public int onGetItemSize(CacheTye value);
	}

	public CacheTye get(String key) {
		CacheTye cacheTye = mLruCache.get(MD5Utils.getMD5String(key));
		return cacheTye;
	}

	public void put(String key, CacheTye value) {
		mLruCache.put(MD5Utils.getMD5String(key), value);
	}

}
