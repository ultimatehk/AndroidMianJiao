package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BasePager;
import com.leaven.mianjiao.tools.Constant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserCenterFragment extends BasePager.AbstractPagerFragment {

	private static String TAG = "个人中心";

	public UserCenterFragment() {
		super();
		super.TAG = TAG;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_center, null);
		if (getActivity() instanceof FragmentActivity) {
			FragmentManager fm = ((FragmentActivity) getActivity()).getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.userInfoContainer, new UserInfoFragment());
			ft.add(R.id.redPacketContainer, new RedPacketFragment());
			ft.add(R.id.deliveryAddressContainer, new DeliveryAddressFragment());
			ft.add(R.id.orderHistoryContainer, new OrderHistoryFragment());
			ft.add(R.id.goodReputationContainer, new GoodReputationFragment());
			ft.add(R.id.aboutUsContainer, new AboutUsFragment());
			ft.commit();
		}
		return v;
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
		return R.drawable.selector_homebottom_tab_usercenter;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_USER;
	}
}
