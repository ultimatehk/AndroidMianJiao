package com.leaven.mianjiao.view;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 通用的顶部标题栏： 默认title字体大小为19，颜色为title_bar_title_text_color； 默认左键字体大小为18，颜色为title_bar_left_text_color；
 * 默认右键字体大小为18，颜色为title_bar_right_text_color； 默认显示左键，右键以及底部线, 通过setLeftBtnListener、setRightBtnListener设置左键和右键监听
 * ，通过setLeftBtnVisibility
 * 、setRightBtnVisibility显示左右键是否显示，通过setDrawableLeft、setDrawableRight是设置左右键图片，通过setLeftBtnText、setRightBtnText设置左右键文案
 */
public class TopTitleBar extends RelativeLayout {
	private static final int TEXT_SIZE_DEFAULT = 19;
	private static final int BTN_TEXT_SIZE_DEFAULT = 18;
	private static final int TITLE_TEXT_COLOR_DEFAULT = R.color.title_bar_title_text_color;
	private static final int LEFT_TEXT_COLOR_DEFAULT = R.color.title_bar_left_text_color;
	private static final int RIGHT_TEXT_COLOR_DEFAULT = R.color.title_bar_right_text_color;
	private static final int BG_COLOR_DEFAULT = R.color.title_bar_bg;
	/**
	 * 背景颜色
	 */
	private int bgColor;
	/**
	 * 标题
	 */
	private String topTitle = "";
	/**
	 * 是否显示左上角按钮
	 */
	private boolean showLeftBtn = true;
	/**
	 * 是否显示右上角按钮
	 */
	private boolean showRightBtn = true;
	/**
	 * 左上角按钮的left drawable
	 */
	private int drawableLeftBtn = 0;
	/**
	 * 右上角按钮的left drawable
	 */
	private int drawableRightBtn = 0;
	/**
	 * 左上角按钮文案
	 */
	private String titleLeftBtn = "";
	/**
	 * 右上角按钮文案
	 */
	private String titleRightBtn = "";
	/**
	 * 右上角字体颜色
	 */
	private int colorRightText;
	/**
	 * 右上角字体大小
	 */
	private int btnRightTextSize = BTN_TEXT_SIZE_DEFAULT;
	/**
	 * 左上角字体颜色
	 */
	private int colorLeftText;
	/**
	 * 左上角字体大小
	 */
	private int btnLeftTextSize = BTN_TEXT_SIZE_DEFAULT;
	/**
	 * 标题栏字体颜色
	 */
	private int colorTitle;
	/**
	 * 标题栏字体大小
	 */
	private int titleTextSize = TEXT_SIZE_DEFAULT;
	/**
	 * 是否显示标题栏的分割线,默认显示
	 */
	private boolean showDividorLine = true;

	private TextView tvTitle;
	private TextView btnTopLeft;
	private TextView btnTopRight;
	private View topDividorLine;

	public TopTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获得自定义属性
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopTitleBar);
		int count = ta.getIndexCount();
		for (int i = 0; i < count; i++) {
			int itemId = ta.getIndex(i); // 获取某个属性的Id值
			switch (itemId) {
			case R.styleable.TopTitleBar_title:
				topTitle = ta.getString(itemId);
				break;
			case R.styleable.TopTitleBar_show_dividor_line:
				showDividorLine = ta.getBoolean(itemId, true);
				break;
			case R.styleable.TopTitleBar_left_btn_show:
				showLeftBtn = ta.getBoolean(itemId, true);
				break;
			case R.styleable.TopTitleBar_right_btn_show:
				showRightBtn = ta.getBoolean(itemId, true);
				break;
			case R.styleable.TopTitleBar_left_btn_drawable:
				drawableLeftBtn = ta.getResourceId(itemId, 0);
				break;
			case R.styleable.TopTitleBar_right_btn_drawable:
				drawableRightBtn = ta.getResourceId(itemId, 0);
				break;
			case R.styleable.TopTitleBar_left_btn_title:
				titleLeftBtn = ta.getString(itemId);
				break;
			case R.styleable.TopTitleBar_right_btn_title:
				titleRightBtn = ta.getString(itemId);
				break;
			case R.styleable.TopTitleBar_title_color:
				colorTitle = ta.getInteger(itemId, CommonUtils.getColorFromResource(context, TITLE_TEXT_COLOR_DEFAULT));
				break;
			case R.styleable.TopTitleBar_right_btn_text_color:
				colorRightText = ta.getInteger(itemId,
						CommonUtils.getColorFromResource(context, RIGHT_TEXT_COLOR_DEFAULT));
				break;
			case R.styleable.TopTitleBar_right_btn_text_size:
				btnRightTextSize = ta.getDimensionPixelSize(itemId, TEXT_SIZE_DEFAULT);
				btnRightTextSize = CommonUtils.px2sp(context, btnRightTextSize);
				break;
			case R.styleable.TopTitleBar_left_btn_text_color:
				colorLeftText = ta.getInteger(itemId,
						CommonUtils.getColorFromResource(context, LEFT_TEXT_COLOR_DEFAULT));
				break;
			case R.styleable.TopTitleBar_left_btn_text_size:
				btnLeftTextSize = ta.getDimensionPixelSize(itemId, TEXT_SIZE_DEFAULT);
				btnLeftTextSize = CommonUtils.px2sp(context, btnLeftTextSize);
				break;
			case R.styleable.TopTitleBar_title_text_size:
				titleTextSize = ta.getDimensionPixelSize(itemId, TEXT_SIZE_DEFAULT);
				titleTextSize = CommonUtils.px2sp(context, titleTextSize);
				break;
			case R.styleable.TopTitleBar_title_bar_bg:
				bgColor = ta.getInteger(itemId, CommonUtils.getColorFromResource(context, BG_COLOR_DEFAULT));
				break;
			}
		}
		ta.recycle();
		init();
	}

	/**
	 * 从AttributeSet attrs获取到属性后进行视图刷新
	 */
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_toptitle_bar, this);
		setBackgroundColor(bgColor == 0 ? CommonUtils.getColorFromResource(getContext(), BG_COLOR_DEFAULT) : bgColor);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		topDividorLine = findViewById(R.id.topDividorLine);
		topDividorLine.setVisibility(showDividorLine ? View.VISIBLE : View.INVISIBLE);
		tvTitle.setText(TextUtils.isEmpty(topTitle) ? "" : topTitle);
		tvTitle.setTextColor(colorTitle == 0 ? CommonUtils.getColorFromResource(getContext(), TITLE_TEXT_COLOR_DEFAULT)
				: colorTitle);
		tvTitle.setTextSize(titleTextSize);
		btnTopLeft = (TextView) findViewById(R.id.btnTopLeft);
		btnTopLeft.setVisibility(showLeftBtn ? View.VISIBLE : View.INVISIBLE);
		/**
		 * 如果是没有任何文案默认加上空格，扩大textview的可点击区域
		 */
		btnTopLeft.setText(TextUtils.isEmpty(titleLeftBtn) ? getResources().getString(R.string.backbtn_blank_text)
				: titleLeftBtn);
		if (drawableLeftBtn > 0) {
			btnTopLeft.setCompoundDrawablesWithIntrinsicBounds(drawableLeftBtn, 0, 0, 0);
		}
		btnTopLeft.setTextColor(colorLeftText == 0 ? CommonUtils.getColorFromResource(getContext(),
				LEFT_TEXT_COLOR_DEFAULT) : colorLeftText);
		btnTopLeft.setTextSize(btnLeftTextSize);

		btnTopRight = (TextView) findViewById(R.id.btnTopRight);
		btnTopRight.setVisibility(showRightBtn ? View.VISIBLE : View.INVISIBLE);
		/**
		 * 如果是没有任何文案默认加上空格，扩大textview的可点击区域
		 */
		btnTopRight.setText(TextUtils.isEmpty(titleRightBtn) ? getResources().getString(R.string.backbtn_blank_text)
				: titleRightBtn);
		if (drawableRightBtn > 0) {
			btnTopRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRightBtn, 0);
		}
		btnTopRight.setTextColor(colorRightText == 0 ? CommonUtils.getColorFromResource(getContext(),
				RIGHT_TEXT_COLOR_DEFAULT) : colorRightText);
		btnTopRight.setTextSize(btnRightTextSize);
	}

	public void setTitle(String text) {
		topTitle = text;
		tvTitle.setText(text);
	}

	public String getTitle() {
		return topTitle;
	}

	public void setDrawableLeft(int resId) {
		if (resId > 0) {
			btnTopLeft.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
		}
	}

	public void setDrawableRight(int resId) {
		if (resId > 0) {
			btnTopRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
		}
	}

	public void setLeftBtnListener(View.OnClickListener listener) {
		btnTopLeft.setOnClickListener(listener);
	}

	public void setRightBtnListener(View.OnClickListener listener) {
		btnTopRight.setOnClickListener(listener);
	}

	public void setRightBtnText(String text) {
		btnTopRight.setText(text);
	}

	public void setLeftBtnText(String text) {
		btnTopLeft.setText(text);
	}

	public void setRightBtnVisibility(int visibility) {
		btnTopRight.setVisibility(visibility);
	}

	public void setLeftBtnVisibility(int visibility) {
		btnTopLeft.setVisibility(visibility);
	}
}
