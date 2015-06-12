package com.leaven.mianjiao.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.leaven.mianjiao.view.BaseSwipeRefreshListFragment;

public class SwipeRefreshListFragment extends BaseSwipeRefreshListFragment {

	public ArrayList<String> getList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			list.add(str + i);
		}
		return list;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				android.R.id.text1, getList("初始化"));
		setListAdapter(adapter);
		setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				initiateRefresh();
			}
		});
	}

	private void initiateRefresh() {
		new DummyBackgroundTask().execute();
	}

	private void onRefreshComplete(List<String> result) {
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1,
				result));
		setRefreshing(false);
	}

	private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

		static final int TASK_DURATION = 4 * 1000; // 3 seconds

		@Override
		protected List<String> doInBackground(Void... params) {
			// Sleep for a small amount of time to simulate a background-task
			try {
				Thread.sleep(TASK_DURATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Return a new random list of cheeses
			return getList("更新完毕");
		}

		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			onRefreshComplete(result);
		}

	}

}
