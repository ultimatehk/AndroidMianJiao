package com.leaven.mianjiao.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.adapter.HomeAdapter;
import com.leaven.mianjiao.bean.HomeItemBean;
import com.leaven.mianjiao.pager.BaseHomeFragment;
import com.leaven.mianjiao.view.SwipeRefreshListView;

public class HomeFragment extends BaseHomeFragment implements View.OnClickListener {
	private static String TAG = "HomeFragment";
	private ListView mListView;
	private SwipeRefreshListView mSwipeRefreshLayout;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		// v.findViewById(R.id.btnOpenSearchFragment).setOnClickListener(this);
		initView(v);
		return v;
	}

	private void initView(View layout) {
		mSwipeRefreshLayout = (SwipeRefreshListView) layout.findViewById(R.id.swipeRefreshListView);
		mListView = mSwipeRefreshLayout.getListView();

		HomeAdapter adapter = new HomeAdapter(getActivity(), HomeItemBean.getList());
		mListView.setAdapter(adapter);
		mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				// initiateRefresh();
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});
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
	public void onClick(View v) {
		// openFragment(new MoreBusinessesFragment());
	}

	// private void initiateRefresh() {
	// new DummyBackgroundTask().execute();
	// }
	//
	// private void onRefreshComplete(List<String> result) {
	// mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
	// android.R.id.text1, result));
	// mSwipeRefreshLayout.setRefreshing(false);
	// }
	//
	// private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {
	//
	// static final int TASK_DURATION = 4 * 1000; // 3 seconds
	//
	// @Override
	// protected List<String> doInBackground(Void... params) {
	// // Sleep for a small amount of time to simulate a background-task
	// try {
	// Thread.sleep(TASK_DURATION);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// // Return a new random list of cheeses
	// return getList("更新完毕");
	// }
	//
	// @Override
	// protected void onPostExecute(List<String> result) {
	// super.onPostExecute(result);
	// onRefreshComplete(result);
	// }
	//
	// }
}
