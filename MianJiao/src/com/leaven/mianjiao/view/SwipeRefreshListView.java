package com.leaven.mianjiao.view;

import com.leaven.mianjiao.R;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class SwipeRefreshListView extends SwipeRefreshLayout {
	private ListView mListView;

	public SwipeRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SwipeRefreshListView(Context context) {
		this(context, null);
	}

	public void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_swipe_refresh_listview, this);
		setColorSchemeResources(R.color.default_more_dark_blue, R.color.default_dark_blue, R.color.default_blue,
				R.color.default_light_blue, R.color.default_more_light_blue);
		mListView = (ListView) findViewById(R.id.listView);
	}

	public ListView getListView() {
		return mListView;
	}

	/**
	 * As mentioned above, we need to override this method to properly signal when a 'swipe-to-refresh' is possible.
	 *
	 * @return true if the {@link android.widget.ListView} is visible and can scroll up.
	 */
	@Override
	public boolean canChildScrollUp() {
		final ListView listView = getListView();
		if (listView.getVisibility() == View.VISIBLE) {
			return canListViewScrollUp(listView);
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
			return listView.getChildCount() > 0
					&& (listView.getFirstVisiblePosition() > 0 || listView.getChildAt(0).getTop() < listView
							.getPaddingTop());
		}
	}
	// END_INCLUDE (check_list_can_scroll)

}
