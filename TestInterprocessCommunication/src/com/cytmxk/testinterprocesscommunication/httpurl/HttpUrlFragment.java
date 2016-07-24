package com.cytmxk.testinterprocesscommunication.httpurl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cytmxk.testinterprocesscommunication.R;

public class HttpUrlFragment extends Fragment implements View.OnClickListener, AsyncTaskUtil.AsyncTaskListener {

	private Button mBtTranslationGet;
	private Button mBtTranslationPost;
	private EditText mEtTranslation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragmnet_http_url, container,
				false);
		mBtTranslationGet = (Button) view.findViewById(R.id.bt_translation_get);
		mBtTranslationPost = (Button) view
				.findViewById(R.id.bt_translation_post);
		mEtTranslation = (EditText) view
				.findViewById(R.id.et_translation_string);

		mBtTranslationGet.setOnClickListener(this);
		mBtTranslationPost.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_translation_get:
			translate(Methods.METCHOD_GET, mEtTranslation.getText()
					.toString());
			break;
		case R.id.bt_translation_post:
			translate(Methods.METCHOD_POST, mEtTranslation.getText()
					.toString());
			break;

		default:
			break;
		}
	}

	private void translate(Methods identifer, String translation) {
		switch (identifer) {
		case METCHOD_GET:{
			
			String url;
			try {
				url = "http://fanyi.youdao.com/openapi.do?keyfrom=testHttp7597&key=580340150&type=data&doctype=xml&version=1.1&q=" + URLEncoder.encode(translation, "UTF-8");
				AsyncTaskUtil.getTranslationString("get", url, null, this);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case METCHOD_POST:
			String url = "http://fanyi.youdao.com/openapi.do";
			String params;
			try {
				params = "keyfrom=testHttp7597&key=580340150&type=data&doctype=xml&version=1.1&q=" + URLEncoder.encode(translation, "UTF-8");
				AsyncTaskUtil.getTranslationString("post", url, params, this);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	public enum Methods {
		METCHOD_GET, METCHOD_POST
	}

	@Override
	public void onGeTranslation(String result) {
		// TODO Auto-generated method stub
		Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
		Log.i("chenyang", result);
	}

}
