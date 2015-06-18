package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BasePager;
import com.leaven.mianjiao.tools.Constant;
import com.leaven.mianjiao.view.PinnedHeaderScrollView;

public class UserCenterFragment extends BasePager.AbstractPagerFragment implements
		PinnedHeaderScrollView.OnScrollViewChangeListener {
	/**
	 * 个人信息index
	 */
	private static final int INDEX_USER_INFO = 0;
	/**
	 * 我的红包index
	 */
	private static final int INDEX_RED_PACKET = 1;
	/**
	 * 关于我们index
	 */
	private static final int INDEX_ABOUT_US = 2;
	/**
	 * 历史订单index
	 */
	private static final int INDEX_ORDER_HISTORY = 3;

	private static String TAG = "UserCenterFragment";

	private OrderHistoryFragment orderHistoryFragment;

	public UserCenterFragment() {
		super();
		super.TAG = TAG;
	}

	private PinnedHeaderScrollView scrollView;
	private ViewGroup layout;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = (ViewGroup) inflater.inflate(R.layout.fragment_user_center, null);
		scrollView = (PinnedHeaderScrollView) layout.findViewById(R.id.scrollView);
		if (getActivity() instanceof FragmentActivity) {
			FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			orderHistoryFragment = new OrderHistoryFragment();
			ft.add(R.id.userInfoContainer, new UserInfoFragment());
			ft.add(R.id.redPacketContainer, new RedPacketFragment());
			ft.add(R.id.orderHistoryContainer, orderHistoryFragment);
			ft.add(R.id.aboutUsContainer, new AboutUsFragment());
			ft.commit();
		}
		scrollView.setOnScrollViewChangeListener(this);
		return layout;
	}

	@Override
	public void onSelected() {
		super.onSelected();
		// TODO 选择
	}

	@Override
	public void onReSelected() {
		// TODO 重复选择
	}

	@Override
	public int getIconResId() {
		return R.drawable.selector_homebottom_tab_user;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_USER;
	}

	@Override
	public void onScrollChanged(int index, int scrollTop) {
		switch (index) {
		case INDEX_ORDER_HISTORY:
			layout.post(new Runnable() {

				@Override
				public void run() {
					orderHistoryFragment.showHeadLineView(layout);
				}
			});

			break;
		case INDEX_USER_INFO:
		case INDEX_RED_PACKET:
		case INDEX_ABOUT_US:
			layout.post(new Runnable() {

				@Override
				public void run() {
					orderHistoryFragment.hideHeadLineView(layout);
				}
			});
			break;
		default:
			break;
		}
	}
}
