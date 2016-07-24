package com.cytmxk.testinterprocesscommunication.volley;

import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cytmxk.testinterprocesscommunication.R;

public class VolleyFragment extends Fragment implements View.OnClickListener {

	private Button mBtTestGet;
	private Button mBtTestPost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.fragment_volley, container, false);
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
			volleyGet();
			break;
		case R.id.bt_test_post:
			volleyPost();
			break;

		default:
			break;
		}
	}

	private void volleyGet() {
		// TODO Auto-generated method stub
		String url = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=335adcc4e891ba4e4be6d7534fd54c5d";
		StringRequest stringRequest = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), response, Toast.LENGTH_LONG)
								.show();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_LONG)
						.show();
					}
				});
		stringRequest.setTag("volleyGet");
		MyApplication.getRequestQueue().add(stringRequest);
	}

	private void volleyPost() {
		// TODO Auto-generated method stub
		String url = "http://apis.juhe.cn/mobile/get?";
		StringRequest stringRequest = new StringRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), response, Toast.LENGTH_LONG)
								.show();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_LONG)
						.show();
					}
				}){

					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						HashMap<String, String> hashMap = new HashMap<String, String>();
						hashMap.put("phone", "13429667914");
						hashMap.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
						return hashMap;
					}
			
		};
		stringRequest.setTag("volleyPost");
		MyApplication.getRequestQueue().add(stringRequest);
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		MyApplication.getRequestQueue().cancelAll("volleyGet");
		MyApplication.getRequestQueue().cancelAll("volleyPost");
	}
}
