package com.cytmxk.criminalintent;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

public class PictureUtils {

	@SuppressWarnings("deprecation")
	public static BitmapDrawable getScaledDrawable(Activity activity,
			String path) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		float destWidth = display.getWidth();
		float destHeight = display.getHeight();

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		float srcWidth = options.outWidth;
		float srcHeight = options.outHeight;

		int inSampleSize = 1;
		if (srcWidth > destWidth || srcHeight > destHeight) {
			if (srcWidth > srcHeight) {
				inSampleSize = Math.round(srcHeight / destHeight);
			} else {
				inSampleSize = Math.round(srcWidth / destWidth);
			}
		}

		options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);

		return new BitmapDrawable(activity.getResources(), bitmap);
	}

	public static byte[] rotatePicture(byte[] data,
			CrimeCameraFragment.Orientation orientation) {
		Matrix m = new Matrix();
		switch (orientation) {
		case ORIENTATION_PORTRAIT_NORMAL:
            m.postRotate(90);
			break;
		case ORIENTATION_PORTRAIT_INVERTED:
			m.postRotate(270);
			break;
		case ORIENTATION_LANDSCAPE_NORMAL:
            m.postRotate(0);
			break;
		case ORIENTATION_LANDSCAPE_INVERTED:
            m.postRotate(180);
			break;

		default:
			break;
		}
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		return out.toByteArray();
	}

	public static void cleanImageView(ImageView iamgeview) {
		if (!(iamgeview.getDrawable() instanceof BitmapDrawable)) {
			return;
		}
		BitmapDrawable bitmapDrawable = (BitmapDrawable) iamgeview
				.getDrawable();
		bitmapDrawable.getBitmap().recycle();
		iamgeview.setImageDrawable(null);
	}
}
