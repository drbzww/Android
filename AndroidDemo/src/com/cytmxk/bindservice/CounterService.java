package com.cytmxk.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

public class CounterService extends Service implements ICounterService {
	
	private CounterBinder counterBinder = new CounterBinder();
	private ICounterCallback iCounterCallback = null;
	
	private boolean stop = false;

	@Override
	public void startCounter(int val, ICounterCallback callback) {
		// TODO Auto-generated method stub
		
		iCounterCallback = callback;

		AsyncTask<Integer, Integer, Integer> asyncTask = new AsyncTask<Integer, Integer, Integer>(){

			@Override
			protected Integer doInBackground(Integer... params) {
				// TODO Auto-generated method stub
				
				Integer counter = params[0];
				stop = false;
				while(!stop){
					publishProgress(counter);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					counter ++;
				}
				
				return counter;
			}
			
			@Override
			protected void onProgressUpdate(Integer... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
				iCounterCallback.counter(values[0]);
			}
			
			@Override
			protected void onPostExecute(Integer result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				iCounterCallback.counter(result);
			}
			
		};
		asyncTask.execute(val);
	}

	@Override
	public void stopCounter() {
		// TODO Auto-generated method stub

		stop = true;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return counterBinder;
	}
	
	public class CounterBinder extends Binder{
		public ICounterService getService(){
			return CounterService.this;
		} 
	}

}
