package com.cytmxk.testanimation;

import java.util.ArrayList;
import java.util.Locale;

import com.cytmxk.testanimation.R;
import com.cytmxk.testanimation.popupwindow.PopupWindowFragment;
import com.cytmxk.testanimation.property.PropertyAnimationFragment;
import com.cytmxk.testanimation.view.ViewAnimationFragment;

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

public class TestAnimationActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ArrayList<OnPageChangeListener> mOnPageChangeListeners =
            new ArrayList<OnPageChangeListener>();
	
    private ViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private ViewPagerAdapter mViewPagerAdapter;
    private PopupWindowFragment mPopupWindowFragment;
    private ViewAnimationFragment mViewAnimationFragment;
    private PropertyAnimationFragment mPropertyAnimationFragment;
    private String[] mTabTitles;
    
    public static final int TAB_COUNT_DEFAULT = 3;
    public static final int TAB_INDEX_POPUP_WINDOW = 0;
    public static final int TAB_INDEX_VIEW_ANIMATION = 1;
    public static final int TAB_INDEX_PROPERTY_ANIMATION = 2;
    
    /**
     * The position of the currently selected tab.
     */
    private int mTabIndex = TAB_INDEX_POPUP_WINDOW;
	
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
                case TAB_INDEX_POPUP_WINDOW:
                    mPopupWindowFragment = new PopupWindowFragment();
                    return mPopupWindowFragment;
                case TAB_INDEX_VIEW_ANIMATION:
                	mViewAnimationFragment = new ViewAnimationFragment();
                    return mViewAnimationFragment;
                case TAB_INDEX_PROPERTY_ANIMATION:
                    mPropertyAnimationFragment = new PropertyAnimationFragment();
                    return mPropertyAnimationFragment;
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
            if (fragment instanceof PopupWindowFragment && position == TAB_INDEX_POPUP_WINDOW) {
                mPopupWindowFragment= (PopupWindowFragment) fragment;
            } else if (fragment instanceof ViewAnimationFragment && position == TAB_INDEX_VIEW_ANIMATION) {
            	mViewAnimationFragment= (ViewAnimationFragment) fragment;
            } else if (fragment instanceof PropertyAnimationFragment && position == TAB_INDEX_PROPERTY_ANIMATION) {
            	mPropertyAnimationFragment= (PropertyAnimationFragment) fragment;
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
		setContentView(R.layout.activity_test_animation);
		
        mViewPager = (ViewPager) findViewById(R.id.lists_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_COUNT_DEFAULT - 1);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(getRtlPosition(mTabIndex));

        mTabTitles = new String[TAB_COUNT_DEFAULT];
        mTabTitles[TAB_INDEX_POPUP_WINDOW] = getResources().getString(R.string.tab_popup_window);
        mTabTitles[TAB_INDEX_VIEW_ANIMATION] = getResources().getString(R.string.tab_view_animation);
        mTabTitles[TAB_INDEX_PROPERTY_ANIMATION] = getResources().getString(R.string.tab_property_animation);
        
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
