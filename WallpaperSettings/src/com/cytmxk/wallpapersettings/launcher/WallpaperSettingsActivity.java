package com.cytmxk.wallpapersettings.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class WallpaperSettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		onClickWallpaperPicker();
	}
	
    protected void onClickWallpaperPicker() {
        android.util.Log.i("chenyang", "onClickWallpaperPicker");
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        pickWallpaper.setComponent(getWallpaperPickerComponent());
        startActivityForResult(pickWallpaper, 10);
        finish();
    }
    
    protected ComponentName getWallpaperPickerComponent() {
        
        return new ComponentName("com.android.launcher3", "com.android.launcher3.LauncherWallpaperPickerActivity");
    }
}
