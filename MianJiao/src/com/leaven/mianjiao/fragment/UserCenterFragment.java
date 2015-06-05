package com.leaven.mianxiao.fragment;

import com.leaven.mianxiao.R;
import com.leaven.mianxiao.pager.BasePager;
import com.leaven.mianxiao.tools.Constant;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserCenterFragment extends BasePager.AbstractPagerFragment {

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_center, null);
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
		return R.drawable.selector_homebottom_tab_usercenter;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_USER;
	}
}
