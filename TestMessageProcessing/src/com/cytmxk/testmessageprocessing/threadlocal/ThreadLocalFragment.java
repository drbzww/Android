package com.cytmxk.testmessageprocessing.threadlocal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThreadLocalFragment extends Fragment {

	private ThreadLocal<Boolean> mThreadLocal = new ThreadLocal<Boolean>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		testThreadLocal();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void testThreadLocal() {
		mThreadLocal.set(true);
		android.util.Log.i("chenyang", "Thread#main mThreadLocal = "
				+ mThreadLocal.get());

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mThreadLocal.set(false);
				android.util.Log.i("chenyang", "Thread#1 mThreadLocal = "
						+ mThreadLocal.get());
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				android.util.Log.i("chenyang", "Thread#2 mThreadLocal = "
						+ mThreadLocal.get());
			}
		}).start();
	}
}
