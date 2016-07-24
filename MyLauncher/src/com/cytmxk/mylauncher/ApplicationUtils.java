package com.cytmxk.mylauncher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

public class ApplicationUtils {

	/*
	 * 根据包名 查询 图标
	 */
	public static Drawable getAppIcon(PackageManager pm, String packname) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadIcon(pm);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 获取程序的版本号
	 */
	public static String getAppVersion(PackageManager pm, String packname) {

		try {
			PackageInfo packinfo = pm.getPackageInfo(packname, 0);
			return packinfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 获取程序的名字
	 */
	public static String getAppName(PackageManager pm, String packname) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadLabel(pm).toString();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 获取程序的权限
	 */
	public static String[] getAppPremission(PackageManager pm, String packname) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(packname,
					PackageManager.GET_PERMISSIONS);
			// 获取到所有的权限
			return packinfo.requestedPermissions;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 获取程序的签名
	 */
	public static String getAppSignature(PackageManager pm, String packname) {
		try {
			PackageInfo packinfo = pm.getPackageInfo(packname,
					PackageManager.GET_SIGNATURES);
			// 获取到所有的权限
			return packinfo.signatures[0].toCharsString();

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
