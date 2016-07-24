package com.cytmxk.photogallery.baiduapistore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.cytmxk.photogallery.entry.GalleryItem;

public class BaiduFetchr {

	public static final String PREF_SEARCH_QUARY = "searchQuary";
	public static final String ENDPOINT_SHOW = "http://apis.baidu.com/tngou/gallery/news";
	public static final String ENDPOINT_SEARCH = " http://apis.baidu.com/image_search/search/search";
	
	public byte[] geturlBytes(String urlSpec) {

		InputStream input = null;
		ByteArrayOutputStream output = null;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlSpec);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);//连接服务器超时设置为5秒
			connection.setReadTimeout(10000);//图片读超时设置为10秒
			input = connection.getInputStream();
			output = new ByteArrayOutputStream();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			return output.toByteArray();
		} catch (IOException exception) {
			
		} finally {
			// TODO: handle exception
			if (null != connection) {
				connection.disconnect();
			}
			try {
				if (null != input) {
					input.close();
				}
				if (null != output) {
					output.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getUrl(String urlSpec) throws IOException {
		// TODO Auto-generated method stub
		return new String(geturlBytes(urlSpec));
	}

	public void parseItemsShow(ArrayList<GalleryItem> items, String jsonText) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONTokener(jsonText)
					.nextValue();

			if (jsonObject.has("tngou")) {
				JSONArray jsonArray = jsonObject.getJSONArray("tngou");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					items.add(new GalleryItem(jsonObject2, 1));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseItemsSearch(ArrayList<GalleryItem> items, String jsonText) {
		try {
			JSONObject jsonObject = (JSONObject) new JSONTokener(jsonText)
					.nextValue();

			if (jsonObject.has("data")) {
				JSONObject dataJsonObject = jsonObject.getJSONObject("data");
				JSONArray jsonArray = dataJsonObject.getJSONArray("ResultArray");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					items.add(new GalleryItem(jsonObject2, 2));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
