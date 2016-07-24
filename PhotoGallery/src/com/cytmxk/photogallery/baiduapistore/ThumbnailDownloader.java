package com.cytmxk.photogallery.baiduapistore;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.cytmxk.photogallery.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class ThumbnailDownloader<Token> extends HandlerThread implements
		LruCacheUtils.Callbacks<Bitmap> {

	private static final String TAG = "ThumbnailDownloader";
	private static final int MESSAGE_DOWNLOAD = 0;
	private Handler mHandler = null;
	private HashMap<Token, String> requestMap = new HashMap<Token, String>();
	private Handler mResponseHandler = null;
	private Listener<Token> mListener = null;
	private LruCacheUtils<Bitmap,ThumbnailDownloader<Token>> mLruCacheUtils = null;
	private DiskLruCacheUtils mDiskLruCacheUtils = null;
	private Context mContext = null;

	public ThumbnailDownloader(Context context, Handler responseHandler) {
		super(TAG);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mResponseHandler = responseHandler;

		mLruCacheUtils = new LruCacheUtils<Bitmap,ThumbnailDownloader<Token>>(this);
		mDiskLruCacheUtils = new DiskLruCacheUtils(context);
	}

	public interface Listener<Token> {
		public void onThumbnailDownloaded(Token token, Bitmap bitmap);
	}

	public void setListener(Listener<Token> listener) {
		this.mListener = listener;
	}

	public void queneThumbnail(Token token, String url) {
		Log.i(TAG, "Got a URL: " + url);
		requestMap.put(token, url);
		mHandler.obtainMessage(MESSAGE_DOWNLOAD, token).sendToTarget();
	}

	@Override
	protected void onLooperPrepared() {
		// TODO Auto-generated method stub
		super.onLooperPrepared();
		mHandler = new Handler(this.getLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == MESSAGE_DOWNLOAD) {
					@SuppressWarnings("unchecked")
					Token token = (Token) msg.obj;
					handleRequest(token);
				}
			}

		};
	}

	private void handleRequest(final Token token) {
		final String url = requestMap.get(token);
		if (null == url) {
			return;
		}
		try {
			Bitmap bitmap = mLruCacheUtils.get(url);
			if (bitmap == null) {//Memory Cache 中不存在
				InputStream inputStream = mDiskLruCacheUtils.getInputStream(url);
				if (null == inputStream) {//Disk Cache 中不存在
					byte[] bytes = new BaiduFetchr().geturlBytes(url);
					if (null == bytes) { // 当网上图片无法获取时，用默认图片代替，例如java.io.FileNotFoundException: http://f7.topit.me/7/96/9e/111971910036f9e967o.jpg
						bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.beauty4);
					} else {
						bitmap = PictureUtils.decodeSampledBitmapFromBytes(bytes, 240,
								240);
					}
					Log.i("chenyang", "url = " + url + ", bitmap = " + bitmap);
					if (null == bitmap) { // 当网上图片数据不完整时，用默认图片代替
						bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.beauty4);
					}
					mDiskLruCacheUtils.put(url, PictureUtils.bitmapToBytes(bitmap));
					inputStream = mDiskLruCacheUtils.getInputStream(url);
				}
				bitmap = BitmapFactory.decodeStream(inputStream);
				mLruCacheUtils.put(url, bitmap);
			}
			final Bitmap finalBitmap = bitmap;
			mResponseHandler.post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub

					if (requestMap.get(token) != url) {
						return;
					}

					requestMap.remove(token);
					mListener.onThumbnailDownloaded(token, finalBitmap);
				}
			});
			Log.i(TAG, "Bitmap Created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "	Error downloading image !", e);
			Log.i("chenyang", "e = " + e);
		}

	}

	public void cleanQueue() {
		mHandler.removeMessages(MESSAGE_DOWNLOAD);
		requestMap.clear();
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public int getBitmapSize(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19
			return bitmap.getAllocationByteCount();
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { // API12
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight(); // earlier version
	}

	@Override
	public int onGetItemSize(Bitmap value) {
		// TODO Auto-generated method stub
		return getBitmapSize(value);
	}
	
	public void diskLruCacheFlush() {
		try {
			mDiskLruCacheUtils.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
