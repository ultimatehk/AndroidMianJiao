package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.ScanActivity;
import com.leaven.mianjiao.tools.CommonUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SearchFragment extends Fragment implements View.OnClickListener {
	public static int ID_TAB_LEFT = R.id.btnSearchTabLeft;
	public static int ID_TAB_RIGHT = R.id.btnSearchTabRight;

	private View btnScan;
	private View btnLocation;
	private TextView btnSearchTabLeft, btnSearchTabRight;
	private EditText edtSearch;
	private OnClickBtnListener onClickBtnListener;
	// 定义下拉菜单
	private PopupWindow popupwindow;

	public void setOnClickBtnListener(OnClickBtnListener onClickBtnListener) {
		this.onClickBtnListener = onClickBtnListener;
	}

	public interface OnClickBtnListener {
		/**
		 * 左键和右键的ID通过本类的ID_TAB_LEFT和ID_TAB_RIGHT得到
		 * 
		 * @param view
		 *            左键和右键的View
		 */
		public abstract void onClickTab(View view);

		public abstract void onClickSearch(String keyWords);
	}

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
		btnSearchTabLeft = (TextView) layout.findViewById(R.id.btnSearchTabLeft);
		btnSearchTabLeft.setOnClickListener(this);
		btnSearchTabRight = (TextView) layout.findViewById(R.id.btnSearchTabRight);
		btnSearchTabRight.setOnClickListener(this);
		edtSearch = (EditText) layout.findViewById(R.id.edtSearch);
		edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					CommonUtils.hideSoftInputFromWindow(v);
					if (onClickBtnListener != null) {
						onClickBtnListener.onClickSearch(v.getText().toString());
					}
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
	 * @param isLeftTab
	 *            是否是左边标签
	 */
	private void switchTabState(boolean isLeftTab) {
		btnSearchTabLeft.setSelected(isLeftTab);
		btnSearchTabRight.setSelected(!isLeftTab);
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.btnScan:
			Intent it = new Intent(getActivity(), ScanActivity.class);
			it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			getActivity().startActivity(it);
			break;
		case R.id.btnLocation:
			// TODO 位置定位页面
			// Intent it2 = new Intent(getActivity(), MapActivity.class);
			// getActivity().startActivity(it2);
			if (popupwindow != null && popupwindow.isShowing()) {
				popupwindow.dismiss();
				return;
			} else {
				initmPopupWindowView();
				popupwindow.showAsDropDown(v, 0, 5);
			}

			break;
		case R.id.btnSearchTabLeft:
		case R.id.btnSearchTabRight:
			switchTabState(v.getId() == R.id.btnSearchTabLeft);
			if (onClickBtnListener != null) {
				onClickBtnListener.onClickTab(v);
			}
			break;
		}
	}

	/**
	 * 设置搜索的Tab文案
	 * 
	 * @param isAfterSearch
	 *            boolean 是否是搜索后的
	 */
	public void setAfterSearchTabText(boolean isAfterSearch) {
		btnSearchTabLeft.setText(isAfterSearch ? R.string.nearest_distance : R.string.business_recommendatio);
		btnSearchTabRight.setText(isAfterSearch ? R.string.cheapest_price : R.string.recent_update);
	}

	/**
	 * 设置自定义下拉菜单view
	 */
	@SuppressLint("InflateParams")
	public void initmPopupWindowView() {

		// 获取自定义布局文件pop.xml的视图
		Bundle b = new Bundle();
		View quickSelectLocationView = getLayoutInflater(b).inflate(R.layout.view_quick_select_location, null, false);
		// 创建PopupWindow实例,200,150分别是宽度和高度
		popupwindow = new PopupWindow(quickSelectLocationView, 250, 280);
		// 设置动画效果
		popupwindow.setAnimationStyle(R.style.AnimationFade);
		quickSelectLocationView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupwindow != null && popupwindow.isShowing()) {
					popupwindow.dismiss();
					popupwindow = null;
				} else {
					v.performClick();
				}
				return true;
			}
		});
		// Button btnLocationHome = (Button) quickSelectLocationView.findViewById(R.id.btn_location_home);
		// Button btnLocationCompany = (Button) quickSelectLocationView.findViewById(R.id.btn_location_company);
		// Button btnLocationOther = (Button) quickSelectLocationView.findViewById(R.id.btn_location_other);
	}
}
