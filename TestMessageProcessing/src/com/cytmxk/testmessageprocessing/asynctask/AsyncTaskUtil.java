package com.cytmxk.testmessageprocessing.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class AsyncTaskUtil {

	/** The enumeration of {@link AsyncTask} objects used in this class. */
    public enum Tasks {
        BLUR_RS_PICTURE,
        MERGE_LTR_PICTURE,
        ROUNDED_CORNER_PICTURE
    }
    
    public interface AsyncTaskListener {
        public void onGetResultPicture(Bitmap bitmap);
    }
    
    private static AsyncTaskExecutor sAsyncTaskExecutor;

    private static void initTaskExecutor() {
        sAsyncTaskExecutor = AsyncTaskExecutors.createThreadPoolExecutor();
    }
    
	public static void getRSBlurPicture(final Context context,
			final Bitmap bitmap, final AsyncTaskListener asyncTaskListener) {
		if (sAsyncTaskExecutor == null) {
			initTaskExecutor();
		}

		sAsyncTaskExecutor.submit(Tasks.BLUR_RS_PICTURE,
				new AsyncTask<Void, Void, Bitmap>() {
					@Override
					public Bitmap doInBackground(Void... params) {
						return PictureUtils.RSBlurBitmap(context, bitmap);
					}

					@Override
					public void onPostExecute(Bitmap result) {
						if (asyncTaskListener != null) {
							asyncTaskListener.onGetResultPicture(result);
						}
					}
				});
	}

	public static void getLtrMergePicture(final Context context,
			final Bitmap leftBitmap, final Bitmap rightBitmap,
			final AsyncTaskListener asyncTaskListener) {
		if (sAsyncTaskExecutor == null) {
			initTaskExecutor();
		}

		sAsyncTaskExecutor.submit(Tasks.MERGE_LTR_PICTURE,
				new AsyncTask<Void, Void, Bitmap>() {
					@Override
					public Bitmap doInBackground(Void... params) {
						return PictureUtils
								.mergeBitmapLtr(leftBitmap, rightBitmap);
					}

					@Override
					public void onPostExecute(Bitmap result) {
						if (asyncTaskListener != null) {
							asyncTaskListener.onGetResultPicture(result);
						}
					}
				});
	}

	public static void getRoundedCornerPicture(final Context context,
			final Bitmap bitmap, final AsyncTaskListener asyncTaskListener) {
		if (sAsyncTaskExecutor == null) {
			initTaskExecutor();
		}

		sAsyncTaskExecutor.submit(Tasks.ROUNDED_CORNER_PICTURE,
				new AsyncTask<Void, Void, Bitmap>() {
					@Override
					public Bitmap doInBackground(Void... params) {
						return PictureUtils.getRoundedCornerBitmap(bitmap, 90);
					}

					@Override
					public void onPostExecute(Bitmap result) {
						if (asyncTaskListener != null) {
							asyncTaskListener.onGetResultPicture(result);
						}
					}
				});
	}
}
