package com.cytmxk.customview.test.listview;

import java.util.List;

import com.cytmxk.customview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatListViewAdapter extends BaseAdapter {

	private List<ChatItemBean> mData = null;
	private LayoutInflater mInflater = null;
	
	
	public ChatListViewAdapter(Context context, List<ChatItemBean> data) {
		// TODO Auto-generated constructor stub
		this.mData = data;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return mData.get(position).getType();
	}
	
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			if (0 == getItemViewType(position)) {
				convertView = mInflater.inflate(R.layout.chat_item_itemin, null);
				viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_in);
				viewHolder.text = (TextView) convertView.findViewById(R.id.text_in);
			} else {
				convertView = mInflater.inflate(R.layout.chat_item_itemout, null);
				viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon_out);
				viewHolder.text = (TextView) convertView.findViewById(R.id.text_out);
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.icon.setImageBitmap(mData.get(position).getIcon());
		viewHolder.text.setText(mData.get(position).getText());
		
		return convertView;
	}
	
	public class ViewHolder {
		public ImageView icon;
		public TextView text;
	}

}
