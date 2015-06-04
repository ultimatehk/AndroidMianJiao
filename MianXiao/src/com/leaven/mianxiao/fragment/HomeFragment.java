package com.leaven.mianxiao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.leaven.mianxiao.R;
import com.leaven.mianxiao.ScanActivity;
import com.leaven.mianxiao.pager.BasePager;
import com.leaven.mianxiao.tools.Constant;

public class HomeFragment extends BasePager.AbstractPagerFragment {

	View btnScan;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		btnScan = v.findViewById(R.id.btnScan);
		btnScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(getActivity(), ScanActivity.class);
				getActivity().startActivity(it);
			}

		});
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
		return R.drawable.selector_homebottom_tab_homepage;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_HOME;
	}
}
