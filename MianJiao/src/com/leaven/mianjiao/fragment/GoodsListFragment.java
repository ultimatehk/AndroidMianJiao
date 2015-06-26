package com.leaven.mianjiao.fragment;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.GoodsListAdapter;
import com.leaven.mianjiao.bean.GoodsListItemBean;
import com.leaven.mianjiao.pager.BaseHomeFragment;
import com.leaven.mianjiao.view.SwipeRefreshListView;

@SuppressLint("HandlerLeak")
public class GoodsListFragment extends BaseHomeFragment implements OnItemClickListener {
	private static final int MSG_WHAT_SET_ADAPTER = 0;
	private static String TAG = "GoodsListFragment";
	private SwipeRefreshListView mSwipeRefreshLayout;
	private ListView mListView;
	private List<GoodsListItemBean> goodsList;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (getActivity() != null && !getActivity().isFinishing()) {
				switch (msg.what) {
				case MSG_WHAT_SET_ADAPTER:
					goodsList = GoodsListItemBean.getList(msg.obj.toString());
					mListView.setAdapter(new GoodsListAdapter(getActivity(), goodsList));
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
		initView(layout);
		return layout;
	}

	private void initView(View layout) {
		mSwipeRefreshLayout = (SwipeRefreshListView) layout.findViewById(R.id.swipeRefreshListView);
		mListView = mSwipeRefreshLayout.getListView();
		mListView.setOnItemClickListener(this);
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// initiateRefresh();
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
	}

	public void searchByKeyWords(final String searchKey) {
		if (mListView == null) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 防止lvGoods为空
					while (mListView == null) {
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		System.out.println(position + ":" + id);
		openFragment(MoreBusinessesFragment.class);
		if (goodsList != null && !goodsList.isEmpty())
			MoreBusinessesFragment.setGoodListItemBean(goodsList.get(position));
	}

}
