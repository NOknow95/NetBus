package com.compus.netbus.bean;

import java.util.Date;

/**
 * Created by NOknow on 2018/05/19/0019.
 */

public class CloudMsg {
	private String projectState;
	private String msgTitle;
	private String msgContent;
	private Date create_time;

	public CloudMsg() {
	}

	public CloudMsg(String projectState, String msgTitle, String msgContent,
			Date create_time) {
		super();
		this.projectState = projectState;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.create_time = create_time;
	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "CloudMsg [projectState=" + projectState + ", msgTitle="
				+ msgTitle + ", msgContent=" + msgContent + ", create_time="
				+ create_time + "]";
	}

}