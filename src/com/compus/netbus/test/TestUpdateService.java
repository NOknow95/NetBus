package com.compus.netbus.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.junit.Test;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.service.UpdateService;
import com.compus.netbus.utils.Const;
import com.compus.netbus.utils.LogUtils;

public class TestUpdateService {

	UpdateService updateService = new UpdateService();

	@Test
	public void testUpdateAWP() {
	}
}
