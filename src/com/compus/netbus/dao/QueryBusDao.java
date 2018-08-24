package com.compus.netbus.dao;

import java.util.List;
import java.util.Stack;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.PointIfo;
import com.compus.netbus.utils.Const;
import com.compus.netbus.utils.Dijkstra;
import com.compus.netbus.utils.GraphMatrixOperator;
import com.compus.netbus.utils.LogUtils;
import com.compus.netbus.utils.RoutePlan;

public class QueryBusDao extends BaseDao<Bus> {
	/**
	 * 查询所有bus，除了busId为exceptId之外
	 * 
	 * @param exceptId
	 * @return
	 */
	public List<Bus> findBusesExcept(String exceptId) {
		String sql = "select *" + " from " + Const.TABLE_BUS
				+ " where busId<>?" + Const.SQL_BUS_QUERY_LIMIT;
		LogUtils.f(sql);

		List<Bus> list = getBeanList(sql, exceptId);

		return list;
	}

	public Bus findABusById(String budId) {
		String sql = "select * from " + Const.TABLE_BUS + " where busId=?"
				+ Const.SQL_BUS_QUERY_LIMIT;
		Bus bus = getBean(sql, budId);
		return bus;
	}

	/**
	 * 路线规划(基于人数的规划）
	 */
	public void RoutePlanning() {
		StationDao sd = new StationDao();
		Long total2 = sd.countWP("s2");
		Long total3 = sd.countWP("s3");
		Long total4 = sd.countWP("s4");
		Long total5 = sd.countWP("s5");
		Long total6 = sd.countWP("s6");
		Long total7 = sd.countWP("s7");
		RoutePlan rp = new RoutePlan();
		rp.init();
		rp.add(0, 1, total2);
		rp.add(1, 2, total3);
		rp.add(2, 3, total4);
		rp.add(3, 4, total5);
		rp.add(4, 5, total6);
		rp.add(5, 6, total7);
		rp.output();
	}
	/**
	 * 路线规划（基于路径的规划）
	 */
	public String RoutePlanning1() {
		    GraphMatrixOperator gmo = new GraphMatrixOperator();
		    String[] src = new String[]{"s1,s2,5", "s1,s3,8", "s1,s4,3", 
		    							"s1,s5,1","s1,s6,6","s1,s7,8",
		    							"s2,s1,5","s2,s3,3","s2,s4,2",
		    							"s2,s5,4","s2,s6,1","s2,s7,3",
		    							"s3,s1,8","s3,s2,3","s3,s4,5",
		    							"s3,s5,7","s3,s6,4","s3,s7,3",
		    							"s4,s1,3","s4,s2,2","s4,s3,5",
		    							"s4,s5,2","s4,s6,3","s4,s7,5",
		    							"s5,s1,1","s5,s2,4","s5,s4,2",
		    							"s5,s6,5","s5,s7,7","s6,s1,6",
		    							"s6,s2,1","s6,s3,4","s6,s4,3",
		    							"s6,s5,5","s6,s7,2","s7,s1,8",
		    							"s7,s2,3","s7,s3,3","s7,s4,5",
		    							"s7,s5,7","s7,s6,2"};
	        GraphMatrixOperator g = gmo.createMGraph(src);
//	        int v0 = 0;
	        String shortpath = gmo.main_floyd(g);
	        LogUtils.f(shortpath+"....");
	        return shortpath;
	}
	
	/**
	 * 路线规划（基于路径的规划2）
	 * @param start_point
	 * @param end_point
	 * @return
	 */
	public String RoutePlanning2(int start_point,int end_point) {
		Dijkstra d = new Dijkstra();
		int prev[]= new int[d.getN()];//用来标注当前结点的上一个结点，以便输出两点间最短路线 
		Stack myStack = new Stack(); 		
		int temp = end_point;
		PointIfo pi = new PointIfo();
        pi.setPointSum(7);
        pi.setLineSum(9);
        d.memset(d.getVisited(),d.getVisited().length);
        d.memset1(d.getDisp(),d.getDisp().length);
        int edge[][] = {{999999,999999,999999,3,1,999999,999999},
				{999999,999999,3,2,999999,1,999999},
				{999999,3,999999,999999,999999,999999,3},
				{3,2,999999,999999,2,3,999999},
				{1,999999,999999,2,999999,999999,999999},
				{999999,1,999999,3,999999,999999,2},
				{999999,999999,3,999999,999999,2,999999}};
        for (int i = 0; i<7; i++) {
			prev[i] = i;//初始状态设每个点的前驱为自身
			edge[i][i] = 0;
		}


		d.getDisp()[start_point] = 0;
		for (int i = 0; i < pi.getPointSum(); i++) {
			int u = -1, min = d.getInf();
			for (int j = 0; j < pi.getPointSum(); j++) {
				if (d.getVisited()[j] == false && d.getDisp()[j] <= min)
				{
					u = j;
					min = d.getDisp()[j];
				}
			}

			if (u == -1)
				break;

			d.getVisited()[u] = true;
			for (int v = 0; v < pi.getPointSum(); v++) {
				if (d.getVisited()[v] == false && edge[u][v] !=d.getInf())
				{
					if (d.getDisp()[u] + edge[u][v] < d.getDisp()[v]) {
						d.getDisp()[v] = d.getDisp()[u] + edge[u][v];
						prev[v] = u;//prev保存当前结点的上一个节点，以便输出最短路线
					}
				}
			}
		}
		
		myStack.push(end_point);//先加终点 
		while (start_point != temp) {
			temp = prev[temp];
			myStack.push(temp);//注意这里是把当前点的上一个结点放进去，此时换成了temp 
		}
		System.out.println("起点"+start_point+"到"+end_point+"的最短路劲为： " +d.getDisp()[end_point]);
		String str="";
		while (!myStack.empty()) {			
			str+=myStack.pop()+",";
		}
		return str;
	}
}
