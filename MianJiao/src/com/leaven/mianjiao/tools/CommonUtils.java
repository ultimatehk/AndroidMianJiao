package com.leaven.mianxiao.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 一系列工具类
 * 
 * @author wjz
 *
 */
public class CommonUtils {
	/**
	 * 隐藏键盘
	 * 
	 * @param v
	 *            View 传入点击的view
	 */
	public static void hideSoftInputFromWindow(View v) {
		if (v == null) {
			return;
		}
		InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
		}
	}

	/**
	 * 传入金额，返回两位小数结尾的字符串
	 * 
	 * @param d
	 *            double 金额
	 * @return String 两位小数的字符串
	 */
	public static String formatDouble(double d) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return String.valueOf(decimalFormat.format(d));
	}

	/**
	 * dp(dip): device independent pixels(设备独立像素). 不同设备有不同的显示效果,这个和设备硬件有关，一般我们为了支持WVGA、HVGA和QVGA 推荐使用这个，不依赖像素。
	 * dp也就是dip，这个和sp基本类似。如果设置表示长度、高度等属性时可以使用dp 或sp。但如果设置字体，需要使用sp。dp是与密度无关，sp除了与密度无关外，还与scale无关。如果屏幕密度为160，这时dp和sp和px是一
	 * 样的。1dp=1sp=1px，但如果使用px作单位，如果屏幕大小不变（假设还是3.2寸），而屏幕密度变成了320。那么原来TextView的宽度
	 * 设成160px，在密度为320的3.2寸屏幕里看要比在密度为160的3.2寸屏幕上看短了一半。但如果设置成160dp或160sp的话。系统会自动 将width属性值设置成320px的。也就是160 * 320 /
	 * 160。其中320 / 160可称为密度比例因子。也就是说，如果使用dp和sp，系统会根据屏幕密度的变化自动进行转换。 px: pixels(像素).
	 * 不同设备显示效果相同，一般我们HVGA代表320x480像素，这个用的比较多。 pt: point，是一个标准的长度单位，1pt＝1/72英寸，用于印刷业，非常简单易用； sp: scaled pixels(放大像素).
	 * 主要用于字体显示best for textsize。
	 */

	/**
	 * 将px值转换为dp值，保证尺寸大小不变
	 * 
	 * @param context
	 *            Context类型，为了获得getDisplayMetrics().density
	 * @param pxValue
	 *            px值
	 * @return int dp值
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param context
	 *            Context类型，为了获得getDisplayMetrics().density
	 * @param dpValue
	 *            dp值
	 * @return int px值
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param context
	 *            Context类型，为了获得getDisplayMetrics().density
	 * @param pxValue
	 *            px值
	 * @return int sp值
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param context
	 *            Context类型，为了获得getDisplayMetrics().density*
	 * @param spValue
	 *            sp值
	 * @return px值
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param url
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> getUrlParams(String url) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(url);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}

	/**
	 * 
	 * @param src
	 * @param num
	 * @return 浮点数保留指定位数的小数
	 */
	public static double round(double src, int num) {
		try {
			return round(src, num, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return src;
		}
	}

	/**
	 * 
	 * @param src
	 * @param num
	 * @return 浮点数保留指定位数的小数
	 */
	public static double round(double src, int num, int roundMode) {
		BigDecimal bd = new BigDecimal(src);
		return bd.setScale(num, roundMode).doubleValue();
	}

	/**
	 * 得到Color，从资源上获得
	 * 
	 * @param context
	 * @param rId
	 *            资源id
	 * @return
	 */
	public static int getColorFromResource(Context context, int rId) {
		if (context == null) {
			return 0;
		} else {
			return context.getResources().getColor(rId);
		}
	}

}
