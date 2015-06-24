package com.leaven.mianjiao.view;

import com.leaven.mianjiao.tools.CommonUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class HideSoftInputRelativeLayoutLayout extends RelativeLayout {

	public HideSoftInputRelativeLayoutLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public HideSoftInputRelativeLayoutLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HideSoftInputRelativeLayoutLayout(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		CommonUtils.hideSoftInputFromWindow(this);
		return super.onInterceptTouchEvent(ev);
	}
}
