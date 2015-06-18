package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BasePager;
import com.leaven.mianjiao.tools.Constant;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OrderCenterFragment extends BasePager.AbstractPagerFragment {
	private static String TAG = "订单中心";

	public OrderCenterFragment() {
		super();
		super.TAG = TAG;
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_order_center, null);
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
		return R.drawable.selector_homebottom_tab_order;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_ORDER;
	}
}
