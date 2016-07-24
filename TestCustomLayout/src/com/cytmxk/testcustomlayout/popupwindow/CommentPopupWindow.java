package com.cytmxk.testcustomlayout.popupwindow;

import com.cytmxk.testcustomlayout.R;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommentPopupWindow extends BasePopupWindow implements
		View.OnClickListener {

	private ImageView mLikeAnimaView;
	private TextView mLikeText;
	private RelativeLayout mLikeClikcLayout;
	private RelativeLayout mCommentClickLayout;

	private int[] viewLocation;

	public CommentPopupWindow(Activity context) {
		this(context, ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	public CommentPopupWindow(Activity context, int w, int h) {
		super(context, w, h);

		viewLocation = new int[2];

		mLikeAnimaView = (ImageView) mPopupView.findViewById(R.id.iv_like);
		mLikeText = (TextView) mPopupView.findViewById(R.id.tv_like);

		mLikeClikcLayout = (RelativeLayout) mPopupView
				.findViewById(R.id.item_like);
		mCommentClickLayout = (RelativeLayout) mPopupView
				.findViewById(R.id.item_comment);

		mLikeClikcLayout.setOnClickListener(this);
		mCommentClickLayout.setOnClickListener(this);

	}

	public void showPopupWindow(View v) {
		try {
			// 得到v的位置
			v.getLocationOnScreen(viewLocation);
			// 展示位置：
			// 参照点为view的右上角，偏移值为：x方向距离参照view的一定倍数距离
			// 垂直方向自身减去popup自身高度的一半（确保在中间）
			mPopupWindow.showAtLocation(v, Gravity.RIGHT | Gravity.TOP,
					ScreenUtils.getScreenWidth(mContext.getWindowManager())
							- viewLocation[0] + 8, viewLocation[1] - 16);

			if (getAnimation() != null && getAnimaView() != null) {
				getAnimaView().startAnimation(getAnimation());
			}
		} catch (Exception e) {
			Log.w("error", "error");
		}
	}

	private Animation getAnimation() {
		return getScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				0.0f);
	}

	@Override
	protected View getPopupView() {
		// TODO Auto-generated method stub
		return LayoutInflater.from(mContext).inflate(R.layout.popup_comment,
				null);
	}

	@Override
	protected View getAnimaView() {
		// TODO Auto-generated method stub
		return mPopupView.findViewById(R.id.comment_popup_contianer);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
