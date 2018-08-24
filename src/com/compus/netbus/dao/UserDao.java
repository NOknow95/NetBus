package com.compus.netbus.dao;

import com.compus.netbus.bean.Bus;
import com.compus.netbus.bean.FeedBack;
import com.compus.netbus.bean.PushBean;
import com.compus.netbus.bean.WaitPot;
import com.compus.netbus.utils.Const;
import com.compus.netbus.utils.JPush;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;

public class UserDao extends BaseDao<Object> {

	/**
	 * 注册
	 * 
	 * @param tableName
	 * @param username
	 * @param pwd
	 * @return
	 */
	public int regist(String tableName, String username, String pwd) {
		tableName = tableName.toUpperCase();

		String columName = "busName";
		String columPwd = "busPwd";
		String columId = "busId";
		if (tableName.equals(Const.TABLE_WAITPOT)) {
			columName = "wpName";
			columPwd = "wpPwd";
			columId = "wpId";
		}

		String sql = "insert into " + tableName + " (" + columName + ","
				+ columPwd + ") values (?,?)";

		int update = update(sql, username, pwd);
		int id = 0;
		if (update > 0) {
			sql = "select " + columId + " from " + tableName + " where "
					+ columName + "=? and " + columPwd + "=?";
			id = (Integer) getAValue(sql, username, pwd);
		} else {
			id = -1;
		}
		return id;
	}

	/**
	 * 
	 * @param tableName
	 * @param username
	 * @param pwd
	 * @return
	 */
	public int getBeanByNameAndPwd(String tableName, String username, String pwd) {
		tableName = tableName.toUpperCase();
		String sql = "";
		getBean(sql, tableName, username, pwd);
		return 0;
	}

	/**
	 * 用户登录
	 * 
	 * @param tableName
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Object login(String tableName, String username, String pwd) {

		tableName = tableName.toUpperCase();

		String columName;
		String columPwd;

		boolean isWP = tableName.equals(Const.TABLE_WAITPOT);

		if (isWP) {
			columName = "wpName";
			columPwd = "wpPwd";
		} else {
			columName = "busName";
			columPwd = "busPwd";
		}

		String sql = "select * from " + tableName + " where " + columName
				+ "=? and " + columPwd + "=?";

		Object bean = null;

		if (isWP) {
			bean = getBean(WaitPot.class, sql, username, pwd);
		} else {
			bean = getBean(Bus.class, sql, username, pwd);
			LogUtils.f(bean);
		}

		if (bean != null && !isWP) {
			String content = JsonUtils.objToJson(bean);
			PushBean pushBean = new PushBean(PushBean.TYPE_UPDATE_BEAN, content);
			JPush.sendPushMessageAll(JsonUtils.beanToJson(pushBean,
					PushBean.class));
		}
		return bean;
	}

	/**
	 * 【成功：返回1】【 失败：返回0】【 出错：返回-1】
	 * 
	 * @param busId
	 * @return
	 */
	public int loginOut(String busId) {

		int resultCode = 1;

		String sql = "update " + Const.TABLE_BUS
				+ " set online=false where busId=? and valied=true";

		try {
			resultCode = update(sql, busId);

			sql = "select * from " + Const.TABLE_BUS + " where busId=?";
			Bus bus = getBean(Bus.class, sql, busId);

			if (bus != null) {
				String content = JsonUtils.beanToJson(bus, Bus.class);
				PushBean pushBean = new PushBean(PushBean.TYPE_UPDATE_BEAN,
						content);
				JPush.sendPushMessageAll(JsonUtils.beanToJson(pushBean,
						PushBean.class));
			}

		} catch (Exception e) {
			resultCode = -1;
		}

		return resultCode;
	}

	/**
	 * 用户反馈
	 * 
	 * @param feedBack
	 * @return
	 */
	public int feedback(FeedBack feedBack) {
		String sql = "insert into " + Const.TABLE_FEEDBACK + " values(?,?,?,?)";
		return update(sql, feedBack.getId(), feedBack.getContent(),
				feedBack.getUserType(), feedBack.getCreate_time());
	}
}
