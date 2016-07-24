package com.cytmxk.photogallery.baiduapistore;

import android.util.Log;

public class LogUtils {
	// 使用Log来显示调试信息,因为log在实现上有字符长度限制
	// 所以这里使用自己分节的方式来输出足够长度的message
	public static void i(String TAG, String str) {
		int index = 0;
		int maxLength = 3000;
		String sub;
		while (index < str.length()) {
			// java的字符不允许指定超过总的长度end
			if (str.length() <= index + maxLength) {
				sub = str.substring(index);
			} else {
				sub = str.substring(index, index + maxLength);
			}

			index += maxLength;
			Log.i(TAG, sub);
		}
	}
	
	public static void d(String TAG, String str) {
		int index = 0;
		int maxLength = 3000;
		String sub;
		while (index < str.length()) {
			// java的字符不允许指定超过总的长度end
			if (str.length() <= index + maxLength) {
				sub = str.substring(index);
			} else {
				sub = str.substring(index, index + maxLength);
			}

			index += maxLength;
			Log.d(TAG, sub);
		}
	}
	
	public static void e(String TAG, String str) {
		int index = 0;
		int maxLength = 3000;
		String sub;
		while (index < str.length()) {
			// java的字符不允许指定超过总的长度end
			if (str.length() <= index + maxLength) {
				sub = str.substring(index);
			} else {
				sub = str.substring(index, index + maxLength);
			}

			index += maxLength;
			Log.e(TAG, sub);
		}
	}
}
