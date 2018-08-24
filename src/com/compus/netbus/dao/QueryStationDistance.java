package com.compus.netbus.dao;

import com.compus.netbus.bean.StationDistance;

public class QueryStationDistance extends BaseDao<StationDistance> {
	/**
	 * 查询站点之间的距离
	 * @param startWp
	 * @param endWp
	 * @return
	 */
	public int findWpToWpDistance(String startWp,String endWp) {
		String sql = "select distance from station_distance where startId=? and endId=?";
		
		return getBean(sql, startWp,endWp).getDistance();
		
	}
}
