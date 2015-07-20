package com.leaven.mianjiao.bean;

import java.util.ArrayList;

public class RedPacketItem implements IRedPacketItem {

	private String title;
	private String description;

	public static ArrayList<RedPacketItem> getList() {
		ArrayList<RedPacketItem> list = new ArrayList<RedPacketItem>();
		list.add(new RedPacketItem("￥ 1", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 2", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 3", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 4", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 5", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 6", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 7", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 10", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 1", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 2", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 3", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 4", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 5", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 6", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 7", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 10", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 1", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 2",
				"我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态我就是看看状态"));
		list.add(new RedPacketItem("￥ 3", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 4", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 5", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 6", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 7", "我就是看看状态"));
		list.add(new RedPacketItem("￥ 10", "我就是看看状态"));
		return list;
	}

	public RedPacketItem(String title, String description) {
		this.title = title;
		this.description = description;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
