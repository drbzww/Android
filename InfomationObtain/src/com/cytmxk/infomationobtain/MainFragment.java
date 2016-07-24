package com.cytmxk.infomationobtain;

import java.util.ArrayList;
import java.util.Locale;
import com.cytmxk.infomationobtain.R;
import com.cytmxk.infomationobtain.application.ApplicationFragment;
import com.cytmxk.infomationobtain.process.ProcessFragment;
import com.cytmxk.infomationobtain.system.SystemInfoFragment;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ArrayList<OnPageChangeListener> mOnPageChangeListeners =
            new ArrayList<OnPageChangeListener>();
    
    private ViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private ViewPagerAdapter mViewPagerAdapter;
    private SystemInfoFragment mSystemInfoFragment;
    private ApplicationFragment mApplicationFragment;
    private ProcessFragment mProcessFragment;
    private String[] mTabTitles;
	
    public static final int TAB_COUNT_DEFAULT = 3;
    public static final int TAB_INDEX_SYSTEM_INFO = 0;
    public static final int TAB_INDEX_APP_INFO = 1;
    public static final int TAB_INDEX_RUNNING_PROCESS_INFO = 2;
    
    /**
     * The position of the currently selected tab.
     */
    private int mTabIndex = TAB_INDEX_SYSTEM_INFO;
	
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public long getItemId(int position) {
            return getRtlPosition(position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (getRtlPosition(position)) {
                case TAB_INDEX_SYSTEM_INFO:
                	mSystemInfoFragment = new SystemInfoFragment();
                    return mSystemInfoFragment;
                case TAB_INDEX_APP_INFO:
                	mApplicationFragment = new ApplicationFragment();
                    return mApplicationFragment;
                case TAB_INDEX_RUNNING_PROCESS_INFO:
                	mProcessFragment = new ProcessFragment();
                    return mProcessFragment;
            }
            throw new IllegalStateException("No fragment at position " + position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // On rotation the FragmentManager handles rotation. Therefore getItem() isn't called.
            // Copy the fragments that the FragmentManager finds so that we can store them in
            // instance variables for later.
            final Fragment fragment =
                    (Fragment) super.instantiateItem(container, position);
            if (fragment instanceof SystemInfoFragment && position == TAB_INDEX_SYSTEM_INFO) {
                mSystemInfoFragment = (SystemInfoFragment) fragment;
            } else if (fragment instanceof ApplicationFragment && position == TAB_INDEX_APP_INFO) {
                mApplicationFragment = (ApplicationFragment) fragment;
            } else if (fragment instanceof ProcessFragment && position == TAB_INDEX_RUNNING_PROCESS_INFO) {
                mProcessFragment = (ProcessFragment) fragment;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_COUNT_DEFAULT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main, container,
				false);
	
        mViewPager = (ViewPager) view.findViewById(R.id.lists_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_COUNT_DEFAULT - 1);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(getRtlPosition(mTabIndex));

        mTabTitles = new String[TAB_COUNT_DEFAULT];
        mTabTitles[TAB_INDEX_SYSTEM_INFO] = getResources().getString(R.string.tab_system_info);
        mTabTitles[TAB_INDEX_APP_INFO] = getResources().getString(R.string.tab_app_info);
        mTabTitles[TAB_INDEX_RUNNING_PROCESS_INFO] = getResources().getString(R.string.tab_running_process_info);
        
        mViewPagerTabs = (ViewPagerTabs) view.findViewById(R.id.lists_pager_header);
        mViewPagerTabs.setViewPager(mViewPager);
        addOnPageChangeListener(mViewPagerTabs);
		return view;
	}
	
    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (!mOnPageChangeListeners.contains(onPageChangeListener)) {
            mOnPageChangeListeners.add(onPageChangeListener);
        }
    }
	
    private int getRtlPosition(int position) {
        if (isRtl()) {
            return mViewPagerAdapter.getCount() - 1 - position;
        }
        return position;
    }
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static boolean isRtl() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) ==
            View.LAYOUT_DIRECTION_RTL;
    }

	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageScrollStateChanged(state);
        }
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		// TODO Auto-generated method stub
        mTabIndex = getRtlPosition(position);

        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageScrolled(position, positionOffset,
                    positionOffsetPixels);
        }
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
        mTabIndex = getRtlPosition(position);

        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageSelected(position);
        }
		
	}
}
