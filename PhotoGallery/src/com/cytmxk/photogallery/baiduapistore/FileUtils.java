package com.cytmxk.photogallery.baiduapistore;

import java.io.File;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class FileUtils {

	public static File getDiskCacheDir(Context context, String uniqueName) {
		String cachePath;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = context.getExternalCacheDir().getPath();
		} else {
			cachePath = context.getCacheDir().getPath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}
}
