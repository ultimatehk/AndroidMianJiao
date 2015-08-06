package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.LeftHeadLineView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OrderHistoryFragment extends Fragment implements View.OnClickListener {
	private LeftHeadLineView headLineLayout;
	private ViewGroup layout;
	private ListView lvList;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = (ViewGroup) inflater.inflate(R.layout.fragment_order_history, null);
		headLineLayout = (LeftHeadLineView) layout.findViewById(R.id.headLineLayout);
		headLineLayout.setOnClickListener(this);
		headLineLayout.initClickStatus(true);
		lvList = (ListView) layout.findViewById(R.id.lvList);
		return layout;
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.headLineLayout:
			lvList.setVisibility(headLineLayout.onClick() ? View.GONE : View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/**
	 * 是否隐藏HeadLineView，用于方法showHeadLineView和hideHeadLineView
	 */
	private boolean isShowHeadLineView = false;

	/**
	 * 显示置顶的HeadLineView
	 * 
	 * @param viewGroup
	 *            HeadLineView的父布局
	 */
	public void showHeadLineView(ViewGroup viewGroup) {
		if (!isShowHeadLineView) {
			layout.removeView(headLineLayout);
			viewGroup.addView(headLineLayout);
			isShowHeadLineView = true;
		}
	}

	/**
	 * 隐藏置顶的HeadLineView
	 *
	 * @param viewGroup
	 *            HeadLineView的父布局
	 */
	public void hideHeadLineView(ViewGroup viewGroup) {
		if (isShowHeadLineView) {
			viewGroup.removeView(headLineLayout);
			layout.addView(headLineLayout);
			isShowHeadLineView = false;
		}
	}
}
