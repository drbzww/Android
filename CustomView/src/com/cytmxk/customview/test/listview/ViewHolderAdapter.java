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

public class ViewHolderAdapter extends BaseAdapter {

	private List<String> mData = null;
	private LayoutInflater mInflater = null;
	
	
	public ViewHolderAdapter(Context context, List<String> data) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (null == convertView) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.viewholder_item, null);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_img);
			viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.img.setBackgroundResource(R.drawable.ic_launcher);
		viewHolder.title.setText(mData.get(position));
		
		return convertView;
	}
	
	public class ViewHolder {
		public ImageView img;
		public TextView title;
	}

}
