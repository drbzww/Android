package com.cytmxk.infomationobtain.process;

import java.util.ArrayList;
import java.util.List;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.cytmxk.infomationobtain.R;

public class ProcessFragment extends Fragment {

    private ListView mListView;
    private List<AMProcessInfo> mAmProcessInfoList;
    private ActivityManager mActivityManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_process, container, false);
        mListView = (ListView) view.findViewById(R.id.listView_am_process);
        mActivityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        mListView.setAdapter(new AMProcessAdapter(getContext(), getRunningProcessInfo()));
		return view;
	}
	
    private List<AMProcessInfo> getRunningProcessInfo() {
        mAmProcessInfoList = new ArrayList<AMProcessInfo>();

        List<ActivityManager.RunningAppProcessInfo> appProcessList =
                mActivityManager.getRunningAppProcesses();

        for (int i = 0; i < appProcessList.size(); i++) {
            ActivityManager.RunningAppProcessInfo info =
                    appProcessList.get(i);
            int pid = info.pid;
            int uid = info.uid;
            String processName = info.processName;
            int[] memoryPid = new int[]{pid};
            Debug.MemoryInfo[] memoryInfo = mActivityManager
                    .getProcessMemoryInfo(memoryPid);
            int memorySize = memoryInfo[0].getTotalPss();

            AMProcessInfo processInfo = new AMProcessInfo();
            processInfo.setPid("" + pid);
            processInfo.setUid("" + uid);
            processInfo.setMemorySize("" + memorySize);
            processInfo.setProcessName(processName);
            mAmProcessInfoList.add(processInfo);
        }
        return mAmProcessInfoList;
    }
}
