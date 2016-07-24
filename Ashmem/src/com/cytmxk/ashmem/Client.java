package com.cytmxk.ashmem;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Client extends Activity {
	
	private static final String TAG = "com.cytmxk.ashmem.Client";
	
	private EditText mEditValue = null;
	private Button mReadButton = null;
	private Button mWriteButton = null;
	private Button mClearButton = null;
	
	private IMemoryService mMemoryService = null;
	private ParcelFileDescriptor mPfd = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.client);
		IMemoryService ms = getMemoryService();
		if(null == ms){
			android.util.Log.i(TAG,"Start com.cytmxk.ashmem.Server");
			startService(new Intent("com.cytmxk.ashmem.server"));
		}else{
			android.util.Log.i(TAG,"MemoryService has started.");
		}
		
		initViews();
	}

	private IMemoryService getMemoryService() {
		
		if(mMemoryService != null) {
			return mMemoryService;
		}
		
		try {
			Class<?> clz = Class.forName("android.os.ServiceManager");
			Method mMethod = clz.getMethod("getService", String.class);
			mMemoryService = IMemoryService.Stub.asInterface((IBinder) mMethod.invoke(null, "AnonymousSharedMemory"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG,"e = " + e);
		}
		android.util.Log.i(TAG, mMemoryService != null ? "Succeed to get memeory service." : "Failed to get memory service.");
		return mMemoryService;
	}

	private void initViews() {
		mEditValue = (EditText) findViewById(R.id.edit_value);
		mReadButton = (Button) findViewById(R.id.button_read);
		mWriteButton = (Button) findViewById(R.id.button_write);
		mClearButton = (Button) findViewById(R.id.button_clear);
		
		mReadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				byte[] buffer = new byte[4];
				InputStream mIn = null;
				try {
					mPfd = mMemoryService.getFileDescriptor();
					mIn = new ParcelFileDescriptor.AutoCloseInputStream(mPfd);
					mIn.read(buffer, 0, 4);
					int val = (buffer[0] << 24) | ((buffer[1] & 0xFF) << 16) | ((buffer[2] & 0xFF) << 8) | (buffer[3] & 0xFF);
					mEditValue.setText(val + "");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					try {
						mIn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						android.util.Log.i(TAG,"e = " + e);
					}
				}
			}
		});
		
		mWriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IMemoryService ms = getMemoryService();
				try {
					ms.setValue(Integer.parseInt(mEditValue.getText().toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					android.util.Log.i(TAG,"e = " + e);
				}
			}
		});
		
		mClearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mEditValue.setText("");
			}
		});
	}
}
