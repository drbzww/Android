package com.cytmxk.customview.test.listview;

import java.util.List;
import com.cytmxk.customview.R;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FocusListViewAdapter extends BaseAdapter {

	private List<String> mData = null;
	private int mClickedItem = 0;
	private Context mContext = null;
	
	
	public FocusListViewAdapter(Context context, List<String> data) {
		// TODO Auto-generated constructor stub
		this.mData = data;
		this.mContext = context;
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
	
	public void setClickedItem(int clickedItem) {
		this.mClickedItem = clickedItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout layout = new LinearLayout(mContext);
		layout.setGravity(Gravity.CENTER);
		if (mClickedItem == position) {
			layout.addView(getFocusView());
		} else {
			layout.addView(getNormalView(position));
		}
		return layout;
	}
	
	private View getFocusView() {
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(R.drawable.ic_launcher);
		return imageView;
	}

	private View getNormalView(int position) {
		LinearLayout layout = new LinearLayout(mContext);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		layout.setGravity(Gravity.CENTER);
		
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(R.drawable.in_icon);
		layout.addView(imageView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		TextView textView = new TextView(mContext);
		textView.setText(mData.get(position));
		layout.addView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		return layout;
	}
}
