package com.leaven.mianjiao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class PinnedHeaderScrollView extends ScrollView {

	private OnScrollViewChangeListener scrollChangeListener;

	public void setOnScrollViewChangeListener(OnScrollViewChangeListener scrollChangeListener) {
		this.scrollChangeListener = scrollChangeListener;
	}

	/**
	 * 滚动监听器
	 * @author wjz
	 *
	 */
	public interface OnScrollViewChangeListener {
		/**
		 * 滚动变化监听
		 * 
		 * @param index
		 *            第几个子控件，从0开始
		 * @param scrollTop
		 *            相对于这个控件滚动了多高
		 */
		public abstract void onScrollChanged(int index, int scrollTop);
	}

	public PinnedHeaderScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PinnedHeaderScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PinnedHeaderScrollView(Context context) {
		super(context);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (getChildCount() <= 0) {
			return;
		}
		ViewGroup group = (ViewGroup) getChildAt(0);
		int tempViewHeight = group.getPaddingTop();
		for (int i = 0; i < group.getChildCount(); i++) {
			View v = group.getChildAt(i);
			tempViewHeight += v.getHeight();
			if (tempViewHeight > t) {
				if (scrollChangeListener != null) {
					scrollChangeListener.onScrollChanged(i, t - tempViewHeight + v.getHeight());
				}
				return;
			}
		}
	}
}
