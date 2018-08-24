package com.compus.netbus.dao;

import com.compus.netbus.bean.AppUpdateMsg;
import com.compus.netbus.utils.Const;

public class AppUpdateMsgDao extends BaseDao<AppUpdateMsg> {

	public AppUpdateMsg findAUM() {
		String sql = "select * from " + Const.TABLE_APPUPDATEMSG
				+ " WHERE create_time=(SELECT MAX(create_time) FROM "
				+ Const.TABLE_APPUPDATEMSG + ");";
		AppUpdateMsg appUpdateMsg = getBean(sql);
		
		return appUpdateMsg;
	}

}
