package com.leaven.mianjiao.bean;

public interface IOrderItem extends IOrderInfoItem {
	public abstract int getGoodCount();

	public abstract void updateCount(int count);

}
