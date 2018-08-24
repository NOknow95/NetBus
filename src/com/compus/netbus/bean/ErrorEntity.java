package com.compus.netbus.bean;

/**
 * Created by NOknow on 2018/05/19/0019.
 */

public class ErrorEntity {
	private int errorCode;
	private String errorTip;
	private Object errorObj;

	public ErrorEntity() {
	}

	public ErrorEntity(int errorCode, String errorTip, Object errorObj) {
		this.errorCode = errorCode;
		this.errorTip = errorTip;
		this.errorObj = errorObj;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorTip() {
		return errorTip;
	}

	public void setErrorTip(String errorTip) {
		this.errorTip = errorTip;
	}

	public Object getErrorObj() {
		return errorObj;
	}

	public void setErrorObj(Object errorObj) {
		this.errorObj = errorObj;
	}

	@Override
	public String toString() {
		return "ErrorEntity{" + "errorCode=" + errorCode + ", errorTip='"
				+ errorTip + '\'' + ", errorObj=" + errorObj + '}';
	}
}