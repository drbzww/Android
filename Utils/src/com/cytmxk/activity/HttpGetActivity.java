package com.cytmxk.activity;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cytmxk.utils.R;
import com.cytmxk.utils.http.HttpUtils;

public class HttpGetActivity extends Activity {
	
	private static final String TAG = "com.cytmxk.activity.HttpGetActivity";
	
	private Button buttonGet = null;
	private Button buttonPost = null;
	private TextView textInformation = null;
	
	List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.http_get_activity);
		
		initDates(); 
		initViews();
		
	}

	private void initDates() {
		params.add(new BasicNameValuePair("param1", "陈阳"));  
		params.add(new BasicNameValuePair("param2", "他是一个好孩子"));
	}

	private void initViews() {
		buttonGet = (Button) findViewById(R.id.button_get);
		buttonPost = (Button) findViewById(R.id.button_post);
		textInformation = (TextView) findViewById(R.id.text_information);
		buttonGet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new HttpUtils("http://www.baidu.com",handler,params,"get")).start();
			}
		});
		buttonPost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new HttpUtils("http://www.baidu.com",handler,params,"post")).start();
			}
		});
	}
	
	private Handler handler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle bundle = msg.getData();
	        switch (msg.what) {
			case 0:
				textInformation.setText("resCode = " + bundle.getInt("resCode") + "\n"
						+ "result = " + bundle.getString("result"));
				break;
            case 1:
            	textInformation.setText("resCode = " + bundle.getInt("resCode") + "\n"
						+ "result = " + bundle.getString("result"));
				break;

			default:
				break;
			}
	    }
	};

}
