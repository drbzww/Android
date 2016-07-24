/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cytmxk.testdivide.listview;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cytmxk.testdivide.IntentUtil;
import com.cytmxk.testdivide.R;

/**
 * Adapter for a ListView containing history items from the details of a call.
 */
public class ListViewAdapter extends BaseAdapter {
	/**
	 * The top element is a blank header, which is hidden under the rest of the
	 * UI.
	 */
	private static final int VIEW_TYPE_HEADER = 0;
	/** Each history item shows the detail of a call. */
	private static final int VIEW_TYPE_LIST_ITEM = 1;

    private final Context mContext;
	private final LayoutInflater mLayoutInflater;
	private final ArrayList<String> mListItemInfos;

	public ListViewAdapter(Context context, LayoutInflater layoutInflater, ArrayList<String> listItemInfos) {
        mContext = context;
		mLayoutInflater = layoutInflater;
		mListItemInfos = listItemInfos;
	}

	//下面这个方法如果返回false，就会导致Item之间的下划线消失且无法点击
	/*@Override
	public boolean isEnabled(int position) {
		// None of history will be clickable.
		return false;
	}*/

	@Override
	public int getCount() {
		return mListItemInfos.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		if (position == 0) {
			return null;
		}
		return mListItemInfos.get(position - 1);
	}

	@Override
	public long getItemId(int position) {
		if (position == 0) {
			return -1;
		}
		return position - 1;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return VIEW_TYPE_HEADER;
		}
		return VIEW_TYPE_LIST_ITEM;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == 0) {
			final View header = convertView == null ? mLayoutInflater.inflate(
					R.layout.list_header, parent, false)
					: convertView;
			return header;
		}

		// Make sure we have a valid convertView to start with
		final View result = convertView == null ? mLayoutInflater.inflate(
				R.layout.list_item, parent, false) : convertView;

		final String listItemInfo = mListItemInfos.get(position - 1);
		TextView listItemTv = (TextView) result.findViewById(R.id.list_item);
		listItemTv.setText(listItemInfo);

		LinearLayout currentItem = (LinearLayout)result;
		ImageButton currentItemBtn = (ImageButton) currentItem.getChildAt(2);
		currentItemBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                final Intent intent = IntentUtil.getSendSmsIntent(listItemInfo);
                mContext.startActivity(intent);
			}
		});
		
		return result;
	}
}
