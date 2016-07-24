package com.cytmxk.infomationobtain.system;

import com.cytmxk.infomationobtain.R;
import com.cytmxk.utils.common.SystemInfoUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SystemInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_system_info, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        String systemInfoStr = SystemInfoUtils.getBuildInfo();
        systemInfoStr += "-------------------------------------\r\n";
        systemInfoStr += SystemInfoUtils.getSystemPropertyInfo();
        textView.setText(systemInfoStr);
		return view;
	}
}
