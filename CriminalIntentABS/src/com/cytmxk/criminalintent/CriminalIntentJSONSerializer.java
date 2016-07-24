package com.cytmxk.criminalintent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import android.content.Context;

public class CriminalIntentJSONSerializer {

	private Context mContext = null;
	private String mFileName = null;

	public CriminalIntentJSONSerializer(Context mContext, String mFileName) {
		super();
		this.mContext = mContext;
		this.mFileName = mFileName;
	}

	public void saveCrimes(ArrayList<Crime> crimes) throws JSONException,
			IOException {
		JSONArray array = new JSONArray();
		for (Crime crime : crimes) {
			array.put(crime.toJSON());
		}

		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFileName,
					Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	public ArrayList<Crime> loadCrimes() throws IOException, JSONException {

		ArrayList<Crime> crimes = new ArrayList<Crime>();
		BufferedReader reader = null;

		try {
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer jsonString = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
					.nextValue();
			for (int i = 0; i < array.length(); i++) {
				crimes.add(new Crime(array.getJSONObject(i)));
			}
		} finally {
			if (null != reader) {
				reader.close();
			}
		}

		return crimes;
	}

	public void saveCrimesToExternalStorage(ArrayList<Crime> crimes)
			throws JSONException, IOException {

		JSONArray array = new JSONArray();
		for (Crime crime : crimes) {
			array.put(crime.toJSON());
		}

		Writer writer = null;
		try {
			File file = mContext.getExternalFilesDir(null);
			OutputStream out = new FileOutputStream(file.getPath() + "/"
					+ mFileName);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if (null != writer) {
				writer.close();
			}
		}
	}

	public ArrayList<Crime> loadCrimesFromExternalStorage() throws IOException,
			JSONException {
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		BufferedReader reader = null;
		try {
			File file = mContext.getExternalFilesDir(null);
			InputStream in = new FileInputStream(file.getPath() + "/"
					+ mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer jsonString = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
					.nextValue();
			for (int i = 0; i < array.length(); i++) {
				crimes.add(new Crime(array.getJSONObject(i)));
			}
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		return crimes;
	}
}
