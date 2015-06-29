package com.leaven.mianjiao.bean;

import java.util.ArrayList;

public class GoodsListItemBean implements IGoodsListItem {
	private String imgUrl;
	private String goodName;
	private String goodPrice;
	private String distance;
	private String address;
	private String yearsOfRelease;
	private String goodWeight;

	protected GoodsListItemBean(String imgUrl, String goodName, String goodPrice, String distance, String address) {
		super();
		this.imgUrl = imgUrl;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.distance = distance;
		this.address = address;
		this.yearsOfRelease = "2014";
		this.goodWeight = "260";
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

	@Override
	public String getYearsOfRelease() {
		return yearsOfRelease;
	}

	@Override
	public String getWeight() {
		return goodWeight + "g";
	}

	public static ArrayList<GoodsListItemBean> getList(String searchkey) {
		ArrayList<GoodsListItemBean> list = new ArrayList<GoodsListItemBean>();
		for (int i = 0; i < 20; i++) {
			GoodsListItemBean item = new GoodsListItemBean(
					"http://img1.imgtn.bdimg.com/it/u=2128666655,1801503662&fm=21&gp=0.jpg", searchkey + i, "商品价格" + i,
					"距离" + i * 12, "地址" + i);
			list.add(item);
		}
		return list;
	}
}