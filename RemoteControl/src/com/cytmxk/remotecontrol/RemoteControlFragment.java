package com.cytmxk.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RemoteControlFragment extends Fragment {

	private TextView mTvSelectedChannel = null;
	private TextView mTvEnterChannel = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_remote_control,
				container, false);
		mTvSelectedChannel = (TextView) view
				.findViewById(R.id.tv_selected_channel);
		mTvEnterChannel = (TextView) view.findViewById(R.id.tv_enter_channel);

		View.OnClickListener numberClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String press = ((TextView) v).getText().toString();
				String enterchannel = mTvEnterChannel.getText().toString();
				if ("0".equals(enterchannel)) {
					mTvEnterChannel.setText(press);
				} else {
					mTvEnterChannel.setText(enterchannel + press);
				}
			}
		};

		int number = 1;
		TableLayout tl = (TableLayout) view
				.findViewById(R.id.tl_fragment_remote_control);
		for (int i = 2; i < tl.getChildCount() - 1; i++) {
			TableRow tr = (TableRow) tl.getChildAt(i);
			for (int j = 0; j < tr.getChildCount(); j++) {
				Button btNumber = (Button) tr.getChildAt(j);
				btNumber.setText("" + number);
				btNumber.setOnClickListener(numberClickListener);
				number++;
			}
		}

		TableRow trLast = (TableRow) tl.getChildAt(tl.getChildCount() - 1);
		Button btDelete = (Button) trLast.getChildAt(0);
		btDelete.setText("Delete");
		btDelete.setTextAppearance(getActivity(), R.style.RemoteButton_OperateButton);
		btDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!"0".equals(mTvEnterChannel.getText().toString())) {
					mTvEnterChannel.setText("0");
				}
			}
		});
		Button btZero = (Button) trLast.getChildAt(1);
		btZero.setText("0");
		btZero.setOnClickListener(numberClickListener);
		Button btEnter = (Button) trLast.getChildAt(2);
		btEnter.setText("Enter");
		btEnter.setTextAppearance(getActivity(), R.style.RemoteButton_OperateButton);
		btEnter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String enterchannel = mTvEnterChannel.getText().toString();
				if (enterchannel.length() > 0) {
					mTvSelectedChannel.setText(enterchannel);
					mTvEnterChannel.setText("0");
				}
			}
		});
		return view;
	}
}
