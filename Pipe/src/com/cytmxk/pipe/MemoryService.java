package com.cytmxk.pipe;

import java.io.IOException;
import java.io.OutputStream;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public class MemoryService extends IMemoryService.Stub {

	private static final String TAG = "com.cytmxk.ashmem.MemoryPipeService";
	
	private ParcelFileDescriptor mReadPipe = null;
	private ParcelFileDescriptor mWritePipe = null;
	
	public MemoryService() {
		super();
		// TODO Auto-generated constructor stub
		
		try {
			ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
			mReadPipe = pipe[0];
			mWritePipe = pipe[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ParcelFileDescriptor getFileDescriptor() throws RemoteException {
		// TODO Auto-generated method stub
		
		return mReadPipe;
	}

	@Override
	public void setValue(int val) throws RemoteException {
		// TODO Auto-generated method stub
		OutputStream out = null;
		
		byte[] buffer = new byte[4];
		buffer[0] = (byte) ((val >>> 24)&0xFF);
		buffer[1] = (byte) ((val >>> 16)&0xFF);
		buffer[2] = (byte) ((val >>> 8)&0xFF);
		buffer[3] = (byte) ((val >>> 0)&0xFF);
		
		try {
			out = new ParcelFileDescriptor.AutoCloseOutputStream(mWritePipe);
			out.write(buffer, 0, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			android.util.Log.i(TAG,"e = " + e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				android.util.Log.i(TAG,"e = " + e);
			}
		}
		
	}

}
