package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.RedPacketAdapter;
import com.leaven.mianjiao.bean.RedPacketItem;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.LeftHeadLineView;

public class RedPacketFragment extends Fragment implements View.OnClickListener {
	private LeftHeadLineView headLineView;
	private GridView mGridView;
	private RedPacketAdapter adapter;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_red_packet, null);
		headLineView = (LeftHeadLineView) v.findViewById(R.id.headLineView);
		headLineView.initClickStatus(true);
		headLineView.setOnClickListener(this);
		mGridView = (GridView) v.findViewById(R.id.gridView);
		adapter = new RedPacketAdapter(getActivity(), RedPacketItem.getList());
		mGridView.setAdapter(adapter);
		notifyDataChange();
		return v;
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.headLineView:
			mGridView.setVisibility(headLineView.onClick() ? View.VISIBLE : View.GONE);
			break;
		default:
			break;
		}

	}

	private void notifyDataChange() {
		int verticalSpace = getActivity().getResources().getDimensionPixelOffset(
				R.dimen.user_center_red_packet_default_padding);
		int itemHeight = 2 * getActivity().getResources()
				.getDimensionPixelOffset(R.dimen.user_center_red_packet_height);
		int listSize = adapter.getList().size();

		int mGridViewHeight = (verticalSpace + itemHeight) * (listSize / 3 + (listSize % 3 == 0 ? 0 : 1))
				+ verticalSpace;
		mGridView.getLayoutParams().height = mGridViewHeight;
	}
}
