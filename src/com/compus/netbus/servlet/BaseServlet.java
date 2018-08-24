package com.compus.netbus.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compus.netbus.utils.RespontHelper;

/**
 * 被其他setvlet继承
 * 
 * @author NOknow
 * 
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RespontHelper respontHelper;

	public void init() throws ServletException {
		respontHelper = new RespontHelper();
	};

	public RespontHelper getRespontHelper() {
		return respontHelper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String methodName = req.getParameter("action");
		Class cla = this.getClass();
		try {
			Method method = cla.getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}