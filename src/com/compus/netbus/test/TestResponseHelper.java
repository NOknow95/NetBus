package com.compus.netbus.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.ResponseEntity;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;
import com.compus.netbus.utils.RespontHelper;

public class TestResponseHelper {
	RespontHelper respontHelper = new RespontHelper();

	@Test
	public void test() {
		Bus bus = new Bus();
		bus.setBusName("bus_01");
		bus.setOnline(true);
		String responseEntity = respontHelper.response(null, bus,
				RespontHelper.MODE_TEST);
		LogUtils.f(responseEntity);
		WaitPot waitPot = new WaitPot();
		waitPot.setWpId(15321);
		;
		responseEntity = respontHelper.response(null, null,
				RespontHelper.MODE_TEST);
		LogUtils.f(responseEntity);

		LogUtils.fline();

		ResponseEntity entity = JsonUtils.jsonToBean(responseEntity,
				ResponseEntity.class);
		LogUtils.f(entity);

	}
}
