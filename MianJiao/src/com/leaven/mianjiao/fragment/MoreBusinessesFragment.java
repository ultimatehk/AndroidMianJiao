package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BaseHomeFragment;

public class MoreBusinessesFragment extends BaseHomeFragment {
	private static String TAG = "MoreBusinessesFragment";

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_order_center, null);
		return v;
	}

	@Override
	public String getTAG() {
		return TAG;
	}

	@Override
	public boolean isWithSearchFragment() {
		return true;
	}
}
