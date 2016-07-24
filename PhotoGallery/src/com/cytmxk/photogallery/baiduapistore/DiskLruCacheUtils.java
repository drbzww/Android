package com.cytmxk.photogallery.baiduapistore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import libcore.io.DiskLruCache;
import libcore.io.DiskLruCache.Editor;
import libcore.io.DiskLruCache.Snapshot;
import android.content.Context;

public class DiskLruCacheUtils {

	private DiskLruCache mDiskLruCache = null;
	
	public DiskLruCacheUtils(Context context) {
		super();
		// TODO Auto-generated constructor stub
		try {
			// 获取图片缓存路径
			File cacheDir = FileUtils.getDiskCacheDir(context, "thumb");
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			// 创建DiskLruCache实例，初始化缓存数据
			mDiskLruCache = DiskLruCache.open(cacheDir,
					AppInfoUtils.getAppVersion(context), 1, 20 * 1024 * 1024);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public InputStream getInputStream(String key) throws IOException{
		String md5String = MD5Utils.getMD5String(key);
		Snapshot snapshot = mDiskLruCache.get(md5String);
		InputStream inputStream = null;
		if (null != snapshot) {
			inputStream = snapshot.getInputStream(0);
		}
		return inputStream;
	}
	
	public void put(String key, byte[] value) throws IOException {
		String md5String = MD5Utils.getMD5String(key);
		Editor edit = mDiskLruCache.edit(md5String);
		android.util.Log.i("chenyang","edit = " + edit + ", url = " + key + ", md5String = " + md5String);
		if (null != edit) {
			OutputStream outputStream = edit.newOutputStream(0);
			outputStream.write(value);
			edit.commit();
		}
	}
	/**
	 * 在最后一次操作缓存后，必须要执行该方法，否者最后一次的操作不会更新到journal文件中
	 * @throws IOException
	 */
	public void flush() throws IOException {
		mDiskLruCache.flush();
	}
}
