package com.leaven.mianjiao.bean;

import java.util.ArrayList;

public class HomeItemBean implements IHomeItem {
	public String imgUrl;
	public String goodName;
	public String goodPrice;
	public String distance;
	public String address;

	protected HomeItemBean(String imgUrl, String goodName, String goodPrice, String distance, String address) {
		super();
		this.imgUrl = imgUrl;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.distance = distance;
		this.address = address;
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

	@Override
	public String getDistance() {
		return distance;
	}

	@Override
	public String getAddress() {
		return address;
	}

	public static ArrayList<HomeItemBean> getList() {
		ArrayList<HomeItemBean> list = new ArrayList<HomeItemBean>();
		for (int i = 0; i < 20; i++) {
			HomeItemBean item = new HomeItemBean(
					"http://f.hiphotos.baidu.com/zhidao/pic/item/faf2b2119313b07ea7b89cff0cd7912396dd8cc4.jpg", "商品名称"
							+ i, "商品价格" + i, "距离" + i * 12, "地址" + i);
			list.add(item);
		}
		return list;
	}
}