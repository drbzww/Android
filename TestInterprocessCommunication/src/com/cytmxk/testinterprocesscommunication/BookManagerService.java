package com.cytmxk.testinterprocesscommunication;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import com.cytmxk.testinterprocesscommunication.serializable.Book;
import com.cytmxk.testinterprocesscommunication.serializable.IBookManager;
import com.cytmxk.testinterprocesscommunication.serializable.IOnNewBookArrivedListener;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

public class BookManagerService extends Service {
	
	protected static final String TAG = "BookManagerService";
	
	private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
	private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
	private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();

	private IBookManager.Stub mIBookManager = new IBookManager.Stub() {
		
		@Override
		public List<Book> getBookList() throws RemoteException {
			// TODO Auto-generated method stub
			return mBookList;
		}
		
		@Override
		public void addBook(Book book) throws RemoteException {
			// TODO Auto-generated method stub
			mBookList.add(book);
		}

		@Override
		public void registerListener(IOnNewBookArrivedListener listener)
				throws RemoteException {
			// TODO Auto-generated method stub
			mListenerList.register(listener);
			android.util.Log.i(TAG, "registerListener, listener : " + listener);
			android.util.Log.i(TAG, "registerListener, size : " + mListenerList.beginBroadcast());
			mListenerList.finishBroadcast();
		}

		@Override
		public void unregisterListener(IOnNewBookArrivedListener listener)
				throws RemoteException {
			// TODO Auto-generated method stub
			mListenerList.unregister(listener);
			android.util.Log.i(TAG, "unregisterListener, listener : " + listener);
			android.util.Log.i(TAG, "unregisterListener, size : " + mListenerList.beginBroadcast());
			mListenerList.finishBroadcast();
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mIBookManager;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mBookList.add(new Book("chenyang good boy", 1000));
		mBookList.add(new Book("liuhanling good boy", 1000));
		new Thread(new ServiceWorker()).start();
	}
	
	private class ServiceWorker implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			while (!mIsServiceDestoryed.get()) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Book newBook = new Book("Android 群英传" + (mBookList.size() + 1), 221);
				onNewBookArrived(newBook);
			}
		}

	}
	
	private void onNewBookArrived(Book book) {
		mBookList.add(book);
		final int n = mListenerList.beginBroadcast();
		for (int i = 0; i < n; i++) {
			try {
				IOnNewBookArrivedListener arrivedListener = mListenerList.getBroadcastItem(i);
				if (null != arrivedListener) {
					arrivedListener.onNewBookArrived(book);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mListenerList.finishBroadcast();
	}

}
