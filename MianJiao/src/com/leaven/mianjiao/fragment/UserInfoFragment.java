package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.LeftHeadLineView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserInfoFragment extends Fragment implements View.OnClickListener {
	private LeftHeadLineView headLineView;
	private View userInfoContentLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_user_info, null);
		headLineView = (LeftHeadLineView) v.findViewById(R.id.leftHeadLineView);
		userInfoContentLayout = v.findViewById(R.id.userInfoContentLayout);
		headLineView.setOnClickListener(this);
		headLineView.initClickStatus(true);
		userInfoContentLayout.setVisibility(View.VISIBLE);
		return v;
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.leftHeadLineView:
			userInfoContentLayout.setVisibility(headLineView.onClick() ? View.VISIBLE : View.GONE);
			break;
		default:
			break;
		}
	}
}
