package com.leaven.mianjiao.view;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 字体默认大小为16sp，颜色默认为default_blue
 * 
 * @author wjz
 *
 */
public class HeadLineView extends RelativeLayout {
	private static final int TITLE_TEXT_SIZE_DEFAULT = 16;
	private static final int TITLE_TEXT_COLOR_DEFAULT = R.color.default_blue;

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

	public HeadLineView(Context context, AttributeSet attrs, int defStyle) {
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

	public HeadLineView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	private TextView tvHeadLineTitle;

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_headline, this);
		tvHeadLineTitle = (TextView) findViewById(R.id.tvHeadLineTitle);
		tvHeadLineTitle.setText(TextUtils.isEmpty(titleStr) ? "" : titleStr);
		tvHeadLineTitle.setTextColor(textColor == 0 ? CommonUtils.getColorFromResource(getContext(),
				TITLE_TEXT_COLOR_DEFAULT) : textColor);
		tvHeadLineTitle.setTextSize(textSize);
	}

}
