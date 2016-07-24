package com.cytmxk.customview.test.listview;

import android.graphics.Bitmap;

public class ChatItemBean {

	private int type = -1;
	private String text = null;
	private Bitmap icon = null;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

}
