package com.leaven.mianjiao.pager;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import com.umeng.analytics.MobclickAgent;

public class BasePager extends RelativeLayout {
	private List<? extends AbstractPagerFragment> mPagerFragments;
	private int mCurrentItemIndex = 0;
	public static final String TAG = "BasePager";

	public BasePager(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public BasePager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BasePager(Context context) {
		super(context);
		init();

	}

	private void init() {
	}

	public void setPagerFragments(List<? extends AbstractPagerFragment> pagerFragments) {
		this.mPagerFragments = pagerFragments;
		if (getContext() instanceof FragmentActivity && mPagerFragments != null && !mPagerFragments.isEmpty()) {
			FragmentActivity activity = (FragmentActivity) getContext();
			FragmentManager fm = activity.getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			for (int i = 0; i < mPagerFragments.size(); i++) {
				ft.add(getId(), mPagerFragments.get(i));
			}
			ft.commit();
		}
	}

	public void setCurrentItem(int selectedIndex) {
		getItem(selectedIndex).onSelected();
		mCurrentItemIndex = selectedIndex;
		if (getContext() instanceof FragmentActivity && mPagerFragments != null && !mPagerFragments.isEmpty()) {
			FragmentActivity activity = (FragmentActivity) getContext();
			FragmentManager fm = activity.getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			for (int i = 0; i < mPagerFragments.size(); i++) {
				if (i == mCurrentItemIndex) {
					ft.show(mPagerFragments.get(i));
				} else {
					ft.hide(mPagerFragments.get(i));
				}
			}
			ft.commit();
		}
	}

	public int getCurrentItemIndex() {
		return mCurrentItemIndex;
	}

	public AbstractPagerFragment getItem(int index) {
		if (mPagerFragments == null || mPagerFragments.isEmpty()) {
			return null;
		}
		int size = mPagerFragments.size();
		if (index < 0 || index >= size) {
			return null;
		}
		return mPagerFragments.get(index);
	}

	public int getIconResId(int position) {
		return getItem(position).getIconResId();
	}

	public int getRightEdgeIconId(int position) {
		return getItem(position).getRightEdgeIconResId();
	}

	public String getPageTitle(int position) {
		return getItem(position).getPageTitle();
	}

	public int getCount() {
		return mPagerFragments == null ? 0 : mPagerFragments.size();
	}

	public static class AbstractPagerFragment extends Fragment implements ITabUiConfig {

		protected TabIndicator mTabIndicator;
		protected String TAG;

		public AbstractPagerFragment() {
			super();
		}

		protected boolean canFinish() {
			return true;
		}

		protected void goBack() {
			getActivity().finish();
		}

		protected void setTabIndicator(TabIndicator mTabIndicator) {
			this.mTabIndicator = mTabIndicator;
		}

		/**
		 * 当前fragment被选中
		 */
		public void onSelected() {
			MobclickAgent.onPageStart(TAG);
		}

		public void onSelected(Bundle extras) {
			MobclickAgent.onPageStart(TAG);
		}

		/**
		 * 当前fragment被重新选中
		 */
		public void onReSelected() {
		}

		/**
		 * 当前Fragment失去选中状态，为了记录页面访问
		 */
		public void onLoseSelected() {
			MobclickAgent.onPageEnd(TAG);
		}

		/**
		 * 返回对应的tab icon
		 * 
		 * @return
		 */
		public int getIconResId() {
			return 0;
		}

		@Override
		public int getRightEdgeIconResId() {
			return 0;
		}

		public String getPageTitle() {
			return "";
		}

		public void hideSoftInputFromWindow() {
			if (getActivity() == null) {
				return;
			}
			View v = getActivity().getCurrentFocus();
			if (v == null) {
				return;
			}
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm.isActive()) {
				imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}

		protected void notifyBottomTabStateChanged() {
			if (mTabIndicator != null) {
				mTabIndicator.notifyBottomTabChanged(this);
			}
		}
	}

	public List<? extends AbstractPagerFragment> getPagerFragments() {
		return mPagerFragments;
	}

}
