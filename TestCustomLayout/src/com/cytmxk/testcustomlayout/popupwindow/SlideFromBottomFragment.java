package com.cytmxk.testcustomlayout.popupwindow;

import com.cytmxk.testcustomlayout.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SlideFromBottomFragment extends Fragment implements View.OnClickListener{

	private Button mShow = null;
	private SlideFromBottomMenu mSlideFromBottomMenu = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_slide_from_bottom, container, false);
		final View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popup_slide_from_bottom,null);
		inflate.findViewById(R.id.tx_1).setOnClickListener(this);
		inflate.findViewById(R.id.tx_2).setOnClickListener(this);
		inflate.findViewById(R.id.tx_3).setOnClickListener(this);
		mShow = (Button) view.findViewById(R.id.btn_show_slide_from_bottom);
		mShow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSlideFromBottomMenu = new SlideFromBottomMenu(getActivity(), inflate);
				mSlideFromBottomMenu.showPopupWindow();
			}
		});
		
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tx_1:
			Toast.makeText(getContext(),"R.id.tx_1",Toast.LENGTH_SHORT).show();
			mSlideFromBottomMenu.dismiss();
			break;
		case R.id.tx_2:
			Toast.makeText(getContext(),"R.id.tx_2",Toast.LENGTH_SHORT).show();
			mSlideFromBottomMenu.dismiss();
			break;
		case R.id.tx_3:
			Toast.makeText(getContext(),"R.id.tx_3",Toast.LENGTH_SHORT).show();
			mSlideFromBottomMenu.dismiss();
			break;

		default:
			break;
		}
	}
}
