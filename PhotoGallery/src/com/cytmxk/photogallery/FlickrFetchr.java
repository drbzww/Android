package com.cytmxk.photogallery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlickrFetchr {

	private byte[] geturlBytes(String urlSpec) throws IOException {
		
		URL url = new URL(urlSpec);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setConnectTimeout(5000);
			InputStream input = connection.getInputStream();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = input.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			input.close();
			out.close();
			return out.toByteArray();
		} finally {
			// TODO: handle exception
			connection.disconnect();
		}
	}
	
	public String getUrl(String urlSpec) throws IOException {
		// TODO Auto-generated method stub
        return new String(geturlBytes(urlSpec));
	}
}
