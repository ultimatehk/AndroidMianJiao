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
import com.leaven.mianjiao.MultiTabActivity;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.bean.GoodsListItemBean;

public class GoodsListAdapter extends BaseAdapter {
	private Context mContext;
	private List<GoodsListItemBean> goodsItemList;

	public GoodsListAdapter(Context mContext, List<GoodsListItemBean> goodsItemList) {
		this.mContext = mContext;
		this.goodsItemList = goodsItemList;
	}

	private boolean isEmptyList() {
		return goodsItemList == null || goodsItemList.isEmpty();
	}

	@Override
	public int getCount() {
		return isEmptyList() ? 0 : goodsItemList.size();
	}

	@Override
	public GoodsListItemBean getItem(int position) {
		return isEmptyList() ? null : goodsItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return isEmptyList() ? 0 : goodsItemList.size();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HomeItemViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods_list_adapter, null);
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
		private View btnAddGood, imgAddGoodBg;

		public HomeItemViewHolder(View layout) {
			imgGoodImg = (ImageView) layout.findViewById(R.id.imgGoodImg);
			tvGoodName = (TextView) layout.findViewById(R.id.tvGoodName);
			tvGoodPrice = (TextView) layout.findViewById(R.id.tvGoodPrice);
			tvDistanceAddress = (TextView) layout.findViewById(R.id.tvDistanceAddress);
			btnAddGood = layout.findViewById(R.id.btnAddGood);
			imgAddGoodBg = layout.findViewById(R.id.imgAddGoodBg);
		}

		public void bindData(GoodsListItemBean data) {
			int imageSize = mContext.getResources().getDimensionPixelOffset(R.dimen.home_adapter_item_good_img_size);
			Glide.with(mContext).load(data.getImgURL()).override(imageSize, imageSize).centerCrop().into(imgGoodImg);
			tvGoodName.setText(data.getGoodName());
			tvGoodPrice.setText(data.getGoodPrice());
			tvDistanceAddress.setText(data.getDistance() + mContext.getString(R.string.backbtn_blank_text_4)
					+ data.getAddress());
			btnAddGood.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mContext instanceof MultiTabActivity) {
						int[] locations = new int[2];
						imgAddGoodBg.getLocationOnScreen(locations);
						int x = locations[0];// 获取组件当前位置的横坐标
						int y = locations[1];// 获取组件当前位置的纵坐标
						((MultiTabActivity) mContext).addGoods(x, y);
					}
				}
			});
		}
	}

}
