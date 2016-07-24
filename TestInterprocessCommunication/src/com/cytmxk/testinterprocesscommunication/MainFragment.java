package com.cytmxk.testinterprocesscommunication;

import com.cytmxk.testinterprocesscommunication.serializable.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment implements View.OnClickListener{

	public static final String EXTRA_LAYOUT_ID = "com.cytmxk.testinterprocesscommunication.MainFragment.LAYOUT_ID";

	private Button mBtStartOneActivity = null;
	private Button mBtStartTwoActivity = null;
	private TextView mShowUserInfo = null;
	private Button mBtReadOrWrite = null;
	private boolean isWrite = true;
	
	public static MainFragment newInstance(int layoutId) {
		MainFragment fragment = new MainFragment();
		Bundle args = new Bundle();
		args.putInt(EXTRA_LAYOUT_ID, layoutId);
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = null;
		int layoutId = -1;
		Bundle arguments = getArguments();
		if (null != arguments) {
			layoutId = getArguments().getInt(EXTRA_LAYOUT_ID, -1);
		}
        if (-1 != layoutId) {
        	view = inflater.inflate(layoutId, container, false);
        } else {
        	view = inflater.inflate(R.layout.fragment_main, container, false);
            mBtStartOneActivity = (Button) view.findViewById(R.id.bt_start_one_activity);
            mBtStartTwoActivity = (Button) view.findViewById(R.id.bt_start_two_activity);
            mShowUserInfo = (TextView) view.findViewById(R.id.tv_show_user);
            mBtReadOrWrite = (Button) view.findViewById(R.id.bt_read_or_write);
            mShowUserInfo.setText("陈阳");
            mBtStartOneActivity.setOnClickListener(this);
            mBtStartTwoActivity.setOnClickListener(this);
            mBtReadOrWrite.setOnClickListener(this);
        }
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_start_one_activity:
			startActivity(new Intent(getActivity(), OneActivity.class));
			break;
		case R.id.bt_start_two_activity:
			startActivity(new Intent(getActivity(), TwoActivity.class));
			break;
		case R.id.bt_read_or_write:
			if (isWrite) {
				User user = new User(mShowUserInfo.getText().toString(), true);
				try {
					user.writeObject(getContext(), "cache.txt");
					mShowUserInfo.setText("");
				} catch (Exception e) {
					android.util.Log.i("chenyang", e.getMessage());
				}
				mBtReadOrWrite.setText(R.string.bt_read_object);
				isWrite = false;
			} else {
				try {
					mShowUserInfo.setText(User.readObject(getContext(), "cache.txt").getUserName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mBtReadOrWrite.setText(R.string.bt_write_object);
				isWrite = true;
			}
			
			break;
			
		default:
			break;
		}
	}
}
