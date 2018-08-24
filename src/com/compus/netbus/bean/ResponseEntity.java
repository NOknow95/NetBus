package com.compus.netbus.bean;

import java.util.Date;

/**
 * 
 * @author NOknow
 * 
 */
public class ResponseEntity {
	
	private Date createTime;
	private ErrorEntity errorEntity;
	private Object attachObject;

	public ResponseEntity(Object attachObject) {
		super();
		createTime = new Date(System.currentTimeMillis());
		this.attachObject = attachObject;
	}

	public ResponseEntity() {
		createTime = new Date(System.currentTimeMillis());
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public ErrorEntity getErrorEntity() {
		return errorEntity;
	}

	public void setErrorEntity(ErrorEntity errorEntity) {
		this.errorEntity = errorEntity;
	}

	public Object getAttachObject() {
		return attachObject;
	}

	public void setAttachObject(Object attachObject) {
		this.attachObject = attachObject;
	}

	@Override
	public String toString() {
		return "ResponseEntity [createTime=" + createTime + ", errorEntity="
				+ (errorEntity == null ? "null" : errorEntity.toString())
				+ ", attachObject="
				+ (attachObject == null ? "null" : attachObject.toString())
				+ "]";
	}
}
