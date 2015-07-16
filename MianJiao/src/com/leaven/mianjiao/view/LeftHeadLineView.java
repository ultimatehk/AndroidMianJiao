package com.leaven.mianjiao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * 字体默认大小为13sp，颜色默认为default_dark_blue
 * 
 * @author wjz
 *
 */
public class LeftHeadLineView extends RelativeLayout {
	private static final int TITLE_TEXT_SIZE_DEFAULT = 13;
	private static final int TITLE_TEXT_COLOR_DEFAULT = R.color.default_dark_blue;

	/**
	 * headline TextSize
	 */
	private int textSize = TITLE_TEXT_SIZE_DEFAULT;
	/**
	 * headline TextColor
	 */
	private int textColor;
	/**
	 * headline 内容
	 */
	private String titleStr;

	public LeftHeadLineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeadLineTitle);
		int count = ta.getIndexCount();
		for (int i = 0; i < count; i++) {
			int itemId = ta.getIndex(i); // 获取某个属性的Id值
			switch (itemId) {
			case R.styleable.HeadLineTitle_headline_title:
				titleStr = ta.getString(itemId);
				break;
			case R.styleable.HeadLineTitle_headline_text_size:
				textSize = ta.getDimensionPixelSize(itemId, TITLE_TEXT_SIZE_DEFAULT);
				textSize = CommonUtils.px2sp(context, textSize);
				break;
			case R.styleable.HeadLineTitle_headline_text_color:
				textColor = ta.getInteger(itemId, CommonUtils.getColorFromResource(context, TITLE_TEXT_COLOR_DEFAULT));
				break;
			}
		}
		ta.recycle();
		init();
	}

	public LeftHeadLineView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	private TextView tvHeadLineTitle;
	private View arrowRight;

	private void init() {
		setBackgroundResource(R.color.default_white);
		LayoutInflater.from(getContext()).inflate(R.layout.view_left_headline, this);
		tvHeadLineTitle = (TextView) findViewById(R.id.tvHeadLineTitle);
		arrowRight = findViewById(R.id.arrowRight);
		tvHeadLineTitle.setText(TextUtils.isEmpty(titleStr) ? "" : titleStr);
		tvHeadLineTitle.setTextColor(textColor == 0 ? CommonUtils.getColorFromResource(getContext(),
				TITLE_TEXT_COLOR_DEFAULT) : textColor);
		tvHeadLineTitle.setTextSize(textSize);
	}

	private boolean whetherExpanded;

	/**
	 * 初始化是否展开的状态
	 * 
	 * @param whetherExpanded
	 *            是否展开
	 */
	public void initClickStatus(boolean whetherExpanded) {
		this.whetherExpanded = whetherExpanded;
		onClick();
	}

	/**
	 * 点击事件必须调用
	 * 
	 * @return 是否展开
	 */
	public boolean onClick() {
		if (whetherExpanded) {
			ViewPropertyAnimator.animate(arrowRight).rotation(90).start();
		} else {
			ViewPropertyAnimator.animate(arrowRight).rotation(0).start();
		}
		whetherExpanded = !whetherExpanded;
		return !whetherExpanded;
	}

}
