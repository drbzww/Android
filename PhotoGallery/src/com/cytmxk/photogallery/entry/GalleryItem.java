package com.cytmxk.photogallery.entry;

import org.json.JSONException;
import org.json.JSONObject;

public class GalleryItem {

	private static final String SHOW_JSON_CAPTION = "title";
	private static final String SHOW_JSON_ID = "id";
	private static final String SHOW_JSON_URL = "img";
	
	private static final String SEARCH_JSON_CAPTION = "Desc";
	private static final String SEARCH_JSON_ID = "Di";
	private static final String SEARCH_JSON_URL = "ObjUrl";
	
	private String mCaption = "";
	private String mId = "";
	private String mUrl = "";
	
	public GalleryItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GalleryItem(JSONObject json, int flag) throws JSONException {
		super();
		// TODO Auto-generated constructor stub
		if (1 == flag) {
			if (json.has(SHOW_JSON_CAPTION)) {
				this.mCaption = json.getString(SHOW_JSON_CAPTION);
			}
			if (json.has(SHOW_JSON_ID)) {
				this.mId = json.getString(SHOW_JSON_ID);
			}
			if (json.has(SHOW_JSON_URL)) {
				this.mUrl = "http://tnfs.tngou.net/image" + json.getString(SHOW_JSON_URL);
			}
		} else if (2 == flag) {
			if (json.has(SEARCH_JSON_CAPTION)) {
				this.mCaption = json.getString(SEARCH_JSON_CAPTION);
			}
			if (json.has(SEARCH_JSON_ID)) {
				this.mId = json.getString(SEARCH_JSON_ID);
			}
			if (json.has(SEARCH_JSON_URL)) {
				this.mUrl = json.getString(SEARCH_JSON_URL);
			}
		}
	}
	public String getmCaption() {
		return mCaption;
	}
	public void setmCaption(String mCaption) {
		this.mCaption = mCaption;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmUrl() {
		return mUrl;
	}
	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}
	@Override
	public String toString() {
		return mCaption + mUrl;
	}
}
