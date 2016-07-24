package com.cytmxk.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Configuration;

public class Photo {

	private String mFilename = null;
	private int mDeviceOrientation = Configuration.ORIENTATION_UNDEFINED;
	
	private static final String JSON_FILENAME = "filename";

	public Photo(String filename) {
		super();
		this.mFilename = filename;
	}
	
	public Photo(JSONObject json) throws JSONException{
		mFilename = json.getString(JSON_FILENAME);
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_FILENAME, mFilename);
		return json;
	}

	public String getFilename() {
		return mFilename;
	}
	
	public int getOrientation() {
		return mDeviceOrientation;
	}
}
