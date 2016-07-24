package com.cytmxk.customview.test.listview;

import java.util.ArrayList;
import java.util.List;

import com.cytmxk.customview.R;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ChatFragment extends Fragment {
	
	private ListView mListView = null;
	private ChatListViewAdapter mChatListViewAdapter = null;
	List<ChatItemBean> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_chat, container, false);
		ChatItemBean bean1 = new ChatItemBean();
        bean1.setType(0);
        bean1.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.in_icon));
        bean1.setText("Hello how are you?");

        ChatItemBean bean2 = new ChatItemBean();
        bean2.setType(1);
        bean2.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher));
        bean2.setText("Fine thank you, and you?");

        ChatItemBean bean3 = new ChatItemBean();
        bean3.setType(0);
        bean3.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.in_icon));
        bean3.setText("I am fine too");

        ChatItemBean bean4 = new ChatItemBean();
        bean4.setType(1);
        bean4.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher));
        bean4.setText("Bye bye");

        ChatItemBean bean5 = new ChatItemBean();
        bean5.setType(0);
        bean5.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.in_icon));
        bean5.setText("See you");
        
        mData = new ArrayList<ChatItemBean>();
        mData.add(bean1);
        mData.add(bean2);
        mData.add(bean3);
        mData.add(bean4);
        mData.add(bean5);
		mChatListViewAdapter = new ChatListViewAdapter(getActivity(), mData);
		mListView = (ListView) view.findViewById(R.id.listView_chat);
		mListView.setAdapter(mChatListViewAdapter);
		return view;
	}
}
