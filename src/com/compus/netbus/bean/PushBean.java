package com.compus.netbus.bean;

import java.util.Date;

public class PushBean {
	public static final int TYPE_ROUTE=0;
	public static final int TYPE_UPDATE_BEAN=1;
	
	private int resultCode;
	private String resultJson;
	private Date date;
	
	public PushBean() {
	}

	public PushBean(int resultCode, String resultJson) {
		this.date = new Date(System.currentTimeMillis());
		this.resultCode = resultCode;
		this.resultJson = resultJson;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
