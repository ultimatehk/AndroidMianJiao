package com.leaven.mianjiao.fragment;

import java.util.ArrayList;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.HeadLineView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class OrderHistoryFragment extends Fragment implements View.OnClickListener {
	private HeadLineView headLineLayout;
	private ViewGroup layout;
	private ListView lvList;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = (ViewGroup) inflater.inflate(R.layout.fragment_order_history, null);
		headLineLayout = (HeadLineView) layout.findViewById(R.id.headLineLayout);
		headLineLayout.setOnClickListener(this);
		lvList = (ListView) layout.findViewById(R.id.lvList);
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, getList("初始化"));
		lvList.setAdapter(adapter);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 3500);
		lp.setMargins(0, getActivity().getResources().getDimensionPixelOffset(R.dimen.head_line_height), 0, 0);
		lvList.setLayoutParams(lp);
		return layout;
	}

	public ArrayList<String> getList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			list.add(str + i);
		}
		return list;
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.headLineLayout:
			lvList.setVisibility(lvList.isShown() ? View.GONE : View.VISIBLE);
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
