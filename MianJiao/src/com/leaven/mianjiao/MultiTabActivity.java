package com.leaven.mianjiao;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.leaven.mianjiao.bean.IOrderInfoItem;
import com.leaven.mianjiao.bean.IOrderItem;
import com.leaven.mianjiao.bean.OrderItemBean;
import com.leaven.mianjiao.fragment.OrderCenterFragment;
import com.leaven.mianjiao.pager.MultiTabFragment;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.tools.Constant;
import com.leaven.mianjiao.view.CustomToast;

public class MultiTabActivity extends BaseActivity {
	private FragmentManager fragmentManager;
	private MultiTabFragment multiTabFragment;
	public static final String KEY_TAB_INDEX = "tab_index";

	private long exitTime;

	private ArrayList<IOrderItem> orderGoodsList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_tab);
		CommonUtils.initXGPush(this);
		CommonUtils.initUmeng(this);
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		multiTabFragment = new MultiTabFragment();
		transaction.add(R.id.tab_fragment_container, multiTabFragment);
		transaction.commit();
		orderGoodsList = new ArrayList<IOrderItem>();
		// TODO 检查更新
		// checkUpdate();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		if (intent != null && intent.getExtras() != null) {
			final int index = intent.getExtras().getInt(KEY_TAB_INDEX, -1);
			if (index >= 0 && index < Constant.MULTI_TAB_COUNT) {
				multiTabFragment.setCurrentPageItem(index);
			}
		} else {
		}
	}

	/**
	 * 添加商品
	 * 
	 * @param x
	 *            x的位置（相对于整个屏幕）
	 * @param y
	 *            y的位置（相对于整个屏幕）
	 */
	public void addGoods(int x, int y, IOrderInfoItem goodInfo) {
		boolean hasGood = false;
		final ImageView animationRing = new ImageView(this);
		animationRing.setImageResource(R.drawable.ring_blue);
		createAnimLayout().addView(animationRing);
		for (int i = 0; i < orderGoodsList.size(); i++) {
			IOrderItem item = orderGoodsList.get(i);
			if (item.getGoodName().equals(goodInfo.getGoodName())) {
				int count = item.getGoodCount();
				item.updateCount(count++);
				hasGood = true;
				break;
			}
		}
		if (!hasGood) {
			orderGoodsList.add(new OrderItemBean(goodInfo));
		}
		int statusHeight = CommonUtils.getStatusHeight(this);
		int[] location = new int[2];
		multiTabFragment.getRightNumLocation(MultiTabFragment.INDEX_OF_FRAGMENT_ORDER_CENTER, location);
		int translationX = location[0];
		int translationY = location[1];

		TranslateAnimation translateAnimationX = new TranslateAnimation(x, translationX, 0, 0);
		translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationX.setInterpolator(new LinearInterpolator());
		translateAnimationX.setDuration(Constant.ANIMATION_DURATION_TIME);
		translateAnimationX.setFillAfter(true);

		TranslateAnimation translateAnimationY2 = new TranslateAnimation(0, 0, y, y - 2 * statusHeight);
		translateAnimationY2.setInterpolator(new DecelerateInterpolator());
		translateAnimationY2.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationY2.setDuration(80);

		TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, translationY - y + 2 * statusHeight);
		translateAnimationY.setInterpolator(new AccelerateInterpolator());
		translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
		translateAnimationY.setStartOffset(80);
		translateAnimationY.setFillAfter(true);

		AnimationSet set = new AnimationSet(false);
		set.setFillAfter(false);
		set.addAnimation(translateAnimationX);
		set.addAnimation(translateAnimationY2);
		set.addAnimation(translateAnimationY);
		set.setDuration(Constant.ANIMATION_DURATION_TIME);// 动画的执行时间
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				animationRing.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				animationRing.setVisibility(View.GONE);
				OrderCenterFragment fragment = (OrderCenterFragment) multiTabFragment
						.getFragment(MultiTabFragment.INDEX_OF_FRAGMENT_ORDER_CENTER);
				fragment.addGood(orderGoodsList.size());
			}
		});
		animationRing.startAnimation(set);
	}

	@Override
	public void onBackPressed() {
		if (multiTabFragment.canFinish()) {
			super.onBackPressed();
		} else {
			multiTabFragment.goBack();
		}

	}

	@Override
	public void finish() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			CustomToast.showToast(this, R.string.press_again_finish);
			exitTime = System.currentTimeMillis();
		} else {
			super.finish();
		}
	}

	/**
	 * @Description: 创建动画层
	 * @param
	 * @return void
	 * @throws
	 */
	private ViewGroup createAnimLayout() {
		ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
		LinearLayout animLayout = new LinearLayout(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setId(Integer.MAX_VALUE);
		animLayout.setBackgroundResource(android.R.color.transparent);
		rootView.addView(animLayout);
		return animLayout;
	}
	// private void checkUpdate() {
	//
	// NetCon.getInstance(MultiTabActivity.this).checkUpdate("android", QfqMeta.getVersionName(MultiTabActivity.this),
	// new SimpleDataCallBack(MultiTabActivity.this) {
	//
	// @Override
	// public void showMsg(String message) {
	// }
	//
	// @Override
	// public void processData(String data, String message) {
	// Gson gson = new Gson();
	// final UpdateAppBean bean = gson.fromJson(data, UpdateAppBean.class);
	// if (bean != null && bean.getHasnew() && bean.shouldNotifyUpdate(MultiTabActivity.this)) {
	// AlertDialog.Builder builder = new AlertDialog.Builder(MultiTabActivity.this);
	// builder.setTitle(R.string.note);
	// builder.setMessage(R.string.has_new_version);
	// builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface arg0, int arg1) {
	// DownloadConfig downloadConfig = new DownloadConfig(FileCommonUtils.getDiskCacheDir(
	// MultiTabActivity.this, "qufenqi_bd").getAbsolutePath(), bean.getUrl());
	// downloadConfig.setNotificationSmallIconResId(R.drawable.ic_launcher);
	// downloadConfig.setNotificationTitle("鏂扮増鏈鍦ㄤ笅杞戒腑...");
	// downloadConfig
	// .setCustomBroadCast(BDAppDownloadTaskReceiver.ACTION_DOWNLOAD_COMPLETE);
	// FileDownloadService.download(MultiTabActivity.this, downloadConfig);
	// }
	// });
	// builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int arg1) {
	// if (bean.getForceUpdate()) {
	// dialog.dismiss();
	// finish();
	// } else {
	// Spf.saveIgnoredVersion(MultiTabActivity.this, bean.getVersion());
	// dialog.dismiss();
	// }
	// }
	// });
	// builder.setCancelable(false);
	// AlertDialog updateDialog = builder.create();
	// if (!isFinishing()) {
	// updateDialog.show();
	// }
	// }
	// }
	// });
	//
	// }
}
