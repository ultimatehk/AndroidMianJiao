package com.leaven.mianjiao.pager;

import com.leaven.mianjiao.fragment.BasementHomeFragment;
import com.umeng.analytics.MobclickAgent;

import android.support.v4.app.Fragment;

public abstract class BaseHomeFragment extends Fragment implements IHomeFragmentStateListener {

	protected String TAG;
	protected BasementHomeFragment basementHomeFragment;

	protected BaseHomeFragment() {
		super();
		TAG = getTag();
	}

	public void setBasementHomeFragment(BasementHomeFragment basementHomeFragment) {
		this.basementHomeFragment = basementHomeFragment;
	}

	@Override
	public void onOpenFragment() {
		MobclickAgent.onPageStart(TAG);
	}

	@Override
	public void onCloseFragment() {
		MobclickAgent.onPageEnd(TAG);
	}

	protected void openFragment(Class<?> fragmentClass) {
		if (basementHomeFragment != null) {
			basementHomeFragment.openFragment(fragmentClass);
		}
	}

	protected void closeFragment() {
		if (basementHomeFragment != null) {
			basementHomeFragment.closeFragment();
		}
	}

}
