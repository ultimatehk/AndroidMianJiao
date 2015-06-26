package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.GoodsListAdapter;
import com.leaven.mianjiao.bean.GoodsListItemBean;
import com.leaven.mianjiao.pager.BaseHomeFragment;
import com.leaven.mianjiao.view.SwipeRefreshListView;

public class GoodsListFragment extends BaseHomeFragment {
	private static final int MSG_WHAT_SET_ADAPTER = 0;
	private static String TAG = "GoodsListFragment";
	private SwipeRefreshListView swipeRefreshLayout;
	private ListView lvGoods;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (getActivity() != null && !getActivity().isFinishing()) {
				switch (msg.what) {
				case MSG_WHAT_SET_ADAPTER:
					lvGoods.setAdapter(new GoodsListAdapter(getActivity(),
							GoodsListItemBean.getList(msg.obj.toString())));
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
		View layout = inflater.inflate(R.layout.fragment_goods_list, null);
		swipeRefreshLayout = (SwipeRefreshListView) layout.findViewById(R.id.swipeRefreshListView);
		lvGoods = swipeRefreshLayout.getListView();
		return layout;
	}

	public void searchByKeyWords(final String searchKey) {
		if (lvGoods == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 防止lvGoods为空
					while (lvGoods == null) {
					}
					// 用lvGoods.post
					Message msg = handler.obtainMessage(MSG_WHAT_SET_ADAPTER, searchKey);
					handler.sendMessage(msg);

				}
			}).start();
		} else {
			Message msg = handler.obtainMessage(MSG_WHAT_SET_ADAPTER, searchKey);
			handler.sendMessage(msg);
		}
	}

	@Override
	public String getTAG() {
		return TAG;
	}

	@Override
	public boolean isWithSearchFragment() {
		return true;
	}

}
