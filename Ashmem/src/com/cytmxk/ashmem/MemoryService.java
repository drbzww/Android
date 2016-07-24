package com.cytmxk.ashmem;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class MemoryService extends IMemoryService.Stub {

	private static final String TAG = "com.cytmxk.ashmem.MemoryService";
	
	private MemoryFile mFile = null;
	private ParcelFileDescriptor mPfd = null;
	private Class<?> clz = null;
	
	public MemoryService() {
		super();
		// TODO Auto-generated constructor stub
		
		try {
			clz = Class.forName("android.os.MemoryFile");
			Constructor<?> mConstructor = clz.getConstructor(String.class,int.class);
			mFile = (MemoryFile) mConstructor.newInstance("ashmem.txt",4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG,"e = " + e);
		}
		
		if(null == mFile){
			android.util.Log.i(TAG,"Failed to create memory file.");
		}else{
			android.util.Log.i(TAG,"Succeed to create memory file.");
		}
	}

	@Override
	public ParcelFileDescriptor getFileDescriptor() throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
			Method mMethod = clz.getDeclaredMethod("getFileDescriptor", (Class[]) null);
			FileDescriptor mFd = (FileDescriptor) mMethod.invoke(mFile, (Object[]) null);
			mPfd = ParcelFileDescriptor.dup(mFd);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG,"e = " + e.getMessage());
		}
		
		return mPfd;
	}

	@Override
	public void setValue(int val) throws RemoteException {
		// TODO Auto-generated method stub
		
		if(null == mFile){
			return;
		}
		
		byte[] buffer = new byte[4];
		buffer[0] = (byte) ((val >>> 24)&0xFF);
		buffer[1] = (byte) ((val >>> 16)&0xFF);
		buffer[2] = (byte) ((val >>> 8)&0xFF);
		buffer[3] = (byte) ((val >>> 0)&0xFF);
		
		try {
			mFile.writeBytes(buffer, 0, 0, 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG,"e = " + e.getMessage());
		}
	}

}
