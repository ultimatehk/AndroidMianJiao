package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.BusinessGoodAdapter;
import com.leaven.mianjiao.bean.BusinessGoodListItemBean;
import com.leaven.mianjiao.pager.BaseHomeFragment;

public class BusinessFragment extends BaseHomeFragment {
	private static String TAG = "BusinessesFragment";
	private GridView mGridView;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_business, null);
		mGridView = (GridView) layout.findViewById(R.id.gridView);
		
		 mGridView.setAdapter(new BusinessGoodAdapter(getActivity(), BusinessGoodListItemBean.getList()));
		return layout;
	}

	@Override
	public String getTAG() {
		return TAG;
	}

	@Override
	public boolean isWithSearchFragment() {
		return false;
	}
}
