package com.leaven.mianjiao.bean;

public class OrderItemBean implements IOrderItem {
	private String imgUrl;
	private String goodName;
	private String goodPrice;

	private int count;

	public OrderItemBean(IOrderInfoItem good) {
		imgUrl = good.getImgURL();
		goodName = good.getGoodName();
		goodPrice = good.getGoodPrice();
		count = 1;
	}

	@Override
	public void updateCount(int count) {
		this.count = count;
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
	public int getGoodCount() {
		return count;
	}
}