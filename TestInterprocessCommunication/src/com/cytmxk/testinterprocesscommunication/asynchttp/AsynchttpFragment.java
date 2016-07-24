package com.cytmxk.testinterprocesscommunication.asynchttp;

import java.io.UnsupportedEncodingException;
import com.cytmxk.testinterprocesscommunication.R;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AsynchttpFragment extends Fragment implements View.OnClickListener {

	private Button mBtTestGet;
	private Button mBtTestPost;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_async_http, container, false);
		mBtTestGet = (Button) view.findViewById(R.id.bt_test_get);
		mBtTestPost = (Button) view.findViewById(R.id.bt_test_post);
		mBtTestGet.setOnClickListener(this);
		mBtTestPost.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_test_get:
			asynchttpGet();
			break;
		case R.id.bt_test_post:
			asynchttpPost();
			break;

		default:
			break;
		}
	}
	
	private void asynchttpGet() {
		// TODO Auto-generated method stub
		String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
		
		AsyncHttpUtils.get(url, new MyAsyncHttpResponseHandler(){

			@Override
			public void onMySuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				try {
					Toast.makeText(getContext(), "success : " + new String(arg2, "utf-8"), Toast.LENGTH_LONG).show();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onMyFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "failure : " + arg3.getMessage(), Toast.LENGTH_LONG).show();
			}});
	}

	private void asynchttpPost() {
		// TODO Auto-generated method stub
		String url = "http://apis.juhe.cn/mobile/get?";
		RequestParams params = new RequestParams();
		params.add("phone", "13429667914");
		params.add("key", "335adcc4e891ba4e4be6d7534fd54c5d");
		
		AsyncHttpUtils.post(url, params, new MyAsyncHttpResponseHandler() {
			
			@Override
			public void onMySuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				try {
					Toast.makeText(getContext(), "success : " + new String(arg2, "utf-8"), Toast.LENGTH_LONG).show();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onMyFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "failure : " + arg3.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}
}
