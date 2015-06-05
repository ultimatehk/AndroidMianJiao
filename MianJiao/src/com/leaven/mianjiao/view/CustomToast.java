package com.leaven.mianxiao.view;

import java.util.Calendar;
import com.leaven.mianxiao.R;
import com.leaven.mianxiao.tools.Constant;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
	private static long lastShowTime;

	@SuppressLint("InflateParams")
	private static Toast makeText(Context context, int gravity, String msg, int duration) {
		if (context == null) {
			return null;
		}
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout toastLayout = (LinearLayout) inflater.inflate(R.layout.view_toast, null);
		TextView toastView = (TextView) toastLayout.findViewById(R.id.tvToastMsg);
		if (!TextUtils.isEmpty(msg)) {
			toastView.setText(msg);
		} else {
			// toastView.setText("操作繁忙，请稍等");
		}
		Toast toast = new Toast(context);
		toast.setDuration(duration);
		if (gravity != Integer.MIN_VALUE) {
			toast.setGravity(gravity, 0, 0);
		}
		toast.setView(toastLayout);
		return toast;
	}

	private static Toast makeText(Context context, String msg, int duration) {
		return makeText(context, Integer.MIN_VALUE, msg, duration);
	}

	public static void showToast(Context context, String msg) {
		showToast(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showToast(Context context, int resId) {
		showToast(context, context.getString(resId), Toast.LENGTH_SHORT);
	}

	public static void showToast(Context context, int resId, int duration) {
		showToast(context, context.getString(resId), duration);
	}

	public static void showToast(Context context, String msg, int duration) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}
		Toast toast = makeText(context, msg, duration);
		long showTime = 0L;
		if (duration == Toast.LENGTH_LONG) {
			showTime = Constant.LONG_SHOW_TIME;
		} else {
			showTime = Constant.SHORT_SHOW_TIME;
		}
		// 容错
		if (toast == null) {
			return;
		}
		if (lastShowTime + showTime < Calendar.getInstance().getTimeInMillis()) {
			toast.show();
			lastShowTime = Calendar.getInstance().getTimeInMillis();
		} else {
			toast.cancel();
		}
	}
}
