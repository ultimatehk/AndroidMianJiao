/**
 * 封装Log类，调试时使用
 */
package com.leaven.mianjiao.tools;

public class DebugLog {
	    //设置若干日志标签
		public static final String LOG_Net = "NET----->";
		
		//设置Debug状态的开关
		private static boolean isDebugLog = false;
		
		//设置是否处于调试状态
		public static void setDebugLog(boolean log) {
			isDebugLog = log;
		}

		public static void d(String tag, String msg) {
			if (isDebugLog )
				android.util.Log.d(tag, msg);
		}

		public static void i(String tag, String msg) {
			if (isDebugLog )
				android.util.Log.i(tag, msg);
		}

		public static void e(String tag, String msg) {
			if (isDebugLog )
				android.util.Log.e(tag, msg);
		}
		
		public static void e(String tag, String msg,Throwable tr) {
			e(tag, msg);
		}

		public static void v(String tag, String msg) {
			if (isDebugLog )
				android.util.Log.v(tag, msg);
		}

		public static void w(String tag, String msg) {
			if (isDebugLog )
				android.util.Log.w(tag, msg);
		}
	

}
