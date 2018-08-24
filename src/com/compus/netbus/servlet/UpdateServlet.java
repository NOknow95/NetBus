package com.compus.netbus.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.service.UpdateService;
import com.compus.netbus.utils.Const;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends BaseServlet {

	UpdateService updateService = new UpdateService();

	protected void busOrWP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// UpdateServlet?action=aBus
		String tableName = request.getParameter("type");
		String id = request.getParameter("id");

//		Object beanNew = updateService.updateAWPOrBus(tableName.toUpperCase()
//				.equals(Const.TABLE_BUS) ? Bus.class : WaitPot.class, id,
//				tableName, request);
		
		Object beanNew;
		if (tableName.toUpperCase().equals(Const.TABLE_BUS)) {
			beanNew=updateService.updateAWPOrBus(Bus.class, id, tableName, request);
		}else {
			beanNew=updateService.updateAWPOrBus(WaitPot.class, id, tableName, request);
		}

		getRespontHelper().toResponse(response, beanNew);
	}
}
