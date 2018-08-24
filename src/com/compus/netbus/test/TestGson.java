package com.compus.netbus.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;

public class TestGson {

	@Test
	public void test02() {
		List<WaitPot> wpList = new ArrayList<WaitPot>();
		for (int i = 0; i < 2; i++) {
			WaitPot waitPot = new WaitPot();
			waitPot.setWpName("wp_name_" + i);
			wpList.add(waitPot);
		}
		String wpListJson = JsonUtils.listToJson(wpList);
		LogUtils.f(wpListJson);

		List<WaitPot> jsonToBean = JsonUtils.jsonToList(wpListJson,
				WaitPot.class);
		LogUtils.flist(jsonToBean);
		LogUtils.fline();

		List<Bus> busList = new ArrayList<Bus>();
		for (int i = 0; i < 2; i++) {
			Bus bus = new Bus();
			bus.setBusName("wp_name_" + i);
			busList.add(bus);
		}
		String busListJson = JsonUtils.listToJson(busList);
		LogUtils.f(busListJson);

		List<Bus> jsonToBusBean = JsonUtils.jsonToList(busListJson, Bus.class);
		LogUtils.flist(jsonToBusBean);
	}

	@Test
	public void test01() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			list.add("str___" + i);
		}
		String json = JsonUtils.listToJson(list);
		LogUtils.f(json);

		LogUtils.f(JsonUtils.objToJson(list));

		Bus bus = new Bus();
		bus.setBusId(1);
		String busToJson = JsonUtils.beanToJson(bus, Bus.class);
		LogUtils.f(busToJson);

		Bus bus2 = JsonUtils.jsonToBean(busToJson, Bus.class);
		LogUtils.f(bus2);

		bus.setBusName("bus_name1");
		String objToJson = JsonUtils.objToJson(bus);
		LogUtils.f(objToJson);

		Bus bus3 = JsonUtils.jsonToBean(objToJson, Bus.class);
		LogUtils.f(bus3);
	}

}
