package com.compus.netbus.dao;

import com.compus.netbus.bean.CloudMsg;
import com.compus.netbus.utils.Const;

public class CloudMsgDao extends BaseDao<CloudMsg> {

	public CloudMsg findCloudMsg() {

		String sql = "select * from " + Const.TABLE_CLOUD_MSG
				+ " where create_time=(select MAX(create_time) from "
				+ Const.TABLE_CLOUD_MSG + ")";

		CloudMsg cloudMsg = getBean(sql);
		return cloudMsg;
	}

}
