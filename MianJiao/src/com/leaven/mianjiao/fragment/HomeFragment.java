package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BaseHomeFragment;

public class HomeFragment extends BaseHomeFragment implements View.OnClickListener {
	private static String TAG = "HomeFragment";

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		v.findViewById(R.id.btnOpenSearchFragment).setOnClickListener(this);
		initView(v);
		return v;
	}

	private void initView(View layout) {
		// getFragmentManager().beginTransaction().add(R.id.swipeRefreshLayout, new
		// SwipeRefreshListFragment()).commit();
	}

	@Override
	public String getTAG() {
		return TAG;
	}

	@Override
	public boolean isWithSearchFragment() {
		return true;
	}

	@Override
	public void onClick(View v) {
		openFragment(new MoreBusinessesFragment());
	}
}
