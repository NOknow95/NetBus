package com.compus.netbus.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.compus.netbus.dao.UpdateDao;
import com.compus.netbus.utils.LogUtils;

public class UpdateService {

	UpdateDao updateDao = new UpdateDao();

	public <T> T updateAWPOrBus(Class<T> beanType, String id, String tableName,
			HttpServletRequest request) {

		Enumeration enum_ = request.getParameterNames();

		Map<String, Object> map = new HashMap<String, Object>();

		while (enum_.hasMoreElements()) {
			String paramName = (String) enum_.nextElement();
			if (paramName.equals("action") || paramName.equals("id")
					|| paramName.equals("type")) {
				continue;
			}
			String paramValueOld = request.getParameter(paramName);

			Object paramValue;
			if ("false".equals(paramValueOld) || "true".equals(paramValueOld)) {
				paramValue = Boolean.valueOf(paramValueOld);
			} else {
				paramValue = request.getParameter(paramName);
			}

			map.put(paramName, paramValue);
			LogUtils.f("paramName=" + paramName + ",paramValue=" + paramValue);
		}

		return updateDao.updateAWPOrBus(beanType, id, tableName, map, true);
	}

}
