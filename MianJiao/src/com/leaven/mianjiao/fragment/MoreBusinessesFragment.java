package com.leaven.mianjiao.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.bumptech.glide.Glide;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.MoreBusinessesAdapter;
import com.leaven.mianjiao.bean.GoodsListItemBean;
import com.leaven.mianjiao.bean.MoreBusinessItem;
import com.leaven.mianjiao.pager.BaseHomeFragment;

public class MoreBusinessesFragment extends BaseHomeFragment implements OnItemClickListener {
	private static final int MSG_WHAT_SET_ADAPTER = 0;
	private static String TAG = "MoreBusinessesFragment";
	private static GoodsListItemBean goodListItemBean;

	private GoodViewHolder holder;
	private ListView mlistView;

	private List<MoreBusinessItem> list;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (getActivity() != null && !getActivity().isFinishing()) {
				switch (msg.what) {
				case MSG_WHAT_SET_ADAPTER:
					list = MoreBusinessItem.getList();
					bindData(list);
					break;
				default:
					break;
				}
			}
		};
	};

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_more_businesses, null);
		holder = new GoodViewHolder(v);
		mlistView = (ListView) v.findViewById(R.id.listView);
		mlistView.setOnItemClickListener(this);
		holder.bindData(goodListItemBean);
		MoreBusinessItem.setGood(goodListItemBean);
		handler.sendEmptyMessage(MSG_WHAT_SET_ADAPTER);
		return v;
	}

	/**
	 * 打开此页面时，需要调用此方法，设置相应商品
	 * 
	 * @param goodListItemBean
	 */
	public static void setGoodListItemBean(GoodsListItemBean goodListItemBean) {
		MoreBusinessesFragment.goodListItemBean = goodListItemBean;
	}

	private void bindData(List<MoreBusinessItem> dataList) {
		MoreBusinessesAdapter adapter = new MoreBusinessesAdapter(getActivity(), dataList);
		mlistView.setAdapter(adapter);
	}

	@Override
	public String getTAG() {
		return TAG;
	}

	@Override
	public boolean isWithSearchFragment() {
		return true;
	}

	class GoodViewHolder {
		private TextView tvGoodName, tvGoodPrice, tvGoodInfo;
		private ImageView imgGoodImg;

		public GoodViewHolder(View layout) {
			tvGoodName = (TextView) layout.findViewById(R.id.tvGoodName);
			tvGoodPrice = (TextView) layout.findViewById(R.id.tvGoodPrice);
			tvGoodInfo = (TextView) layout.findViewById(R.id.tvGoodInfo);
			imgGoodImg = (ImageView) layout.findViewById(R.id.imgGoodImg);
		}

		public void bindData(GoodsListItemBean data) {
			if (data == null) {
				return;
			}
			int imageSize = getActivity().getResources().getDimensionPixelOffset(
					R.dimen.home_adapter_item_good_img_size);
			Glide.with(getActivity()).load(data.getImgURL()).override(imageSize, imageSize).centerCrop()
					.into(imgGoodImg);
			tvGoodName.setText(data.getGoodName());
			tvGoodPrice.setText(data.getGoodPrice());
			tvGoodInfo.setText(data.getYearsOfRelease() + getActivity().getString(R.string.backbtn_blank_text_4)
					+ data.getWeight());
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		openFragment(BusinessFragment.class);
	}
}
