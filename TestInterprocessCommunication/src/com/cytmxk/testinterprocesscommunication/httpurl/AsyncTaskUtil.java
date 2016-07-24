package com.cytmxk.testinterprocesscommunication.httpurl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;
import com.cytmxk.utils.common.CloseableUtils;

public class AsyncTaskUtil {

	/** The enumeration of {@link AsyncTask} objects used in this class. */
	public enum Tasks {
		TRANSLATIE_STRING
	}

	public interface AsyncTaskListener {
		public void onGeTranslation(String result);
	}

	private static AsyncTaskExecutor sAsyncTaskExecutor;

	private static void initTaskExecutor() {
		sAsyncTaskExecutor = AsyncTaskExecutors.createThreadPoolExecutor();
	}

	public static void getTranslationString(String identifer, String url,
			String parameters,
			final AsyncTaskListener asyncTaskListener) {
		if (sAsyncTaskExecutor == null) {
			initTaskExecutor();
		}

		sAsyncTaskExecutor.submit(Tasks.TRANSLATIE_STRING,
				new AsyncTask<String, Void, String>() {

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						if (params[0].equals("get")) {
							InputStream is = null;
							InputStreamReader isr = null;
							BufferedReader br = null;
							try {
								URL url = new URL(params[1]);
								HttpURLConnection conn = (HttpURLConnection) url
										.openConnection();
								conn.setRequestMethod("GET");
								//conn.setDoInput(true); //默认是true，所以可以省略
								conn.setDoOutput(true);

								is = conn.getInputStream();
								isr = new InputStreamReader(is, "UTF-8");
								br = new BufferedReader(isr);

								String line;
								StringBuilder sb = new StringBuilder();
								while ((line = br.readLine()) != null) {
									sb.append(line);
								}
								return sb.toString();

							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} finally {
								CloseableUtils.close(br);
								CloseableUtils.close(isr);
								CloseableUtils.close(is);
							}
						} else {
							InputStream is = null;
							InputStreamReader isr = null;
							BufferedReader br = null;
							OutputStream os = null;
							OutputStreamWriter osw = null;
							BufferedWriter bw = null;
							try {
								URL url = new URL(params[1]);
								HttpURLConnection conn = (HttpURLConnection) url
										.openConnection();
								conn.setRequestMethod("POST");
								//conn.setDoInput(true); //默认是true，所以可以省略
								conn.setDoOutput(true);
								
								os = conn.getOutputStream();
								osw = new OutputStreamWriter(os, "UTF-8");
								bw = new BufferedWriter(osw);
								bw.write(params[2]);
								bw.flush();

								is = conn.getInputStream();
								isr = new InputStreamReader(is, "UTF-8");
								br = new BufferedReader(isr);

								String line;
								StringBuilder sb = new StringBuilder();
								while ((line = br.readLine()) != null) {
									sb.append(line);
								}
								return sb.toString();

							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} finally {
								CloseableUtils.close(bw);
								CloseableUtils.close(osw);
								CloseableUtils.close(os);
								CloseableUtils.close(br);
								CloseableUtils.close(isr);
								CloseableUtils.close(is);
							}
						}
						return null;
					}

					@Override
					protected void onPostExecute(String result) {
						// TODO Auto-generated method stub
						super.onPostExecute(result);
						if (asyncTaskListener != null) {
							asyncTaskListener.onGeTranslation(result);
						}
					}
				}, identifer, url, parameters);
	}
}
