package com.compus.netbus.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compus.netbus.bean.ErrorEntity;
import com.compus.netbus.bean.ResponseEntity;
import com.compus.netbus.service.UserService;
import com.compus.netbus.utils.StringUtils;
//import com.sun.xml.internal.bind.v2.TODO;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	UserService userService = new UserService();

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("type");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		ResponseEntity responseEntity = userService.regist(tableName, username,
				pwd);

		getRespontHelper().toResponse(response, responseEntity);

	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tableName = null;
		String username = null;
		String pwd = null;

		tableName = request.getParameter("type");

		ResponseEntity responseEntity;

		if (StringUtils.isNullOrEmpty(tableName)) {
			responseEntity = new ResponseEntity();
			// 一般不会故意出现这个结果
			responseEntity.setErrorEntity(new ErrorEntity(-1,
					"type can not be empty or null", null));
		} else {
			username = request.getParameter("username");
			pwd = request.getParameter("pwd");

			responseEntity = userService.login(tableName, username, pwd);
		}
		getRespontHelper().toResponse(response, responseEntity);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 *            【成功：返回1】【 失败：返回0】【 出错：返回-1】
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void feedback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String userType = request.getParameter("userType");
		String content = request.getParameter("content");

		int result = 0;
		try {
			result = userService.feedback(id, userType, content);
		} catch (Exception e) {
			result = -1;
		}
		getRespontHelper().toResponse(response, result);
	}

	/**
	 * 登出
	 * 
	 * @param request
	 * @param response
	 *            【成功：返回1】【 失败：返回0】【 出错：返回-1】
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ResponseEntity responseEntity = new ResponseEntity();

		String busId = request.getParameter("id");

		int resultCode = userService.loginOut(busId);

		responseEntity.setAttachObject(resultCode);

		getRespontHelper().toResponse(response, responseEntity);

	}

	/**
	 * 注销
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void unregist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO 用户注销的操作

	}
}
