package com.compus.netbus.utils;

public class LoginUtils {

	public static String check(String username, String pwd) {
		String tipString = null;
		if (StringUtils.isNullOrEmpty(username)) {
			tipString = "用户名不能为空！";
		}

		if (StringUtils.isNullOrEmpty(pwd) && tipString == null) {
			tipString = "密码不能为空！";
		}

		return tipString;
	}
}
