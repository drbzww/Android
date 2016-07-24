package com.cytmxk.testpicture.blurpicture;

import java.util.ArrayList;
import java.util.Locale;

import com.cytmxk.testpicture.R;
import com.cytmxk.testpicture.circlepicture.CirclePicFragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.app.FragmentManager;
import android.app.Fragment;
import android.view.ViewGroup;

public class BlurPicActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ArrayList<OnPageChangeListener> mOnPageChangeListeners =
            new ArrayList<OnPageChangeListener>();
	
    private ViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private ViewPagerAdapter mViewPagerAdapter;
    private RSBlurPicFragment mRSBlurPicFragment;
    private FastBlurPicFragment mFastBlurPicFragment;
    private AlphaBlurPicFragment mAlphaBlurPicFragment;
    private CirclePicFragment mCirclePicFragment;
    private String[] mTabTitles;
    
    public static final int TAB_COUNT_DEFAULT = 4;
    public static final int TAB_INDEX_RENDER_SCRIPT_BLUR = 0;
    public static final int TAB_INDEX_FAST_BLUR = 1;
    public static final int TAB_INDEX_ALPHA_BLUR = 2;
    public static final int TAB_INDEX_CIRCLE = 3;
    
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
                    mRSBlurPicFragment = new RSBlurPicFragment();
                    return mRSBlurPicFragment;
                case TAB_INDEX_FAST_BLUR:
                    mFastBlurPicFragment = new FastBlurPicFragment();
                    return mFastBlurPicFragment;
                case TAB_INDEX_ALPHA_BLUR:
                    mAlphaBlurPicFragment = new AlphaBlurPicFragment();
                    return mAlphaBlurPicFragment;
                case TAB_INDEX_CIRCLE:
                	mCirclePicFragment = new CirclePicFragment();
                    return mCirclePicFragment;
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
            if (fragment instanceof RSBlurPicFragment) {
                mRSBlurPicFragment = (RSBlurPicFragment) fragment;
            } else if (fragment instanceof FastBlurPicFragment && position == TAB_INDEX_FAST_BLUR) {
                mFastBlurPicFragment = (FastBlurPicFragment) fragment;
            } else if (fragment instanceof AlphaBlurPicFragment && position == TAB_INDEX_ALPHA_BLUR) {
            	mAlphaBlurPicFragment = (AlphaBlurPicFragment) fragment;
            } else if (fragment instanceof CirclePicFragment && position == TAB_INDEX_CIRCLE) {
            	mCirclePicFragment = (CirclePicFragment) fragment;
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
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blur_pic);
		
        mViewPager = (ViewPager) findViewById(R.id.lists_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_COUNT_DEFAULT - 1);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(getRtlPosition(TAB_INDEX_RENDER_SCRIPT_BLUR));

        mTabTitles = new String[TAB_COUNT_DEFAULT];
        mTabTitles[TAB_INDEX_RENDER_SCRIPT_BLUR] = getResources().getString(R.string.tab_render_script_blur);
        mTabTitles[TAB_INDEX_FAST_BLUR] = getResources().getString(R.string.sagereal_tab_fast_blur);
        mTabTitles[TAB_INDEX_ALPHA_BLUR] = getResources().getString(R.string.sagereal_tab_alpha_blur);
        mTabTitles[TAB_INDEX_CIRCLE] = getResources().getString(R.string.sagereal_tab_circle);
        
        mViewPagerTabs = (ViewPagerTabs) findViewById(R.id.lists_pager_header);
        mViewPagerTabs.setViewPager(mViewPager);
        addOnPageChangeListener(mViewPagerTabs);

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
