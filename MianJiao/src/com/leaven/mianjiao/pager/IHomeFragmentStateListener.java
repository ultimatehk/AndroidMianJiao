package com.leaven.mianjiao.pager;

public interface IHomeFragmentStateListener {
	public abstract void onOpenFragment();

	public abstract void onCloseFragment();

	public abstract String getTAG();

	public abstract boolean isWithSearchFragment();

}
