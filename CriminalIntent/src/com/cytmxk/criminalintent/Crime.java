package com.cytmxk.criminalintent;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class Crime {

	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_DATE = "date";
	private static final String JSON_SLOVED = "sloved";
	private static final String JSON_PHOTO = "photo";
	private static final String JSON_SUSPECT = "suspect";
	
	private UUID mId = null;
	private String mTitle = "";
	private Date mDate = null;
	private boolean mSolved = false;
	private Photo mPhoto = null;
	private String mSuspect = "";
	
	public Crime() {
		super();
		// TODO Auto-generated constructor stub
		this.mId = UUID.randomUUID();
		this.mDate = new Date();
	}
	
	public Crime(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		if (json.has(JSON_TITLE)) {
			mTitle = json.getString(JSON_TITLE);
		}
		mDate = new Date(json.getLong(JSON_DATE));
		mSolved = json.getBoolean(JSON_SLOVED);
		if (json.has(JSON_PHOTO)) {
			mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));
		}
		if (json.has(JSON_SUSPECT)) {
			mSuspect = json.getString(JSON_SUSPECT);
		}
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_DATE, mDate.getTime());
		json.put(JSON_SLOVED, mSolved);
		if (null != mPhoto) {
			json.put(JSON_PHOTO, mPhoto.toJSON());
		}
		if (!TextUtils.isEmpty(mSuspect)) {
			json.put(JSON_SUSPECT, mSuspect);
		}
		return json;
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		mTitle = title;
	}
	public UUID getId() {
		return mId;
	}
	public Date getDate() {
		return mDate;
	}
	public void setDate(Date date) {
		mDate = date;
	}
	public boolean isSolved() {
		return mSolved;
	}
	public void setSolved(boolean solved) {
		mSolved = solved;
	}
	public Photo getPhoto() {
		return mPhoto;
	}
	public void setPhoto(Photo photo) {
		this.mPhoto = photo;
	}
	@Override
	public String toString() {
		return "Crime [mTitle=" + mTitle + "]";
	}

	public String getSuspect() {
		return mSuspect;
	}

	public void setSuspect(String suspect) {
		mSuspect = suspect;
	}
	
}
