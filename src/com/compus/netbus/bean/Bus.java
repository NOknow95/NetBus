package com.compus.netbus.bean;

import com.compus.netbus.utils.LogUtils;

/**
 * Created by NOknow on 2018/05/19/0019.
 */

public class Bus {
	private int busId;// bus的唯一标识
	private String busName;// 名字
	private String busPwd;// 登录密码
	private int busState;// 先留着
	private double latitude;// 纬度
	private double longitude;// 经度
	private double orientation;// 方向
	private int zIndex;// 在地图的哪个图层
	private int seatsTotal;// 座位总数
	private int seatsLeft;// 剩余座位
	private float speed;// 速度
	private boolean bookable;// bus是否可接受预约
	private boolean valied;// bus是否有效（用户注销了则为无效）
	private boolean online;// 是否是登录状态
	private boolean onMoving;// 是否正在移动

	public Bus() {
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusPwd() {
		return busPwd;
	}

	public void setBusPwd(String busPwd) {
		this.busPwd = busPwd;
	}

	public int getBusState() {
		return busState;
	}

	public void setBusState(int busState) {
		this.busState = busState;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getSeatsTotal() {
		return seatsTotal;
	}

	public void setSeatsTotal(int seatsTotal) {
		this.seatsTotal = seatsTotal;
	}

	public int getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isBookable() {
		return bookable;
	}

	public void setBookable(boolean bookable) {
		this.bookable = bookable;
	}

	public boolean isValied() {
		return valied;
	}

	public void setValied(boolean valied) {
		this.valied = valied;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isOnMoving() {
		return onMoving;
	}

	public void setOnMoving(boolean onMoving) {
		this.onMoving = onMoving;
	}

	public Bus(int busId, String busName, String busPwd, int busState,
			double latitude, double longitude, double orientation, int zIndex,
			int seatsTotal, int seatsLeft, float speed, boolean bookable,
			boolean valied, boolean online, boolean onMoving) {
		super();
		this.busId = busId;
		this.busName = busName;
		this.busPwd = busPwd;
		this.busState = busState;
		this.latitude = latitude;
		this.longitude = longitude;
		this.orientation = orientation;
		this.zIndex = zIndex;
		this.seatsTotal = seatsTotal;
		this.seatsLeft = seatsLeft;
		this.speed = speed;
		this.bookable = bookable;
		this.valied = valied;
		this.online = online;
		this.onMoving = onMoving;
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", busPwd="
				+ busPwd + ", busState=" + busState + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", orientation=" + orientation
				+ ", zIndex=" + zIndex + ", seatsTotal=" + seatsTotal
				+ ", seatsLeft=" + seatsLeft + ", speed=" + speed
				+ ", bookable=" + bookable + ", valied=" + valied + ", online="
				+ online + ", onMoving=" + onMoving + "]";
	}

}