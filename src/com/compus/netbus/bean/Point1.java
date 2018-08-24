package com.compus.netbus.bean;

public class Point1 {
	private int from;// 用户出发位置
	private int to;// 用户到达目的
	private Long totalPeople; // 出发人数

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public Long getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Long totalPeople) {
		this.totalPeople = totalPeople;
	}

}
