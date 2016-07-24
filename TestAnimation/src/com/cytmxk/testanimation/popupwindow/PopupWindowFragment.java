package com.cytmxk.testanimation.popupwindow;

import java.util.ArrayList;
import com.cytmxk.testanimation.R;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class PopupWindowFragment extends Fragment implements SlidingMenuWindow.Callbacks{

	private Button mBtShowLocationPopupWindow = null;
	private Button mBtShowDropdownPopupWindow = null;
    //自定义的弹出框类  
	SlidingMenuWindow menuWindow;
	
	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.menu_volte_conf_call:
				
				break;
			case R.id.menu_ip_dial:
				
				break;
			case R.id.menu_2s_pause:
				
				break;
			case R.id.menu_add_wait:
				
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_popupwindow, container, false);
		mBtShowLocationPopupWindow = (Button) view.findViewById(R.id.bt_show_location_popup_window);
		mBtShowDropdownPopupWindow = (Button) view.findViewById(R.id.bt_show_dropdown_popup_window);
		mBtShowLocationPopupWindow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//实例化SelectPicPopupWindow  
                menuWindow = new SlidingMenuWindow(getActivity(), PopupWindowFragment.this, mOnItemClickListener, R.style.AnimBottom);  
                //显示窗口  
                menuWindow.showAtLocation(getActivity().findViewById(R.id.fragment_popup_window), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置  
			}
		});
		mBtShowDropdownPopupWindow.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.KITKAT)
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//实例化SelectPicPopupWindow  
                menuWindow = new SlidingMenuWindow(getActivity(), PopupWindowFragment.this, mOnItemClickListener, R.style.AnimScale);  
                //显示窗口  
                menuWindow.showAsDropDown(mBtShowDropdownPopupWindow, 0, 50, Gravity.TOP | Gravity.START);
			}
		});
		return view;
	}

	@Override
	public ArrayList<String> buildPopmenuItems(String[] popmenuItems) {
		// TODO Auto-generated method stub
		return null;
	}
}
