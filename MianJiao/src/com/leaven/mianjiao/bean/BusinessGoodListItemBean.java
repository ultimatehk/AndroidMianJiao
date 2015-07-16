package com.leaven.mianjiao.bean;

import java.util.ArrayList;

public class BusinessGoodListItemBean implements IOrderInfoItem {
	private String imgUrl;
	private String goodName;
	private String goodPrice;

	protected BusinessGoodListItemBean(String imgUrl, String goodName, String goodPrice) {
		super();
		this.imgUrl = imgUrl;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
	}

	@Override
	public String getImgURL() {
		return imgUrl;
	}

	@Override
	public String getGoodName() {
		return goodName;
	}

	@Override
	public String getGoodPrice() {
		return goodPrice;
	}

	public static ArrayList<BusinessGoodListItemBean> getList() {
		ArrayList<BusinessGoodListItemBean> list = new ArrayList<BusinessGoodListItemBean>();
		for (int i = 0; i < 20; i++) {
			BusinessGoodListItemBean item = new BusinessGoodListItemBean(
					"http://img.funshion.com/pictures/320/818/8/3208188.jpg", "乱七八糟商品名" + i, "商品价格" + i);
			list.add(item);
		}
		return list;
	}
}