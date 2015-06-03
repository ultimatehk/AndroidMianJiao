package com.leaven.mianxiao.pager;

import java.util.ArrayList;
import java.util.List;

import com.leaven.mianxiao.R;
import com.leaven.mianxiao.fragment.HomeFragment;
import com.leaven.mianxiao.pager.BasePager.AbstractPagerFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MultiTabFragment extends Fragment {
	private TabIndicator tabIndicator;
	private BasePager basePager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_tab, null, false);
		basePager = (BasePager) v.findViewById(R.id.basePager);
		tabIndicator = (TabIndicator) v.findViewById(R.id.tabIndicator);
		List<BasePager.AbstractPagerFragment> abstractPagerFragments = initFragments();
		basePager.setPagerFragments(abstractPagerFragments);
		tabIndicator.setPager(basePager, 0);
		return v;
	}

	public List<AbstractPagerFragment> initFragments() {
		List<AbstractPagerFragment> list = new ArrayList<AbstractPagerFragment>();
		// TODO 增加Tab
		list.add(new HomeFragment());
		list.add(new HomeFragment());
		list.add(new HomeFragment());
		for (AbstractPagerFragment fragment : list) {
			fragment.setTabIndicator(tabIndicator);
		}
		return list;
	}

	public int getTotalPagerNum() {
		return (basePager == null) ? 0 : basePager.getCount();
	}

	public int getCurrentPageItem() {
		return (basePager == null) ? 0 : basePager.getCurrentItemIndex();
	}

	public void setCurrentPageItem(int item) {
		setCurrentPageItem(item, null);
	}

	public void setCurrentPageItem(int item, Bundle extras) {
		if (basePager != null && basePager.getCount() > 0 && item >= 0 && item < basePager.getCount()) {
			tabIndicator.tab(item);
			basePager.getItem(item).onSelected(extras);
		}
	}

	public void notifyBottomTabStateChanged() {
		tabIndicator.notifyDataSetChanged();
	}
}
