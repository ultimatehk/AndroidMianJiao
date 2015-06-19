package com.leaven.mianjiao.pager;

import java.util.ArrayList;
import java.util.List;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.fragment.BasementHomeFragment;
import com.leaven.mianjiao.fragment.OrderCenterFragment;
import com.leaven.mianjiao.fragment.UserCenterFragment;
import com.leaven.mianjiao.pager.BasePager.AbstractPagerFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MultiTabFragment extends Fragment {
	private TabIndicator tabIndicator;
	private BasePager basePager;
	private List<BasePager.AbstractPagerFragment> abstractPagerFragments;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main_tab, null, false);
		basePager = (BasePager) v.findViewById(R.id.basePager);
		tabIndicator = (TabIndicator) v.findViewById(R.id.tabIndicator);
		abstractPagerFragments = initFragments();
		basePager.setPagerFragments(abstractPagerFragments);
		tabIndicator.setPager(basePager, 0);
		return v;
	}

	public List<AbstractPagerFragment> initFragments() {
		List<AbstractPagerFragment> list = new ArrayList<AbstractPagerFragment>();
		list.add(new BasementHomeFragment());
		list.add(new OrderCenterFragment());
		list.add(new UserCenterFragment());
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

	public boolean canFinish() {
		int index = getCurrentPageItem();
		if (abstractPagerFragments != null && abstractPagerFragments.size() > index) {
			return abstractPagerFragments.get(index).canFinish();
		}
		return true;
	}

	public void goBack() {
		int index = getCurrentPageItem();
		if (abstractPagerFragments != null && abstractPagerFragments.size() > index) {
			abstractPagerFragments.get(index).goBack();
		}
	}
}
