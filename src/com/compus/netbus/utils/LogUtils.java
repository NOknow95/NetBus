package com.compus.netbus.utils;

import java.util.Date;
import java.util.List;
/**
 * 将数据库的一条记录以对象形式显示到控制台
 * @author asus
 *
 */
public class LogUtils {

	public static void f(Object o) {
		if (o == null) {
			System.out.println("[the object is null]");
			return;
		}
		System.out.println("[[" + new Date().toLocaleString() + "]:" + o + "]");
	}

	public static <T> void flist(List<T> list) {
		f("-----------------print a list begin--------------------");
		if (list == null) {
			LogUtils.f("list is null.");
		}

		if (list != null && list.isEmpty()) {
			f("list is not null ,but is empty.");
		}
		for (T t : list) {
			System.out.println(t);
		}
		f("-----------------print a list end--------------------");
	}

	public static void fline() {
		System.out
				.println("------------------------------------------------------------------------------------------------------------------");
	}

}
