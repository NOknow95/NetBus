package com.compus.netbus.test;

import java.util.List;

import org.junit.Test;

import com.compus.netbus.bean.AppUpdateMsg;
import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.CloudMsg;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.dao.StationDao;
import com.compus.netbus.service.QueryService;
import com.compus.netbus.service.UserService;
import com.compus.netbus.utils.LogUtils;

public class TestQueryService {

	QueryService queryService = new QueryService();

	@Test
	public void testCloudMsg() {
		CloudMsg cloudMsg = queryService.findCloudMsg();
		LogUtils.f(cloudMsg);
	}

	@Test
	public void testCountWPOnStation() {
		Long countWPOnStation = queryService.countWPOnStation("s1");
		LogUtils.f(countWPOnStation);
	}

	@Test
	public void testFindAppUpdateMsg() {
		AppUpdateMsg updateMsg = queryService.findAppUpdateMsg();
		LogUtils.f(updateMsg);
	}

	@Test
	public void testFindAWPs() {
		WaitPot waitPot = queryService.findAWPById("1");
		LogUtils.f(waitPot);
	}

	@Test
	public void testFindWPsExcept() {
		List<WaitPot> list = queryService.findWPsExcept("-1");
		LogUtils.flist(list);
	}

	@Test
	public void testFindBusesExcept() {
		List<Bus> list = queryService.findBusesExcept("2");
		LogUtils.flist(list);
	}

	@Test
	public void testFindABus() {
		Bus bus = queryService.findABusById("2");
		LogUtils.f(bus);
	}

}
