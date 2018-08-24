package com.compus.netbus.service;

import com.compus.netbus.bean.ErrorEntity;
import com.compus.netbus.bean.FeedBack;
import com.compus.netbus.bean.ResponseEntity;
import com.compus.netbus.dao.UserDao;
import com.compus.netbus.utils.LogUtils;
import com.compus.netbus.utils.LoginUtils;
import com.compus.netbus.utils.StringUtils;

public class UserService {

	UserDao userDao = new UserDao();

	public ResponseEntity regist(String tableName, String username, String pwd) {

		ResponseEntity responseEntity = new ResponseEntity();

		ErrorEntity errorEntity = null;

		String checkResult = LoginUtils.check(username, pwd);

		if (!StringUtils.isNullOrEmpty(checkResult)) {
			// 验证未通过
			errorEntity = new ErrorEntity(0, checkResult, null);
			responseEntity.setErrorEntity(errorEntity);
			LogUtils.f("验证未通过");
		} else {
			// 验证通过
			int id = userDao.regist(tableName, username, pwd);
			if (id > 0) {
				// 注册成功
				responseEntity.setAttachObject(id);
				LogUtils.f("注册成功");
			} else {
				// 用户名已存在
				LogUtils.f("用户名已存在");
				errorEntity = new ErrorEntity(0, "用户名已存在，请重新输入。", null);
				responseEntity.setErrorEntity(errorEntity);
			}
		}

		return responseEntity;
	}

	public ResponseEntity login(String tableName, String username, String pwd) {
		ResponseEntity responseEntity = new ResponseEntity();

		ErrorEntity errorEntity = null;

		// 验证表单是否为空
		String checkResult = LoginUtils.check(username, pwd);

		if (!StringUtils.isNullOrEmpty(checkResult)) {
			LogUtils.f("登录  表单验证未通过");
			// 表单验证未通过
			errorEntity = new ErrorEntity(0, checkResult, null);
			responseEntity.setErrorEntity(errorEntity);
		} else {
			// 表单验证通过
			Object object = userDao.login(tableName, username, pwd);
			if (object != null) {
				// 登录成功
				responseEntity.setAttachObject(object);
				LogUtils.f("登录成功");
			} else {
				// 登录失败--用户名不存在或密码错误
				LogUtils.f("登录失败");
				errorEntity = new ErrorEntity(0, "用户名不存在或密码错误，请重新输入。", null);
				responseEntity.setErrorEntity(errorEntity);
			}
		}

		return responseEntity;
	}

	/**
	 * 【成功：返回1】【 失败：返回0】【 出错：返回-1】
	 * 
	 * @param busId
	 * @return
	 */
	public int loginOut(String busId) {
		return userDao.loginOut(busId);
	}

	public int feedback(String id,String userType,String content) {
		return userDao.feedback(new FeedBack(id, userType, content));
	}
}
