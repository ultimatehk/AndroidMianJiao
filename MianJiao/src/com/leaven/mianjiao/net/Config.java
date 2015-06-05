/**
 * 网络相关配置类
 */
package com.leaven.mianjiao.net;

public class Config {
	protected  String SERVER_URL = "";
	protected static  String CHARSET = "utf-8";
	
	
	public String getSERVER_URL() {
		return SERVER_URL;
	}
	public void setSERVER_URL(String sERVER_URL) {
		SERVER_URL = sERVER_URL;
	}
	public String getCHARSET() {
		return CHARSET;
	}
	public void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}
	
	
	
		
}
