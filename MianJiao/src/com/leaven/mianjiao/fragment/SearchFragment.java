package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.MapActivity;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.ScanActivity;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.CustomToast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SearchFragment extends Fragment implements View.OnClickListener {

	private View btnScan;
	private View btnLocation;
	private View btnDistance;
	private View btnPrice;
	private EditText edtSearch;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_search, null);
		initView(v);
		return v;
	}

	private void initView(View layout) {
		btnScan = layout.findViewById(R.id.btnScan);
		btnScan.setOnClickListener(this);
		btnLocation = layout.findViewById(R.id.btnLocation);
		btnLocation.setOnClickListener(this);
		btnDistance = layout.findViewById(R.id.btnDistance);
		btnDistance.setOnClickListener(this);
		btnPrice = layout.findViewById(R.id.btnPrice);
		btnPrice.setOnClickListener(this);
		edtSearch = (EditText) layout.findViewById(R.id.edtSearch);
		edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					CommonUtils.hideSoftInputFromWindow(v);
					CustomToast.showToast(getActivity(), v.getText().toString());
					return true;
				}
				return false;
			}
		});
		initViewState();
	}

	private void initViewState() {
		switchTabState(true);
	}

	/**
	 * 切换点击最近距离以及最低价格
	 * 
	 * @param isDistanceTab
	 *            是否是最近距离标签
	 */
	private void switchTabState(boolean isDistanceTab) {
		btnDistance.setSelected(isDistanceTab);
		btnPrice.setSelected(!isDistanceTab);
		if (isDistanceTab) {
			// TODO 最近距离请求数据，显示界面
		} else {
			// TODO 最近距离请求数据，显示界面
		}
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.btnScan:
			Intent it = new Intent(getActivity(), ScanActivity.class);
			getActivity().startActivity(it);
			break;
		case R.id.btnLocation:
			// TODO 位置定位页面
			Intent it2 = new Intent(getActivity(), MapActivity.class);
			getActivity().startActivity(it2);
			break;
		case R.id.btnDistance:
		case R.id.btnPrice:
			switchTabState(v.getId() == R.id.btnDistance);
			break;
		}
	}
}
