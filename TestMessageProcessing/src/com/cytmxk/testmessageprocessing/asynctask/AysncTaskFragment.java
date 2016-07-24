package com.cytmxk.testmessageprocessing.asynctask;

import java.util.ArrayList;
import java.util.Locale;
import com.cytmxk.testmessageprocessing.R;
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

public class AysncTaskFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ArrayList<OnPageChangeListener> mOnPageChangeListeners =
            new ArrayList<OnPageChangeListener>();
    
    private ViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private ViewPagerAdapter mViewPagerAdapter;
    private RSBlurPictureFragment mRSBlurPictureFragment;
    private LtrMergePictureFragment mLtrMergePictureFragment;
    private RoundedCornerPictureFragment mRoundedCornerPictureFragment;
    private String[] mTabTitles;
	
    public static final int TAB_COUNT_DEFAULT = 3;
    public static final int TAB_INDEX_RENDER_SCRIPT_BLUR = 0;
    public static final int TAB_INDEX_LTR_MERGE = 1;
    public static final int TAB_INDEX_ROUNDED_CORNER = 2;
    
    /**
     * The position of the currently selected tab.
     */
    private int mTabIndex = TAB_INDEX_RENDER_SCRIPT_BLUR;
	
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
                case TAB_INDEX_RENDER_SCRIPT_BLUR:
                	mRSBlurPictureFragment = new RSBlurPictureFragment();
                    return mRSBlurPictureFragment;
                case TAB_INDEX_LTR_MERGE:
                    mLtrMergePictureFragment = new LtrMergePictureFragment();
                    return mLtrMergePictureFragment;
                case TAB_INDEX_ROUNDED_CORNER:
                    mRoundedCornerPictureFragment = new RoundedCornerPictureFragment();
                    return mRoundedCornerPictureFragment;
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
            if (fragment instanceof RSBlurPictureFragment) {
                mRSBlurPictureFragment = (RSBlurPictureFragment) fragment;
            } else if (fragment instanceof LtrMergePictureFragment && position == TAB_INDEX_LTR_MERGE) {
                mLtrMergePictureFragment = (LtrMergePictureFragment) fragment;
            } else if (fragment instanceof RoundedCornerPictureFragment && position == TAB_INDEX_ROUNDED_CORNER) {
            	mRoundedCornerPictureFragment = (RoundedCornerPictureFragment) fragment;
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
		View view = inflater.inflate(R.layout.fragment_aysnc_task, container,
				false);
	
        mViewPager = (ViewPager) view.findViewById(R.id.lists_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_COUNT_DEFAULT - 1);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(getRtlPosition(TAB_INDEX_RENDER_SCRIPT_BLUR));

        mTabTitles = new String[TAB_COUNT_DEFAULT];
        mTabTitles[TAB_INDEX_RENDER_SCRIPT_BLUR] = getResources().getString(R.string.tab_render_script_blur);
        mTabTitles[TAB_INDEX_LTR_MERGE] = getResources().getString(R.string.sagereal_tab_ltr_merge);
        mTabTitles[TAB_INDEX_ROUNDED_CORNER] = getResources().getString(R.string.sagereal_tab_rouned_corner);
        
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
