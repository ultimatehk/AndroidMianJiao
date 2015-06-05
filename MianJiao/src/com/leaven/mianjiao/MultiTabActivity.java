package com.leaven.mianjiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.leaven.mianjiao.pager.MultiTabFragment;
import com.leaven.mianjiao.tools.Constant;

public class MultiTabActivity extends FragmentActivity {
	private FragmentManager fragmentManager;
	private MultiTabFragment multiTabFragment;
	public static final String KEY_TAB_INDEX = "tab_index";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_tab);
		// TODO WebView的Cookie通用
		// CookieSyncManager.createInstance(this);
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		multiTabFragment = new MultiTabFragment();
		transaction.add(R.id.tab_fragment_container, multiTabFragment);
		transaction.commit();
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
