package com.compus.netbus.dao;

import java.util.List;

import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.utils.Const;

public class QueryWPDao extends BaseDao<WaitPot> {

	public List<WaitPot> queryWPsExcept(String exceptId) {
		String sql = "select * from " + Const.TABLE_WAITPOT + " where wpId<>?"
				+ Const.SQL_WAITPOT_QUERY_LIMIT;
		return getBeanList(sql, exceptId);
	}

	public WaitPot findAWPById(String wpId) {
		String sql = "select * from " + Const.TABLE_WAITPOT + " where wpId=?"
				+ Const.SQL_WAITPOT_QUERY_LIMIT;
		return getBean(sql, wpId);
	}
	
	
}
