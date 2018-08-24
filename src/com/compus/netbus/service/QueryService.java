package com.compus.netbus.service;

import java.util.List;

import com.compus.netbus.bean.AppUpdateMsg;
import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.CloudMsg;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.dao.AppUpdateMsgDao;
import com.compus.netbus.dao.CloudMsgDao;
import com.compus.netbus.dao.QueryBusDao;
import com.compus.netbus.dao.QueryWPDao;
import com.compus.netbus.dao.StationDao;

public class QueryService {

	private QueryBusDao queryBusDao = new QueryBusDao();
	private QueryWPDao queryWPDao = new QueryWPDao();
	private AppUpdateMsgDao appUpdateMsgDao = new AppUpdateMsgDao();
	private StationDao stationDao = new StationDao();
	private CloudMsgDao cloudMsgDao = new CloudMsgDao();

	public List<Bus> findBusesExcept(String exceptId) {
		return queryBusDao.findBusesExcept(exceptId);
	}

	public Bus findABusById(String budId) {
		return queryBusDao.findABusById(budId);
	}

	public List<WaitPot> findWPsExcept(String exceptId) {
		return queryWPDao.queryWPsExcept(exceptId);
	}

	public WaitPot findAWPById(String wpId) {
		return queryWPDao.findAWPById(wpId);
	}

	public AppUpdateMsg findAppUpdateMsg() {
		return appUpdateMsgDao.findAUM();
	}

	public Long countWPOnStation(String stationId) {
		return stationDao.countWP(stationId);
	}

	public CloudMsg findCloudMsg() {
		return cloudMsgDao.findCloudMsg();
	}
	/**
	 * 路线规划(基于人数的规划）
	 */
	public void RoutePlanning() {
		queryBusDao.RoutePlanning();
	}
	/**
	 *  路线规划（基于路径的规划）
	 */
	public String RoutePlanning1() {
		return queryBusDao.RoutePlanning1();
	}
}
