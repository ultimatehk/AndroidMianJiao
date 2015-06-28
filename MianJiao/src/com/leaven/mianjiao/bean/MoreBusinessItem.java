package com.leaven.mianjiao.bean;

import java.util.ArrayList;

public class MoreBusinessItem implements IMoreBusinessItem {
	private String icon;
	private String businessName;
	private String distance;
	private String goodPrice;

	public MoreBusinessItem(String icon, String businessName, String distance, String goodPrice) {
		super();
		this.icon = icon;
		this.businessName = businessName;
		this.distance = distance;
		this.goodPrice = goodPrice;
	}

	@Override
	public String getBusinessIcon() {
		return icon;
	}

	@Override
	public String getBusinessName() {
		return businessName;
	}

	@Override
	public String getDistance() {
		return distance;
	}

	@Override
	public String getGoodPrice() {
		return goodPrice;
	}

	public static ArrayList<MoreBusinessItem> getList() {
		ArrayList<MoreBusinessItem> list = new ArrayList<MoreBusinessItem>();
		for (int i = 0; i < 20; i++) {
			list.add(new MoreBusinessItem("http://img2.imgtn.bdimg.com/it/u=3624845512,284636386&fm=21&gp=0.jpg",
					"商户名称" + i, "距离" + i * 12 + "米", "5800"));
		}
		return list;
	}

}
