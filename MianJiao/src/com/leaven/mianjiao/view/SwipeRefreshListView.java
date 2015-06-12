package com.leaven.mianjiao.view;

import com.leaven.mianjiao.R;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SwipeRefreshListView extends SwipeRefreshLayout {
	private ListView mListView;

	public SwipeRefreshListView(Context context) {
		super(context);
		init();
	}

	/**
	 * As mentioned above, we need to override this method to properly signal when a 'swipe-to-refresh' is possible.
	 *
	 * @return true if the {@link android.widget.ListView} is visible and can scroll up.
	 */
	@Override
	public boolean canChildScrollUp() {
		if (mListView.getVisibility() == View.VISIBLE) {
			return canListViewScrollUp(mListView);
		} else {
			return false;
		}
	}

	// BEGIN_INCLUDE (check_list_can_scroll)
	/**
	 * Utility method to check whether a {@link ListView} can scroll up from it's current position. Handles platform
	 * version differences, providing backwards compatible functionality where needed.
	 */
	private static boolean canListViewScrollUp(ListView listView) {
		if (android.os.Build.VERSION.SDK_INT >= 14) {
			// For ICS and above we can call canScrollVertically() to determine this
			return ViewCompat.canScrollVertically(listView, -1);
		} else {
			// Pre-ICS we need to manually check the first visible item and the child view's top
			// value
			return true;
		}
	}

	private void init() {
		mListView = new ListView(getContext());
		addView(mListView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		setColorSchemeResources(R.color.default_blue, R.color.default_light_blue);
	}

	public ListView getListView() {
		return mListView;
	}

}
