package com.cytmxk.testpicture;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
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
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static Bitmap mergeBitmapTtb(Bitmap first, Bitmap second) {

		int mergeWidth = first.getWidth() >= second.getWidth() ? first
				.getWidth() : second.getWidth();
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
	 * 
	 * @param width
	 * @param height
	 * @param config
	 * @return
	 */
	public static Bitmap creatWhiteBitmap(int width, int height, Config config) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		int[] pix = new int[width * height];

		for (int y = 0; y < height; y++) {
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
	 * 
	 * @param width
	 * @param height
	 * @param config
	 * @return
	 */
	public static Bitmap createBlackBitmap(int width, int height, Config config) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, config);
		int[] pix = new int[width * height];

		for (int y = 0; y < height; y++) {
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

	private static long sRSElapsedTime = 0;
	
	public static long getRSElapsedTimeBlur() {
		return sRSElapsedTime;
	}
	
	public static void clearRSElapsedTimeBlur() {
		sRSElapsedTime = 0;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static Bitmap RSBlurBitmap(Context context, Bitmap bitmap) {

		long startMs = System.currentTimeMillis();
		// Let's create an empty bitmap with the same size of the bitmap we want
		// to blur
		Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);

		// Instantiate a new Renderscript
		RenderScript rs = RenderScript.create(context);

		// Create an Intrinsic Blur Script using the Renderscript
		ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs,
				Element.U8_4(rs));

		// Create the Allocations (in/out) with the Renderscript and the in/out
		// bitmaps
		Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
		Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

		// Set the radius of the blur
		blurScript.setRadius(25.f);

		// Perform the Renderscript
		blurScript.setInput(allIn);
		blurScript.forEach(allOut);

		// Copy the final bitmap created by the out Allocation to the outBitmap
		allOut.copyTo(outBitmap);

		// recycle the original bitmap
		bitmap.recycle();

		// After finishing everything, we destroy the Renderscript.
		rs.destroy();

		sRSElapsedTime = System.currentTimeMillis() - startMs;
		return outBitmap;

	}
	
	//相对于上面的blurBitmap方法，可以去掉对Renderscript的依赖（还有最低API版本的限制）。
	//但是可恶的是，理模糊操作竟然花费了147ms！这还不是最慢的SW模糊算法，我都不敢用高斯模糊了
	
	
	private static long sFastElapsedTime = 0;
	
	public static long getFastElapsedTimeBlur() {
		return sFastElapsedTime;
	}
	
	public static void clearFastElapsedTimeBlur() {
		sFastElapsedTime = 0;
	}
	
	public static Bitmap fastBlur(Bitmap bitmap) {
		
		long startMs = System.currentTimeMillis();
	    Bitmap overlay = Bitmap.createBitmap((int) (bitmap.getWidth()),
	            (int) (bitmap.getHeight()), Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(overlay);
	    canvas.drawBitmap(bitmap, 0, 0, null);
	    overlay = fastBlur(overlay, 25, true);
	    sFastElapsedTime = System.currentTimeMillis() - startMs;
	    return overlay;
	}
	
    private static Bitmap fastBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }
    
    /** 
     * 获取裁剪后的圆形图片 
     * @param radius半径 
     */ 
    public static Bitmap getCircleBitmap(Resources res, Bitmap bmp, int radius) {

        Bitmap squareBitmap;
        Bitmap scaledSquareBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片 
        int bmpWidth = bmp.getWidth(); 
        int bmpHeight = bmp.getHeight(); 

        if (bmpHeight > bmpWidth) {// 高大于宽 
            squareBitmap = Bitmap.createBitmap(bmp, 0, (bmpHeight - bmpWidth) / 2, bmpWidth, bmpWidth); 
        } else if (bmpHeight < bmpWidth) {// 宽大于高 
            squareBitmap = Bitmap.createBitmap(bmp, (bmpWidth - bmpHeight) / 2, 0, bmpHeight,bmpHeight); 
        } else { 
            squareBitmap = bmp; 
        } 
        if (squareBitmap.getWidth() != diameter) { 
            scaledSquareBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,diameter, true); 
        } else { 
            scaledSquareBmp = squareBitmap; 
        }

        int mScaledSquareBmpLength = scaledSquareBmp.getWidth();
        int mBorderThickness = mScaledSquareBmpLength / 18;//圆环宽度

        Bitmap output = Bitmap.createBitmap(mScaledSquareBmpLength + mBorderThickness*2, 
                mScaledSquareBmpLength + mBorderThickness*2,  
                Config.ARGB_8888); 
        Canvas canvas = new Canvas(output); 
   
        Paint paint = new Paint(); 
        Rect srcRect = new Rect(0, 0, mScaledSquareBmpLength,mScaledSquareBmpLength); 
        Rect dstRect = new Rect(mBorderThickness, mBorderThickness, mScaledSquareBmpLength + mBorderThickness,mScaledSquareBmpLength + mBorderThickness);
   
        paint.setAntiAlias(true); 
        paint.setFilterBitmap(true); 
        paint.setDither(true); 
        canvas.drawARGB(0, 0, 0, 0); 
        canvas.drawCircle(mScaledSquareBmpLength / 2 + mBorderThickness, 
                mScaledSquareBmpLength / 2 + mBorderThickness,  
                mScaledSquareBmpLength / 2, 
                paint); 
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
        canvas.drawBitmap(scaledSquareBmp, srcRect, dstRect, paint); 
        //overlapBitmap(res, canvas, R.drawable.ic_people_border, mScaledSquareBmpLength + mBorderThickness*2, mScaledSquareBmpLength + mBorderThickness*2);
        drawCircleBorder(canvas, mScaledSquareBmpLength / 2 + mBorderThickness, mBorderThickness, 0xaa0000ff);
        bmp = null; 
        squareBitmap = null; 
        scaledSquareBmp = null; 
        return output; 
    }
    
    /** 
     * 画边缘圆环
     */ 
    public static void drawCircleBorder(Canvas canvas, int radius,int borderThickness, int color) { 
        Paint paint = new Paint(); 
        paint.setAntiAlias(true); //去锯齿
        paint.setFilterBitmap(true); //对位图进行滤波处理
        paint.setDither(true); ////防抖动
        paint.setColor(color); 
        /* 设置paint的　style　为STROKE：空心 */ 
        paint.setStyle(Paint.Style.STROKE); 
        /* 设置paint的外框宽度 */ 
        paint.setStrokeWidth(borderThickness); 
        canvas.drawCircle(radius, radius, radius - borderThickness / 2, paint); 
    }  
    
    /**
	 * 在画布上重叠画上一张图片
	 * 
	 * @param canvas
	 * @param top
	 * @param x
	 * @param y
	 * @return
	 */
	public static void overlapBitmap(Resources res, Canvas canvas, int resid, int width,
			int height) {
        Bitmap scaledBmp;
		Bitmap top = BitmapFactory.decodeResource(res, resid);
        if ((top.getWidth() != width) || (top.getHeight() != height)) {
            scaledBmp = Bitmap.createScaledBitmap(top, width, height, true); 
        } else {
            scaledBmp = top; 
        }
		canvas.drawBitmap(scaledBmp, 0, 0, null); // x、y为top写入点的x、y坐标
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
}
