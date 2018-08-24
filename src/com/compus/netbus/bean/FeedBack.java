package com.compus.netbus.bean;

import java.sql.Date;

public class FeedBack {
	private String id;
	private String userType;
	private String content;
	private Date create_time;

	public FeedBack() {

	}

	public FeedBack(String id, String userType, String content) {
		super();
		this.id = id;
		this.userType = userType;
		this.content = content;
		this.create_time = new Date(System.currentTimeMillis());
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
