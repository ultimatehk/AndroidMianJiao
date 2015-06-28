package com.leaven.mianjiao.adapter;

import java.util.List;
import com.bumptech.glide.Glide;
import com.leaven.mianjiao.MultiTabActivity;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.bean.MoreBusinessItem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreBusinessesAdapter extends BaseAdapter {

	private Context mContext;
	private List<MoreBusinessItem> moreBusinessesItemList;

	public MoreBusinessesAdapter(Context mContext, List<MoreBusinessItem> moreBusinessesItemList) {
		this.mContext = mContext;
		this.moreBusinessesItemList = moreBusinessesItemList;
	}

	private boolean isEmptyList() {
		return moreBusinessesItemList == null || moreBusinessesItemList.isEmpty();
	}

	@Override
	public int getCount() {
		return isEmptyList() ? 0 : moreBusinessesItemList.size();
	}

	@Override
	public MoreBusinessItem getItem(int position) {
		return isEmptyList() ? null : moreBusinessesItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return isEmptyList() ? 0 : moreBusinessesItemList.size();
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MoreBusinessesItemViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_more_businesses_adapter, null);
			holder = new MoreBusinessesItemViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (MoreBusinessesItemViewHolder) convertView.getTag();
		}
		holder.bindData(getItem(position));
		return convertView;
	}

	public class MoreBusinessesItemViewHolder {
		private ImageView imgBusinessIcon;
		private TextView tvBusinessname;
		private TextView tvGoodPrice;
		private TextView tvDistance;
		private View btnAddGood, imgAddGoodBg, dashLine;

		@SuppressLint("NewApi")
		public MoreBusinessesItemViewHolder(View layout) {
			imgBusinessIcon = (ImageView) layout.findViewById(R.id.imgBusinessIcon);
			tvBusinessname = (TextView) layout.findViewById(R.id.tvBusinessname);
			tvGoodPrice = (TextView) layout.findViewById(R.id.tvGoodPrice);
			tvDistance = (TextView) layout.findViewById(R.id.tvDistance);
			btnAddGood = layout.findViewById(R.id.btnAddGood);
			imgAddGoodBg = layout.findViewById(R.id.imgAddGoodBg);
			dashLine = layout.findViewById(R.id.dashLine);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				dashLine.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			}
		}

		public void bindData(MoreBusinessItem data) {
			int imageSize = mContext.getResources().getDimensionPixelOffset(
					R.dimen.more_business_adapter_item_icon_size);
			Glide.with(mContext).load(data.getBusinessIcon()).override(imageSize, imageSize).centerCrop()
					.into(imgBusinessIcon);
			tvBusinessname.setText(data.getBusinessName());
			tvGoodPrice.setText(data.getGoodPrice());
			tvDistance.setText(data.getDistance());
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
