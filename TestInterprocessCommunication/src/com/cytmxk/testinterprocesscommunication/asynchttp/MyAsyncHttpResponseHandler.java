package com.cytmxk.testinterprocesscommunication.asynchttp;

import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

public abstract class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

	@Override
	public void onStart() {
		// called before request is started
		super.onStart();
	}
	
	@Override
	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
		// called when response HTTP status is "4XX" (eg. 401, 403, 404)
		onMyFailure(arg0, arg1, arg2, arg3);
	}

	@Override
	public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
		// called when response HTTP status is "200 OK"
		onMySuccess(arg0, arg1, arg2);
	}
	
	@Override
	public void onRetry(int retryNo) {
		// called when request is retried
		super.onRetry(retryNo);
	}
	
	public abstract void onMySuccess(int arg0, Header[] arg1, byte[] arg2);
	public abstract void onMyFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3);

}
