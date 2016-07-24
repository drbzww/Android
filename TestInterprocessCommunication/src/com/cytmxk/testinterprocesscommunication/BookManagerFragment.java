package com.cytmxk.testinterprocesscommunication;

import java.util.List;

import com.cytmxk.testinterprocesscommunication.serializable.Book;
import com.cytmxk.testinterprocesscommunication.serializable.IBookManager;
import com.cytmxk.testinterprocesscommunication.serializable.IOnNewBookArrivedListener;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BookManagerFragment extends Fragment implements
		View.OnClickListener {

	private static final String TAG = "BookManagerFragment";
	private static final int MESSAGR_NEW_BOOK_ARRIVED = 1;
	
	private Button mBtBindService = null;
	private IBookManager mIBookManager = null;

	private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {

		@Override
		public void binderDied() {
			// TODO Auto-generated method stub
			android.util.Log.i(TAG, "远程Binder 死亡");
			if (null == mIBookManager) {
				return;
			}
			mIBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
			mIBookManager = null;
			// TODO：这里可以重新绑定远程Service
		}
	};
	
	private Handler mHandler = new Handler(Looper.getMainLooper()){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case MESSAGR_NEW_BOOK_ARRIVED:
				android.util.Log.i(TAG, "receive new book : " + msg.obj);
				break;

			default:
				super.handleMessage(msg);
			}
		}
	};
	
	private IOnNewBookArrivedListener.Stub mIOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
		
		@Override
		public void onNewBookArrived(Book newBook) throws RemoteException {
			// TODO Auto-generated method stub
			mHandler.obtainMessage(MESSAGR_NEW_BOOK_ARRIVED, newBook).sendToTarget();
		}
	};

	private ServiceConnection mServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			mIBookManager = null;
			android.util.Log.i(TAG, "链接断开");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mIBookManager = IBookManager.Stub.asInterface(service);
			try {
				service.linkToDeath(mDeathRecipient, 0);
				List<Book> bookList = mIBookManager.getBookList();
				android.util.Log.i(TAG, "quary book list, list type : "
						+ bookList.getClass().getCanonicalName());
				android.util.Log.i(TAG,
						"quary book list : " + bookList.toString());

				Book newBook = new Book("Android 开发艺术探索", 123);
				android.util.Log.i(TAG, "add new book : " + newBook);
				mIBookManager.addBook(newBook);
				android.util.Log.i(TAG, "quary book list : "
						+ mIBookManager.getBookList().toString());
				
				mIBookManager.registerListener(mIOnNewBookArrivedListener);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				android.util.Log.i(TAG, "设置死亡代理失败");
			}

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_book_manager, container,
				false);
		mBtBindService = (Button) view.findViewById(R.id.bt_bind_service);
		mBtBindService.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_bind_service:
			bindService();
			break;

		default:
			break;
		}
	}

	private void bindService() {
		Intent intent = new Intent(getActivity(), BookManagerService.class);
		getActivity().bindService(intent, mServiceConnection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (null != mIBookManager && mIBookManager.asBinder().isBinderAlive()) {
			try {
				mIBookManager.unregisterListener(mIOnNewBookArrivedListener);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getActivity().unbindService(mServiceConnection);
	}
}
