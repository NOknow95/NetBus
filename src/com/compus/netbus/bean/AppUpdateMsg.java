package com.compus.netbus.bean;

/**
 * Created by NOknow on 2018/05/19/0019.
 */

public class AppUpdateMsg{
    private Integer msgFlag;
    private String versionStr;
    private Integer versionCode;
    private String updateTextTip;
    private String downloadUrl;
    private Boolean force;

    public AppUpdateMsg() {

    }

	public AppUpdateMsg(Integer msgFlag, String versionStr,
			Integer versionCode, String updateTextTip, String downloadUrl,
			Boolean force) {
		super();
		this.msgFlag = msgFlag;
		this.versionStr = versionStr;
		this.versionCode = versionCode;
		this.updateTextTip = updateTextTip;
		this.downloadUrl = downloadUrl;
		this.force = force;
	}

	public Integer getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(Integer msgFlag) {
		this.msgFlag = msgFlag;
	}

	public String getVersionStr() {
		return versionStr;
	}

	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getUpdateTextTip() {
		return updateTextTip;
	}

	public void setUpdateTextTip(String updateTextTip) {
		this.updateTextTip = updateTextTip;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Boolean getForce() {
		return force;
	}

	public void setForce(Boolean force) {
		this.force = force;
	}

	@Override
	public String toString() {
		return "AppUpdateMsg [msgFlag=" + msgFlag + ", versionStr="
				+ versionStr + ", versionCode=" + versionCode
				+ ", updateTextTip=" + updateTextTip + ", downloadUrl="
				+ downloadUrl + ", force=" + force + "]";
	}
    
}