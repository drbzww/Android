package com.cytmxk.criminalintent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CrimeCameraFragment extends Fragment {

	private static final String TAG = "CrimeCameraFragment";
	public static final String EXTRA_PHOTO_FILENAME = "com.cytmxk.criminalintent.photo_filename";

	private Camera mCamera = null;
	private SurfaceView mSurfaceView = null;
	private View mProgressContainer = null;

	public enum Orientation {
		ORIENTATION_PORTRAIT_NORMAL, ORIENTATION_PORTRAIT_INVERTED, ORIENTATION_LANDSCAPE_NORMAL, ORIENTATION_LANDSCAPE_INVERTED
	};

	private Orientation mOrientation = null;
	private OrientationEventListener mOrientationEventListener = null;

	private Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {

		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
			mProgressContainer.setVisibility(View.VISIBLE);
		}
	};
	private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			String filename = UUID.randomUUID().toString() + ".jpg";
			FileOutputStream out = null;
			boolean success = true;
			try {
				out = getActivity().openFileOutput(filename,
						Context.MODE_PRIVATE);
				out.write(PictureUtils.rotatePicture(data, mOrientation));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.i(TAG, "Error writing to file " + filename, e);
				success = false;
			} finally {
				if (null != out) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.i(TAG, "Error closing to file " + filename, e);
						success = false;
					}
				}
			}
			if (success) {
				Intent intent = new Intent();
				intent.putExtra(EXTRA_PHOTO_FILENAME, filename);
				getActivity().setResult(Activity.RESULT_OK, intent);
			} else {
				getActivity().setResult(Activity.RESULT_CANCELED);
			}
			getActivity().finish();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_crime_camera, container,
				false);
		mSurfaceView = (SurfaceView) view.findViewById(R.id.sv_crime_camera);
		
		SurfaceHolder holder = mSurfaceView.getHolder();
		/*
		 * 在运行Honeycomb之前版本的设备上，相机预览能够工作离不开setType(...)
		 * 方法以及SURFACE_TYPE_PUSH_BUFFERS常量的支持。
		 */
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				if (null != mCamera) {
					mCamera.stopPreview();
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				try {
					if (null != mCamera) {
						mCamera.setPreviewDisplay(holder);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					android.util.Log.i(TAG, "Error setting up preview display",
							e);
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				if (null == mCamera) {
					return;
				}
				Parameters parameters = mCamera.getParameters();
				Size s = getBestSupportedSize(
						parameters.getSupportedPreviewSizes(), width, height);
				parameters.setPreviewSize(s.width, s.height);
				s = getBestSupportedSize(parameters.getSupportedPictureSizes(),
						width, height);
				parameters.setPictureSize(s.width, s.height);
				mCamera.setParameters(parameters);
				try {
					mCamera.startPreview();
				} catch (Exception e) {
					// TODO: handle exception
					mCamera.release();
					mCamera = null;
				}
			}
		});
		Button takePhotoBt = (Button) view.findViewById(R.id.bt_take_photo);
		takePhotoBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (null != mCamera) {
					mCamera.takePicture(mShutterCallback, null,
							mPictureCallback);
				}
			}
		});

		mProgressContainer = view.findViewById(R.id.fl_progress_container);
		mProgressContainer.setVisibility(View.INVISIBLE);
		return view;
	}

	private Size getBestSupportedSize(List<Size> sizes, int width, int height) {
		Size bestSize = sizes.get(0);
		long largestArea = bestSize.width * bestSize.height;
		for (Size size : sizes) {
			long area = size.width * size.height;
			if (largestArea < area) {
				bestSize = size;
				largestArea = area;
			}
		}
		return bestSize;
	}

	@TargetApi(9)
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			mCamera = Camera.open(0);
		} else {
			mCamera = Camera.open();
		}

		if (null == mOrientationEventListener) {
			mOrientationEventListener = new OrientationEventListener(
					getActivity(), SensorManager.SENSOR_DELAY_NORMAL) {

				@Override
				public void onOrientationChanged(int orientation) {
					// TODO Auto-generated method stub
                    if ((orientation < 45) || (orientation >= 315)) {
                    	mOrientation = Orientation.ORIENTATION_PORTRAIT_NORMAL;
                    } else if ((orientation < 315) && (orientation >= 225)) {
                    	mOrientation = Orientation.ORIENTATION_LANDSCAPE_NORMAL;
                    } else if ((orientation < 225) && (orientation >= 135)) {
                    	mOrientation = Orientation.ORIENTATION_PORTRAIT_INVERTED;
                    } else if ((orientation < 135) && (orientation >= 45)) {
                    	mOrientation = Orientation.ORIENTATION_LANDSCAPE_INVERTED;
                    }
				}
			};
		}
		
		if (mOrientationEventListener.canDetectOrientation()) {
			mOrientationEventListener.enable();
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (null != mCamera) {
			mCamera.release();
			mCamera = null;
		}
		
		if (null != mOrientationEventListener) {
			mOrientationEventListener.disable();
		}
	}
}
