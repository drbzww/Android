package com.cytmxk.testcustomlayout.popupwindow;


import com.cytmxk.testcustomlayout.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CommentPopupFragment extends Fragment implements View.OnClickListener {

	ImageView mIvShowPopup = null;
	private CommentPopupWindow mCommentPopupWindow = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_comment_popup, container, false);
		mIvShowPopup = (ImageView) view.findViewById(R.id.show_popup_window);
		mIvShowPopup.setOnClickListener(this);
		mCommentPopupWindow = new CommentPopupWindow(getActivity());
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.show_popup_window:
			mCommentPopupWindow.showPopupWindow(v);
			break;

		default:
			break;
		}
	}
}
