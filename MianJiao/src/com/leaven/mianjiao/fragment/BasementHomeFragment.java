package com.leaven.mianjiao.fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.pager.BaseHomeFragment;
import com.leaven.mianjiao.pager.BasePager;
import com.leaven.mianjiao.tools.Constant;
import com.leaven.mianjiao.view.CustomToast;

public class BasementHomeFragment extends BasePager.AbstractPagerFragment implements SearchFragment.OnClickBtnListener {

	private ArrayList<BaseHomeFragment> listFragment = null;
	private SearchFragment searchFragment;
	private boolean hasSearch = false;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_basement_home, null);
		searchFragment = new SearchFragment();
		searchFragment.setOnClickBtnListener(this);
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		ft.add(R.id.searchContainer, searchFragment);
		ft.commit();
		openFragment(HomeFragment.class);
		return v;
	}

	public void openFragment(Class<?> className) {
		BaseHomeFragment fg = null;
		try {
			Constructor<?> cons[] = className.getConstructors();
			if (cons.length < 1)
				return;
			fg = (BaseHomeFragment) cons[0].newInstance();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (java.lang.InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (fg instanceof GoodsListFragment) {
			// 搜索结果只有一个
			if (hasSearch) {
				for (int i = listFragment.size() - 1; i >= 0; i--) {
					BaseHomeFragment fragment = listFragment.get(i);
					if (fragment instanceof GoodsListFragment) {
						return;
					} else {
						closeFragment();
					}
				}
			}
		}
		if (fg.isWithSearchFragment()) {
			openFragment(R.id.withSearchContainer, fg);
		} else {
			openFragment(R.id.baseHomeContainer, fg);
		}

	}

	private void hideSearchFragment(FragmentTransaction ft) {
		if (!searchFragment.isHidden()) {
			ft.hide(searchFragment);
		}
	}

	private void showSearchFragment(FragmentTransaction ft) {
		if (searchFragment.isHidden()) {
			ft.show(searchFragment);
		}
	}

	private void openFragment(int resId, BaseHomeFragment fg) {
		try {
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			if (listFragment == null) {
				listFragment = new ArrayList<BaseHomeFragment>();
			}
			// 关闭上一个页面
			if (!listFragment.isEmpty()) {
				BaseHomeFragment preFg = getFirstFragment();
				ft.hide(preFg);
				preFg.onCloseFragment();
			}

			// 搜索Fragment
			if (fg.isWithSearchFragment()) {
				showSearchFragment(ft);
			} else {
				hideSearchFragment(ft);
			}

			// 打开新页面
			fg.setBasementHomeFragment(this);
			ft.add(resId, fg);
			fg.onOpenFragment();
			listFragment.add(fg);
			ft.commit();
		} catch (Exception ex) {
		}
	}

	public void closeFragment() {
		if (listFragment == null || listFragment.isEmpty()) {
			return;
		}
		try {
			FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
			// 结束当前页面
			BaseHomeFragment fg = getFirstFragment();
			ft.remove(fg);
			fg.onCloseFragment();
			listFragment.remove(listFragment.size() - 1);
			if (fg instanceof GoodsListFragment) {
				hasSearch = false;
			}
			// 打开前一个页面
			if (!listFragment.isEmpty()) {
				fg = getFirstFragment();
				// 搜索Fragment
				if (fg.isWithSearchFragment()) {
					showSearchFragment(ft);
				} else {
					hideSearchFragment(ft);
				}
				ft.show(fg);
				fg.onOpenFragment();
				ft.commit();
			}
		} catch (Exception ex) {
		}
	}

	@Override
	public void onSelected() {
		// super.onSelected();
		// TODO 选择
		BaseHomeFragment fragment = getFirstFragment();
		if (fragment != null) {
			fragment.onOpenFragment();
		}
	}

	@Override
	public void onReSelected() {
		// TODO 重复选择
		// TODO 删掉当前的，打开第一个

	}

	@Override
	public void onLoseSelected() {
		// 删掉super的操作，不需要记录该底层的Fragment的事件
		listFragment.get(listFragment.size() - 1).onCloseFragment();
	}

	@Override
	public int getIconResId() {
		return R.drawable.selector_homebottom_tab_home;
	}

	@Override
	public String getPageTitle() {
		return Constant.MULTI_TAB_HOME;
	}

	@Override
	protected boolean canFinish() {
		return listFragment == null || listFragment.size() <= 1;
	}

	@Override
	protected void goBack() {
		if (canFinish()) {
			super.goBack();
		} else {
			closeFragment();
		}
	}

	@Override
	public void onClickTab(View view) {
		// TODO Auto-generated method stub

	}

	private BaseHomeFragment getFirstFragment() {
		return listFragment == null || listFragment.isEmpty() ? null : listFragment.get(listFragment.size() - 1);
	}

	@Override
	public void onClickSearch(String keyWords) {
		// TODO 搜索
		CustomToast.showToast(getActivity(), keyWords);
		openFragment(GoodsListFragment.class);
		hasSearch = true;
		BaseHomeFragment fragment = getFirstFragment();
		if (fragment != null && fragment instanceof GoodsListFragment) {
			((GoodsListFragment) fragment).searchByKeyWords(keyWords);
		}
	}

}
