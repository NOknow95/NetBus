package com.compus.netbus.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.compus.netbus.bean.ResponseEntity;
import com.compus.netbus.service.UserService;
import com.compus.netbus.utils.Const;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;

public class TestUserService {

	private UserService userService = new UserService();

	@Test
	public void testFeedback() {
		int result = userService.feedback("1", Const.TABLE_WAITPOT, "test_content.....");
		LogUtils.f(result);
	}
	@Test
	public void testLoginOut() {
		int resultCode = userService.loginOut("4");
		LogUtils.f("resultCode=" + resultCode);
	}

	@Test
	public void testLogin() {
		// String tableName = Const.TABLE_WAITPOT;
		String tableName = Const.TABLE_BUS;

		ResponseEntity responseEntity;

		responseEntity = userService.login(tableName, "22", "22");
		LogUtils.f(JsonUtils.objToJson(responseEntity));

	}

	@Test
	public void testRegist() {
		String tableName = Const.TABLE_WAITPOT;
		// String tableName = Const.TABLE_BUS;

		ResponseEntity responseEntity;

		responseEntity = userService.regist(tableName, "1", "1");
		LogUtils.f(JsonUtils.objToJson(responseEntity));
		//
		// responseEntity = userService
		// .regist(tableName, "wp_test", "wp_pwd_test");
		// LogUtils.f(responseEntity);
	}

}
