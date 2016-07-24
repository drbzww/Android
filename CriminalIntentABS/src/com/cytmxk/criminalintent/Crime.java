package com.cytmxk.criminalintent;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Crime {

	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_DATE = "date";
	private static final String JSON_SLOVED = "sloved";
	
	private UUID mId = null;
	private String mTitle = "";
	private Date mDate = null;
	private boolean mSolved = false;
	
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
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_DATE, mDate.getTime());
		json.put(JSON_SLOVED, mSolved);
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
	@Override
	public String toString() {
		return "Crime [mTitle=" + mTitle + "]";
	}
	
}