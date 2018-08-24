package com.compus.netbus.bean;

/**
 * Created by NOknow on 2018/05/19/0019.
 */

public class WaitPot {
	/**
	 * 默认
	 */
	public static final int UOD = 0;//
	/**
	 * 等待
	 */
	public static final int UOW = 1;//
	/**
	 * 被接单
	 */
	public static final int UOS = 2;//
	/**
	 * 乘车中
	 */
	public static final int UOB = 3;//

	private int wpId;//
	private String wpName;// 用户名
	private String wpPwd;//
	private int wpState;// 状态
	private int zIndex;//
	private double latitude;// 纬度
	private double longitude;// 经度
	private boolean valied;// 帐号是否有效
	private String busId;// 被接单的busId
	private String stationId;//
	private String endStationId;// 被接单的busId

	public WaitPot() {

	}

	public WaitPot(int wpId, String wpName, String wpPwd, int wpState,
			int zIndex, double latitude, double longitude, boolean valied,
			String busId, String stationId) {
		super();
		this.wpId = wpId;
		this.wpName = wpName;
		this.wpPwd = wpPwd;
		this.wpState = wpState;
		this.zIndex = zIndex;
		this.latitude = latitude;
		this.longitude = longitude;
		this.valied = valied;
		this.busId = busId;
		this.stationId = stationId;
	}

	public int getWpId() {
		return wpId;
	}

	public void setWpId(int wpId) {
		this.wpId = wpId;
	}

	public String getWpName() {
		return wpName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public String getWpPwd() {
		return wpPwd;
	}

	public void setWpPwd(String wpPwd) {
		this.wpPwd = wpPwd;
	}

	public int getWpState() {
		return wpState;
	}

	public void setWpState(int wpState) {
		this.wpState = wpState;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
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

	public boolean isValied() {
		return valied;
	}

	public void setValied(boolean valied) {
		this.valied = valied;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public static int getUod() {
		return UOD;
	}

	public static int getUow() {
		return UOW;
	}

	public static int getUos() {
		return UOS;
	}

	public static int getUob() {
		return UOB;
	}

	public String getEndStationId() {
		return endStationId;
	}

	public void setEndStationId(String endStationId) {
		this.endStationId = endStationId;
	}

	@Override
	public String toString() {
		return "WaitPot [wpId=" + wpId + ", wpName=" + wpName + ", wpPwd="
				+ wpPwd + ", wpState=" + wpState + ", zIndex=" + zIndex
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", valied=" + valied + ", busId=" + busId + ", stationId="
				+ stationId + "]";
	}

}