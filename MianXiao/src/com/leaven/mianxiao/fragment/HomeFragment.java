package com.leaven.mianxiao.fragment;

import com.leaven.mianxiao.R;
import com.leaven.mianxiao.pager.BasePager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends BasePager.AbstractPagerFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		return v;
	}

	@Override
	public void onSelected() {
		// TODO 选择
	}

	@Override
	public void onReSelected() {
		// TODO 重复选择
	}

	@Override
	public int getIconResId() {
		return R.drawable.ic_launcher;
	}

	@Override
	public String getPageTitle() {
		return "我的订单";
	}
}
