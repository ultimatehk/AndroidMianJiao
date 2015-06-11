package com.leaven.mianjiao.pager;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.Constant;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabIndicator extends HorizontalScrollView implements PageIndicator {
	/** Title text used when no title is provided by the adapter. */
	private static final CharSequence EMPTY_TITLE = "";

	private Runnable mTabSelector;

	private final IcsLinearLayout mTabLayout;
	private BasePager mBasePager;

	private int mMaxTabWidth;
	private int mSelectedTabIndex = 0;
	private final OnClickListener mTabClickListener = new OnClickListener() {
		public void onClick(View view) {
			TabView tabView = (TabView) view.getTag();
			final int newSelected = tabView.getIndex();
			final int oldSelected = mBasePager.getCurrentItemIndex();
			if (oldSelected == newSelected) {
				mBasePager.getItem(newSelected).onReSelected();
			} else {
				mBasePager.getItem(oldSelected).onLoseSelected();
				setCurrentItem(newSelected);
			}
		}
	};

	public void tab(int index) {
		setCurrentItem(index);
	}

	public TabIndicator(Context context) {
		this(context, null);
	}

	public TabIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		setHorizontalScrollBarEnabled(false);

		mTabLayout = new IcsLinearLayout(context, 0);
		addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
		init();
	}

	private void init() {

	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
		setFillViewport(lockedExpanded);

		final int childCount = mTabLayout.getChildCount();
		if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
			if (childCount > 2) {
				mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
			} else {
				mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
			}
		} else {
			mMaxTabWidth = -1;
		}

		final int oldWidth = getMeasuredWidth();
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int newWidth = getMeasuredWidth();

		if (lockedExpanded && oldWidth != newWidth) {
			// Recenter the tab display if we're at a new (scrollable) size.
			// setCurrentItem(mSelectedTabIndex);
		}
	}

	private void animateToTab(final int position) {
		final View tabView = mTabLayout.getChildAt(position);
		if (mTabSelector != null) {
			removeCallbacks(mTabSelector);
		}
		mTabSelector = new Runnable() {
			public void run() {
				final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
				smoothScrollTo(scrollPos, 0);
				mTabSelector = null;
			}
		};
		post(mTabSelector);
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mTabSelector != null) {
			// Re-post the selector we saved
			post(mTabSelector);
		}
	}

	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (mTabSelector != null) {
			removeCallbacks(mTabSelector);
		}
	}

	private void addTab(int index, CharSequence text, int iconResId, int rightEdgeIconResId) {
		final TabView tabView = createTab(index);
		if (!TextUtils.isEmpty(text)) {
			tabView.setText(text);
		}
		if (iconResId != 0) {
			tabView.setIcon(iconResId);
		}
		if (rightEdgeIconResId != 0) {
			tabView.setRightEdgeIcon(rightEdgeIconResId);
		}
		tabView.setTag(tabView);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
		mTabLayout.setPadding(0, (int) (10 * dm.density), 0, (int) (5 * dm.density));
		mTabLayout.addView(tabView, p);
	}

	private TabView createTab(int index) {
		final TabView tabView = new TabView(getContext());
		tabView.mIndex = index;
		tabView.setFocusable(true);
		tabView.setOnClickListener(mTabClickListener);
		return tabView;
	}

	public void setPager(BasePager view) {
		setPager(view, mSelectedTabIndex);
	}

	public void setPager(BasePager view, int defaultSelectedIndex) {
		this.mSelectedTabIndex = defaultSelectedIndex;
		if (mBasePager == view) {
			return;
		}
		mBasePager = view;
		notifyDataSetChanged();
	}

	public void notifyDataSetChanged() {
		mTabLayout.removeAllViews();
		final int count = mBasePager.getCount();
		for (int i = 0; i < count; i++) {
			CharSequence title = mBasePager.getPageTitle(i);
			if (title == null) {
				title = EMPTY_TITLE;
			}
			int iconResId = 0;
			iconResId = mBasePager.getIconResId(i);
			int rightEdgeIconResId = mBasePager.getRightEdgeIconId(i);
			addTab(i, title, iconResId, rightEdgeIconResId);
		}
		if (mSelectedTabIndex > count) {
			mSelectedTabIndex = count - 1;
		}
		setCurrentItem(mSelectedTabIndex);
		requestLayout();
	}

	public void notifyBottomTabChanged(ITabUiConfig f) {
		int index = mBasePager.getPagerFragments().indexOf(f);
		if (index >= 0) {
			TabView child = (TabView) mTabLayout.getChildAt(index);
			child.setRightEdgeIcon(f.getRightEdgeIconResId());
		}
	}

	@Override
	public void setCurrentItem(int item) {
		if (mBasePager == null) {
			throw new IllegalStateException("BasePager has not been bound.");
		}
		mSelectedTabIndex = item;
		mBasePager.setCurrentItem(item);

		final int tabCount = mTabLayout.getChildCount();
		for (int i = 0; i < tabCount; i++) {
			final View child = mTabLayout.getChildAt(i);
			final boolean isSelected = (i == item);
			child.setSelected(isSelected);
			if (isSelected) {
				animateToTab(item);
			} else {
			}
		}
	}

	private class TabView extends RelativeLayout {
		private int mIndex;
		private ImageView edgeIco;
		private TextView tvTab;
		private ImageView ivTab;
		private static final int ID_TV_TAB = 0x7f000001;
		private static final int ID_IV_TAB = 0x7f000002;

		public TabView(Context context) {
			super(context, null, 0);
			int density = (int) getResources().getDisplayMetrics().density;
			ivTab = new ImageView(context);
			ivTab.setScaleType(ScaleType.FIT_CENTER);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(Constant.MULTI_TAB_ICON_SIZE * density,
					Constant.MULTI_TAB_ICON_SIZE * density);
			ivTab.setId(ID_IV_TAB);
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			this.addView(ivTab, lp);
			tvTab = new TextView(context);
			tvTab.setMaxLines(1);
			tvTab.setEllipsize(TruncateAt.END);
			tvTab.setGravity(Gravity.CENTER_HORIZONTAL);
			lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			tvTab.setId(ID_TV_TAB);
			Resources resource = context.getResources();
			ColorStateList csl = (ColorStateList) resource
					.getColorStateList(R.color.selector_homebottom_tab_text_color);
			if (csl != null) {
				tvTab.setTextColor(csl);
			}
			tvTab.setMaxEms(4);
			tvTab.setPadding(3 * density, 3 * density, 3 * density, 3 * density);
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lp.addRule(RelativeLayout.BELOW, ivTab.getId());
			this.addView(tvTab, lp);
			lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.ALIGN_TOP, ivTab.getId());
			lp.setMargins(0, 0, 16 * density, 0);
			lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			edgeIco = new ImageView(context);
			edgeIco.setVisibility(View.VISIBLE);
			this.addView(edgeIco, lp);
		}

		@Override
		public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);

			// Re-measure if we went beyond our maximum size.
			if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
				super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY), heightMeasureSpec);
			}
		}

		public int getIndex() {
			return mIndex;
		}

		public void setText(CharSequence text) {
			tvTab.setText(text);
		}

		public void setIcon(int icResId) {
			ivTab.setImageResource(icResId);
		}

		@Override
		public void setSelected(boolean selected) {
			super.setSelected(selected);
			tvTab.setSelected(selected);
			ivTab.setSelected(selected);
		}

		public void setRightEdgeIcon(int icResId) {
			edgeIco.setImageResource(icResId);
		}
	}
}
