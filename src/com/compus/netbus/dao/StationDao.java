package com.compus.netbus.dao;

import com.compus.netbus.bean.Station;
import com.compus.netbus.utils.Const;

public class StationDao extends BaseDao<Station> {

	public Long countWP(String stationId) {
		String sql = "SELECT COUNT(*) FROM " + Const.TABLE_WAITPOT
				+ " WHERE stationId=? and wpState>0";
		Long count = (Long) getAValue(sql, stationId);
		return count;
	}
}
