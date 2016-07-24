package com.cytmxk.photogallery.baiduapistore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromBytes(byte[] bytes,
			int reqWidth, int reqHeight) {
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	}

	public static byte[] rotatePicture(byte[] data, float degrees) {
		Matrix m = new Matrix();
		m.postRotate(degrees);

		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), m, true);
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

	/**
	 * Bitmap → byte[]
	 * 
	 * @param bitmap
	 * @return
	 */
	public static byte[] bitmapToBytes(Bitmap bitmap) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		boolean isSuccess = bitmap.compress(CompressFormat.JPEG, 100, output);
		byte[] bs = null;
		if (isSuccess) {
			bs = output.toByteArray();
		}
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bs;
	}

	/**
	 * byte[] → Bitmap
	 * 
	 * @param b
	 * @return
	 */
	public Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * Bitmap缩放
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	/**
	 * 通过给出的bitmap进行质量压缩
	 * 
	 * @param bitmap
	 * @param percentage
	 *            1-100
	 * @return
	 */
	public static Bitmap compressBitmap(Bitmap bitmap, int percentage) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// 通过这里改变压缩类型，其有不同的结果
		bitmap.compress(Bitmap.CompressFormat.JPEG, percentage, bos);
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		return BitmapFactory.decodeStream(bis);
	}

	/**
	 * 获取图片的byte数
	 * 
	 * @param bitmap
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static int getBitmapSize(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19
			return bitmap.getAllocationByteCount();
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { // API12
			return bitmap.getByteCount();
		}
		return bitmap.getRowBytes() * bitmap.getHeight(); // earlier version
	}

	/**
	 * 将Drawable转化为Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 获得圆角图片
	 * 
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * 获得带倒影的图片
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
				h / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
				Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * 将图片first和second重叠
	 * 
	 * @param first
	 * @param second
	 * @param x
	 * @param y
	 * @return
	 */
	public static Bitmap overlapBitmap(Bitmap first, Bitmap second, float x,
			float y) {
		Bitmap bitmap3 = Bitmap.createBitmap(first.getWidth(),
				first.getHeight(), first.getConfig());
		Canvas canvas = new Canvas(bitmap3);
		canvas.drawBitmap(first, new Matrix(), null);
		canvas.drawBitmap(second, x, y, null); // x、y为bitmap2写入点的x、y坐标
		return bitmap3;
	}

	/**
	 * 将图片first和second由左向右合并
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static Bitmap mergeBitmapLtr(Bitmap first, Bitmap second) {

		int mergeWidth = first.getWidth() + second.getWidth();
		int mergeHeight = first.getHeight() >= second.getHeight() ? first
				.getHeight() : second.getHeight();
		// 创建空得背景bitmap
		// 生成画布图像
		Bitmap resultBitmap = Bitmap.createBitmap(mergeWidth, mergeHeight,
				first.getConfig());
		Canvas canvas = new Canvas(resultBitmap);// 使用空白图片生成canvas

		// 将bmp1绘制在画布上
		Rect srcRect = new Rect(0, 0, first.getWidth(), first.getHeight());// 截取bmp1中的矩形区域
		Rect dstRect = new Rect(0, 0, first.getWidth(), first.getHeight());// bmp1在目标画布中的位置
		canvas.drawBitmap(first, srcRect, dstRect, null);

		// 将bmp2绘制在画布上
		srcRect = new Rect(0, 0, second.getWidth(), second.getHeight());// 截取bmp1中的矩形区域
		dstRect = new Rect(first.getWidth(), 0, mergeWidth, second.getHeight());// bmp2在目标画布中的位置
		canvas.drawBitmap(second, srcRect, dstRect, null);
		// 将bmp1,bmp2合并显示
		return resultBitmap;
	}
	
	/**
	 * 将图片first和second从上到下合并
	 * @param first
	 * @param second
	 * @return
	 */
	public static Bitmap mergeBitmapTtb(Bitmap first, Bitmap second) {

		int mergeWidth = first.getWidth() >= second.getWidth() ? first.getWidth() : second.getWidth();
		int mergeHeight = first.getHeight() + second.getHeight();
		// 创建空得背景bitmap
		// 生成画布图像
		Bitmap resultBitmap = Bitmap.createBitmap(mergeWidth, mergeHeight,
				first.getConfig());
		Canvas canvas = new Canvas(resultBitmap);// 使用空白图片生成canvas

		// 将bmp1绘制在画布上
		Rect srcRect = new Rect(0, 0, first.getWidth(), first.getHeight());// 截取bmp1中的矩形区域
		Rect dstRect = new Rect(0, 0, first.getWidth(), first.getHeight());// bmp1在目标画布中的位置
		canvas.drawBitmap(first, srcRect, dstRect, null);

		// 将bmp2绘制在画布上
		srcRect = new Rect(0, 0, second.getWidth(), second.getHeight());// 截取bmp1中的矩形区域
		dstRect = new Rect(0, first.getHeight(), second.getWidth(), mergeHeight);// bmp2在目标画布中的位置
		canvas.drawBitmap(second, srcRect, dstRect, null);
		// 将bmp1,bmp2合并显示
		return resultBitmap;
	}

	/**
	 * 创建纯白色位图
	 * @param width
	 * @param height
	 * @param config
	 * @return
	 */
	public static Bitmap creatWhiteBitmap(int width, int height, Config config) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		int[] pix = new int[width * height];

		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++) {
				int index = y * width + x;
				int r = ((pix[index] >> 16) & 0xff) | 0xff;
				int g = ((pix[index] >> 8) & 0xff) | 0xff;
				int b = (pix[index] & 0xff) | 0xff;
				pix[index] = 0xff000000 | (r << 16) | (g << 8) | b;
			}
		}
		bitmap.setPixels(pix, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	/**
	 * 创建纯黑色位图
	 * @param width
	 * @param height
	 * @param config
	 * @return
	 */
	public static Bitmap createBlackBitmap(int width, int height, Config config) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		int[] pix = new int[width * height];

		for (int y = 0; y < height; y++){
			for (int x = 0; x < width; x++) {
				int index = y * width + x;
				pix[index] = 0xff000000;
			}
		}
		bitmap.setPixels(pix, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 保存图片到应用的外部缓存
	 * 
	 * @param context
	 * @param bitmap
	 * @param bitName
	 * @param format
	 */
	public static void saveBitmap(Context context, Bitmap bitmap,
			String bitName, CompressFormat format) {
		// 获取图片存储路径
		File cacheDir = FileUtils.getDiskCacheDir(context, "picture");
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}
		switch (format) {
		case JPEG:
			bitName += ".jpg";
			break;
		case PNG:
			bitName += ".png";
			break;
		case WEBP:
			bitName += ".webp";
			break;

		default:
			break;
		}
		File file = new File(cacheDir, bitName);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			if (bitmap.compress(format, 100, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
