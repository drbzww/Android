package com.cytmxk.customview;

import java.util.ArrayList;
import java.util.Locale;
import com.cytmxk.customview.roundedimageview.example.RoundedFragment;
import com.cytmxk.customview.roundedimageview.example.ViewPagerTabs;
import com.cytmxk.customview.test.listview.ChatFragment;
import com.cytmxk.customview.test.listview.FocusFragment;
import com.cytmxk.customview.test.listview.PullFragment;
import com.cytmxk.customview.test.listview.ViewHolderFragment;
import com.cytmxk.customview.test.scroll.ScrollListFragment;
import com.cytmxk.customview.test.shader.ShaderViewFragment;
import com.cytmxk.customview.test.textview.TextViewFragment;
import com.cytmxk.customview.topbar.TopbarFragment;
import com.cytmxk.customview.view.ViewFragment;
import com.cytmxk.customview.viewgroup.ViewGroupFragment;
import com.cytmxk.utils.common.DisplayUtils;
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

public class CustomViewActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ArrayList<OnPageChangeListener> mOnPageChangeListeners =
            new ArrayList<OnPageChangeListener>();
	
    private ViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private ViewPagerAdapter mViewPagerAdapter;
    private RoundedFragment mRoundedFragment;
    private TextViewFragment mTextViewFragment;
    private ShaderViewFragment mShaderViewFragment;
    private TopbarFragment mTopbarFragment;
    private ViewFragment mViewFragment;
    private ViewGroupFragment mViewGroupFragment;
    private ViewHolderFragment mViewHolderFragment;
    private PullFragment mPullFragment;
    private ChatFragment mChatFragment;
    private FocusFragment mFocusFragment;
    private ScrollListFragment mScrollFragment;
    private String[] mTabTitles;
    
    public static final int TAB_COUNT_DEFAULT = 11;
    public static final int TAB_INDEX_ROUNDED = 0;
    public static final int TAB_INDEX_TEXT_VIEW = 1;
    public static final int TAB_INDEX_SHADER_VIEW = 2;
    public static final int TAB_INDEX_TOP_BAR = 3;
    public static final int TAB_INDEX_VIEW = 4;
    public static final int TAB_INDEX_VIEW_GROUP = 5;
    public static final int TAB_INDEX_VIEW_HOLDER = 6;
    public static final int TAB_INDEX_PULL_LISTVIEW = 7;
    public static final int TAB_INDEX_CHAT = 8;
    public static final int TAB_INDEX_FOCUS = 9;
    public static final int TAB_INDEX_SCROLL = 10;
    
    /**
     * The position of the currently selected tab.
     */
    private int mTabIndex = TAB_INDEX_ROUNDED;
	
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
                case TAB_INDEX_ROUNDED:
                	mRoundedFragment = new RoundedFragment();
                    return mRoundedFragment;
                case TAB_INDEX_TEXT_VIEW:
                	mTextViewFragment = new TextViewFragment();
                    return mTextViewFragment;
                case TAB_INDEX_SHADER_VIEW:
                	mShaderViewFragment = new ShaderViewFragment();
                    return mShaderViewFragment;
                case TAB_INDEX_TOP_BAR:
                	mTopbarFragment = new TopbarFragment();
                    return mTopbarFragment;
                case TAB_INDEX_VIEW:
                	mViewFragment = new ViewFragment();
                    return mViewFragment;
                case TAB_INDEX_VIEW_GROUP:
                	mViewGroupFragment = new ViewGroupFragment();
                    return mViewGroupFragment;
                case TAB_INDEX_VIEW_HOLDER:
                	mViewHolderFragment = new ViewHolderFragment();
                    return mViewHolderFragment;
                case TAB_INDEX_PULL_LISTVIEW:
                	mPullFragment = new PullFragment();
                    return mPullFragment;
                case TAB_INDEX_CHAT:
                	mChatFragment = new ChatFragment();
                    return mChatFragment;
                case TAB_INDEX_FOCUS:
                	mFocusFragment = new FocusFragment();
                    return mFocusFragment;
                case TAB_INDEX_SCROLL:
                	mScrollFragment = new ScrollListFragment();
                    return mScrollFragment;
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
            if (fragment instanceof RoundedFragment && position == TAB_INDEX_ROUNDED) {
                mRoundedFragment = (RoundedFragment) fragment;
            } else if (fragment instanceof TextViewFragment && position == TAB_INDEX_TEXT_VIEW) {
                mTextViewFragment = (TextViewFragment) fragment;
            } else if (fragment instanceof ShaderViewFragment && position == TAB_INDEX_SHADER_VIEW) {
                mShaderViewFragment = (ShaderViewFragment) fragment;
            } else if (fragment instanceof TopbarFragment && position == TAB_INDEX_TOP_BAR) {
                mTopbarFragment = (TopbarFragment) fragment;
            } else if (fragment instanceof ViewFragment && position == TAB_INDEX_VIEW) {
            	mViewFragment = (ViewFragment) fragment;
            } else if (fragment instanceof ViewGroupFragment && position == TAB_INDEX_VIEW_GROUP) {
            	mViewGroupFragment = (ViewGroupFragment) fragment;
            } else if (fragment instanceof ViewHolderFragment && position == TAB_INDEX_VIEW_HOLDER) {
            	mViewHolderFragment = (ViewHolderFragment) fragment;
            } else if (fragment instanceof PullFragment && position == TAB_INDEX_PULL_LISTVIEW) {
            	mPullFragment = (PullFragment) fragment;
            } else if (fragment instanceof ChatFragment && position == TAB_INDEX_CHAT) {
            	mChatFragment = (ChatFragment) fragment;
            } else if (fragment instanceof FocusFragment && position == TAB_INDEX_FOCUS) {
            	mFocusFragment = (FocusFragment) fragment;
            } else if (fragment instanceof ScrollListFragment && position == TAB_INDEX_SCROLL) {
            	mScrollFragment = (ScrollListFragment) fragment;
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
		setContentView(R.layout.activity_custom_view);
		
        mViewPager = (ViewPager) findViewById(R.id.lists_pager);
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_COUNT_DEFAULT - 1);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(getRtlPosition(mTabIndex));

        mTabTitles = new String[TAB_COUNT_DEFAULT];
        mTabTitles[TAB_INDEX_ROUNDED] = getResources().getString(R.string.tab_rounded);
        mTabTitles[TAB_INDEX_TEXT_VIEW] = getResources().getString(R.string.tab_text_view);
        mTabTitles[TAB_INDEX_SHADER_VIEW] = getResources().getString(R.string.tab_shader_view);
        mTabTitles[TAB_INDEX_TOP_BAR] = getResources().getString(R.string.tab_top_bar);
        mTabTitles[TAB_INDEX_VIEW] = getResources().getString(R.string.tab_view);
        mTabTitles[TAB_INDEX_VIEW_GROUP] = getResources().getString(R.string.tab_view_group);
        mTabTitles[TAB_INDEX_VIEW_HOLDER] = getResources().getString(R.string.tab_view_holder);
        mTabTitles[TAB_INDEX_PULL_LISTVIEW] = getResources().getString(R.string.tab_pull_listview);
        mTabTitles[TAB_INDEX_CHAT] = getResources().getString(R.string.tab_chat_listview);
        mTabTitles[TAB_INDEX_FOCUS] = getResources().getString(R.string.tab_focus_listview);
        mTabTitles[TAB_INDEX_SCROLL] = getResources().getString(R.string.tab_scroll);
        
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
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			android.util.Log.i("chenyang",
					"2Area one  Width: " + DisplayUtils.getScreenWidth(this)
							+ "  Height: " + DisplayUtils.getScreenHeight(this));
			android.util.Log.i("chenyang",
					"2Area two  Width: " + DisplayUtils.getApplicationArea(this).width()
							+ "  Height: " + DisplayUtils.getApplicationArea(this).height()
							+ "  status bar height : " + DisplayUtils.getStatusBarHeight(this));
			android.util.Log.i("chenyang",
					"2Area three  Width: " + DisplayUtils.getViewArea(this).width()
							+ "  Height: " + DisplayUtils.getViewArea(this).height()
							+ "  action bar height : " + DisplayUtils.getActionbarHeight(this));
		}
	}
}
