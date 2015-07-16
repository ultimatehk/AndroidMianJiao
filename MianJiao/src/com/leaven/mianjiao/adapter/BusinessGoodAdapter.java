package com.leaven.mianjiao.adapter;

import java.util.ArrayList;
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
import com.leaven.mianjiao.bean.BusinessGoodListItemBean;

public class BusinessGoodAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<BusinessGoodListItemBean> mGoodList;

	public BusinessGoodAdapter(Context mContext, ArrayList<BusinessGoodListItemBean> mGoodList) {
		this.mContext = mContext;
		this.mGoodList = mGoodList;
	}

	@Override
	public int getCount() {
		return mGoodList == null ? 0 : mGoodList.size();
	}

	@Override
	public BusinessGoodListItemBean getItem(int position) {
		return mGoodList == null || mGoodList.isEmpty() ? null : mGoodList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BusinessGoodViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_business_good_adapter, null);
			holder = new BusinessGoodViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (BusinessGoodViewHolder) convertView.getTag();
		}
		holder.bindData(getItem(position));
		return convertView;
	}

	class BusinessGoodViewHolder {

		private ImageView imgGoodImg;
		private TextView tvGoodName, tvGoodPrice;
		private View btnAddGood, imgAddGoodBg;

		public BusinessGoodViewHolder(View v) {
			imgGoodImg = (ImageView) v.findViewById(R.id.imgGoodImg);
			tvGoodName = (TextView) v.findViewById(R.id.tvGoodName);
			tvGoodPrice = (TextView) v.findViewById(R.id.tvGoodPrice);
			btnAddGood = v.findViewById(R.id.btnAddGood);
			imgAddGoodBg = v.findViewById(R.id.imgAddGoodBg);
		}

		public void bindData(final BusinessGoodListItemBean data) {
			if (data == null) {
				return;
			}
			int imageSize = mContext.getResources().getDimensionPixelOffset(R.dimen.business_good_adapter_img_size);
			Glide.with(mContext).load(data.getImgURL()).override(imageSize, imageSize).centerCrop().into(imgGoodImg);
			tvGoodName.setText(data.getGoodName());
			tvGoodPrice.setText(data.getGoodPrice());
			btnAddGood.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (mContext instanceof MultiTabActivity) {
						int[] locations = new int[2];
						imgAddGoodBg.getLocationOnScreen(locations);
						int x = locations[0];// 获取组件当前位置的横坐标
						int y = locations[1];// 获取组件当前位置的纵坐标
						((MultiTabActivity) mContext).addGoods(x, y, data);
					}
				}
			});
		}

	}
}
