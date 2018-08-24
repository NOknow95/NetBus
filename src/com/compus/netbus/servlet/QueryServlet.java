package com.compus.netbus.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compus.netbus.bean.AppUpdateMsg;
import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.CloudMsg;
import com.compus.netbus.bean.PushBean;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.dao.QueryBusDao;
import com.compus.netbus.service.QueryService;
import com.compus.netbus.utils.JPush;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;
import com.compus.netbus.utils.RoutePlan;
import com.compus.netbus.utils.StringReverse;

//import com.sun.xml.internal.bind.v2.TODO;

/**
 * Servlet implementation class QueryServlet
 */
public class QueryServlet extends BaseServlet {

	QueryService queryService = new QueryService();
	private static Integer cacheTime = 10 * 1000;
	private static Integer delay = 1000;
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	Calendar c = Calendar.getInstance();
	Timer timer = null;

	/**
	 * 查找除了exceptId之外的所有Bus
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void busExcept(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String exceptId = request.getParameter("id");
		LogUtils.f("busExcept:exceptId=" + exceptId);

		List<Bus> list = queryService.findBusesExcept(exceptId);
		LogUtils.flist(list);

		getRespontHelper().toResponse(response, list);
	}

	/**
	 * 查找除wpId为exceptId之外的所有wp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void wpExcept(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String exceptId = request.getParameter("id");
		LogUtils.f("wpExcept:exceptId=" + exceptId);

		List<WaitPot> list = queryService.findWPsExcept(exceptId);
		LogUtils.flist(list);

		getRespontHelper().toResponse(response, list);
	}

	/**
	 * 根据id找bus
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void aBus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busId = request.getParameter("id");
		LogUtils.f("aBus:busId=" + busId);

		Bus bus = queryService.findABusById(busId);

		getRespontHelper().toResponse(response, bus);
	}

	/**
	 * 根据id查询某个station的等待人数
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void stationWPCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String stationId = request.getParameter("id");

		Long count = queryService.countWPOnStation(stationId);

		getRespontHelper().toResponse(response, count);
	}

	/**
	 * 返回在该station的wp的list
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void stationWPList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO 返回在该station的wp的list

		String stationId = request.getParameter("id");

		Long count = queryService.countWPOnStation(stationId);

		getRespontHelper().toResponse(response, count);
	}

	/**
	 * 根据id找wp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void aWP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String wpId = request.getParameter("id");
		LogUtils.f("aWP:wpId=" + wpId);

		WaitPot waitPot = queryService.findAWPById(wpId);

		getRespontHelper().toResponse(response, waitPot);
	}

	/**
	 * 查询AppUpdateMsg
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void appUpdateMsg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AppUpdateMsg appUpdateMsg = queryService.findAppUpdateMsg();
		LogUtils.f(appUpdateMsg);

		getRespontHelper().toResponse(response, appUpdateMsg);
	}

	protected void cloudMsg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CloudMsg cloudMsg = queryService.findCloudMsg();
		LogUtils.f(cloudMsg);

		getRespontHelper().toResponse(response, cloudMsg);
	}

	/**
	 * 路线规划(基于人数的规划）
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryRoute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (timer != null) {
			LogUtils.f("已经正在自动推送...");
			return;
		}
		timer = new Timer();

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				cacheTime = ((int) (30 * 1000 + Math.random() * 10));
				queryService.RoutePlanning();
				Long total2 = queryService.countWPOnStation("s2");
				Long total3 = queryService.countWPOnStation("s3");
				Long total4 = queryService.countWPOnStation("s4");
				Long total5 = queryService.countWPOnStation("s5");
				Long total6 = queryService.countWPOnStation("s6");
				Long total7 = queryService.countWPOnStation("s7");
				RoutePlan rp = new RoutePlan();
				rp.init();
				rp.add(0, 1, total2);
				rp.add(1, 2, total3);
				rp.add(2, 3, total4);
				rp.add(3, 4, total5);
				rp.add(4, 5, total6);
				rp.add(5, 6, total7);
				rp.spfa(0);
				Long max = (long) 0;
				int j = 0;
				for (Long i : rp.getDis()) {
					if (i > max) {
						j++;
						max = i;
					}
				}
				rp.print(j);
				String str = "";
				for (Object i : rp.getLists()) {
					str += i + ",";
				}
				String string = str.substring(0, str.length() - 1);
				PushBean pushBean = new PushBean(PushBean.TYPE_ROUTE, string);
				JPush.sendPushMessageAll(JsonUtils.beanToJson(pushBean,
						PushBean.class));
				LogUtils.f(dateFormat.format(new Date(System
						.currentTimeMillis())));
				int result = new Date(System.currentTimeMillis()).compareTo(c
						.getTime());
				if (result > 0) {
					timer.cancel();
				}
			}
		}, delay, cacheTime);

	}

	protected void cancelQueryRoute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (timer != null) {
			timer.cancel();
			LogUtils.f("取消推送路线规划.");
			timer = null;
		}
	}
	/**
	 * 路线规划（基于路径的规划1）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */ 
	
	protected void queryRoute1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				String startStation = request.getParameter("startId");
				String endStation = request.getParameter("endId");
				String begToend = startStation+"-"+endStation;
				if(begToend=="1-0"||begToend=="2-0"||begToend=="3-0"
				   ||begToend=="4-0"||begToend=="5-0"||begToend=="6-0"
				   ||begToend=="2-1"||begToend=="3-1"||begToend=="4-1"
				   ||begToend=="5-1"||begToend=="6-1"||begToend=="3-2"
				   ||begToend=="4-2"||begToend=="5-2"||begToend=="6-2"
				   ||begToend=="4-3"||begToend=="5-3"||begToend=="6-3"
				   ||begToend=="5-4"||begToend=="6-4"||begToend=="6-5") {
					StringReverse sr =new StringReverse();
					begToend = sr.swapWords(begToend);
				    String shortpath = queryService.RoutePlanning1();
				    int index = shortpath.indexOf(begToend);
					int index1 = shortpath.indexOf(";", index+4);
					String sp = shortpath.substring(index+4, index1);
					sr.swapWords(sp);
					getRespontHelper().toResponse(response, sp);
				}else {
						String shortpath = queryService.RoutePlanning1();
				        int index = shortpath.indexOf(begToend);
				        int index1 = shortpath.indexOf(";", index+4);
				        String sp = shortpath.substring(index+4, index1);
				        getRespontHelper().toResponse(response, sp);	
				}
						
	}
	/**
	 * 路线规划（基于路径的规划2）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryRoute2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String startStation = request.getParameter("startId");
		String endStation = request.getParameter("endId");
		QueryBusDao qbd = new QueryBusDao();
		String str1 = qbd.RoutePlanning2(Integer.parseInt(startStation), Integer.parseInt(endStation));
		String string = str1.substring(0, str1.length() - 1);
		getRespontHelper().toResponse(response, string);	
	}
}
