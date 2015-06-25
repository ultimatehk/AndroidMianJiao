package com.leaven.mianjiao.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.bean.HomeItemBean;

public class HomeAdapter extends BaseAdapter {
	private Context mContext;
	private List<HomeItemBean> homeItemList;

	public HomeAdapter(Context mContext, List<HomeItemBean> homeItemList) {
		this.mContext = mContext;
		this.homeItemList = homeItemList;
	}

	private boolean isEmptyList() {
		return homeItemList == null || homeItemList.isEmpty();
	}

	@Override
	public int getCount() {
		return isEmptyList() ? 0 : homeItemList.size();
	}

	@Override
	public HomeItemBean getItem(int position) {
		return isEmptyList() ? null : homeItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return isEmptyList() ? 0 : homeItemList.size();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HomeItemViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_adapter, null);
			holder = new HomeItemViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (HomeItemViewHolder) convertView.getTag();
		}
		holder.bindData(getItem(position));
		return convertView;
	}

	public class HomeItemViewHolder {
		private ImageView imgGoodImg;
		private TextView tvGoodName;
		private TextView tvGoodPrice;
		private TextView tvDistanceAddress;

		public HomeItemViewHolder(View layout) {
			imgGoodImg = (ImageView) layout.findViewById(R.id.imgGoodImg);
			tvGoodName = (TextView) layout.findViewById(R.id.tvGoodName);
			tvGoodPrice = (TextView) layout.findViewById(R.id.tvGoodPrice);
			tvDistanceAddress = (TextView) layout.findViewById(R.id.tvDistanceAddress);
		}

		public void bindData(HomeItemBean data) {
			int imageSize = mContext.getResources().getDimensionPixelOffset(R.dimen.home_adapter_item_good_img_size);
			Glide.with(mContext).load(data.getImgURL()).override(imageSize, imageSize).centerCrop().into(imgGoodImg);
			tvGoodName.setText(data.getGoodName());
			tvGoodPrice.setText(data.getGoodPrice());
			tvDistanceAddress.setText(data.getDistance() + mContext.getString(R.string.backbtn_blank_text_4)
					+ data.getAddress());
		}
	}

}
