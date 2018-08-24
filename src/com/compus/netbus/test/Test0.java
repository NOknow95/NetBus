package com.compus.netbus.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.junit.Test;

import com.compus.netbus.dao.QueryBusDao;
import com.compus.netbus.dao.QueryStationDistance;
import com.compus.netbus.dao.QueryWPDao;
import com.compus.netbus.service.QueryService;
import com.compus.netbus.utils.LogUtils;
import com.compus.netbus.utils.StringReverse;
import com.compus.netbus.utils.StringUtils;

public class Test0 {

	@Test
	public void test03() {
	}

	@Test
	public void test02() {
		Boolean paramValueBool;
		try {
			paramValueBool = Boolean.valueOf("");
			LogUtils.f(paramValueBool);
			paramValueBool = Boolean.valueOf("1");
			LogUtils.f(paramValueBool);
			paramValueBool = Boolean.valueOf("0");
			LogUtils.f(paramValueBool);
			paramValueBool = Boolean.valueOf("false");
			LogUtils.f(paramValueBool);
			paramValueBool = Boolean.valueOf("true");
			LogUtils.f(paramValueBool);
			paramValueBool = Boolean.valueOf("fsdffds");
			LogUtils.f(paramValueBool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test01() {
		String string = "";
		LogUtils.f(StringUtils.isNullOrEmpty(string));
		string = null;
		LogUtils.f(StringUtils.isNullOrEmpty(string));
		string = "11";
		LogUtils.f(StringUtils.isNullOrEmpty(string));
	}
	@Test
	public void test1() {
		QueryService qs = new QueryService();
		
//		int index = shortpath.indexOf("0-1");
//		int index1 = shortpath.indexOf(";", index+4);
//		String sp = shortpath.substring(index+4, index1);
//		System.out.println(sp);
		
//		StringReverse sr = new StringReverse();
//		System.out.println(sr.swapWords("i love you"));
		String begToend ="0-4";
		if(begToend=="1-0"||begToend=="2-0"||begToend=="3-0"
				   ||begToend=="4-0"||begToend=="5-0"||begToend=="6-0"
				   ||begToend=="2-1"||begToend=="3-1"||begToend=="4-1"
				   ||begToend=="5-1"||begToend=="6-1"||begToend=="3-2"
				   ||begToend=="4-2"||begToend=="5-2"||begToend=="6-2"
				   ||begToend=="4-3"||begToend=="5-3"||begToend=="6-3"
				   ||begToend=="5-4"||begToend=="6-4"||begToend=="6-5") {
					StringReverse sr =new StringReverse();
					begToend = sr.swapWords(begToend);
					String shortpath = qs.RoutePlanning1();
				    int index = shortpath.indexOf(begToend);
					int index1 = shortpath.indexOf(";", index+4);
					String sp = shortpath.substring(index+4, index1);
					System.out.println(sr.swapWords(sp));
				}else {
					String shortpath = qs.RoutePlanning1();
				        int index = shortpath.indexOf(begToend);
				        int index1 = shortpath.indexOf(";", index+4);
				        String sp = shortpath.substring(index+4, index1);
				        System.out.println(sp);
				}
	}
}
