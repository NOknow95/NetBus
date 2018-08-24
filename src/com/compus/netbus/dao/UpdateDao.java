package com.compus.netbus.dao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.compus.netbus.bean.PushBean;
import com.compus.netbus.utils.Const;
import com.compus.netbus.utils.JPush;
import com.compus.netbus.utils.JsonUtils;
import com.compus.netbus.utils.LogUtils;

public class UpdateDao extends BaseDao<Object> {

	public <T> T updateAWPOrBus(Class<T> beanType, String id, String tableName,
			Map<String, Object> map, boolean isToPush) {

		T beanNew = null;

		tableName = tableName.toUpperCase();

		String sql = "UPDATE " + tableName.toUpperCase() + " SET ";

		if (map != null && !map.isEmpty()) {

			Object[] params = new Object[map.size() + 1];

			Iterator<Entry<String, Object>> iterator = map.entrySet()
					.iterator();

			int i = 0;

			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				String columName = entry.getKey();
				Object valueNew = entry.getValue();

				sql += columName + "=?,";

				params[i++] = valueNew;
			}

			sql = sql.substring(0, sql.length() - 1);

			params[i++] = id;

			// LogUtils.f("params=" + Arrays.toString(params));

			String columId = (tableName.equals(Const.TABLE_WAITPOT) ? "wpId"
					: "busId");

			sql += " where " + columId + "=?";

			// -1错误，0失败，1正常
			int updateResult = update(sql, params);

			if (updateResult > 0) {
				// update success
				sql = "select * from " + tableName + " where " + columId + "=?";
				// return a new bean after update
				beanNew = getBean(beanType, sql, id);
				if (isToPush && beanNew != null) {
					// push update message
					String content = JsonUtils.beanToJson(beanNew, beanType);
					PushBean pushBean = new PushBean(PushBean.TYPE_UPDATE_BEAN,
							content);
					JPush.sendPushMessageAll(JsonUtils.beanToJson(pushBean,
							PushBean.class));
				}
			} else if (updateResult == -1) {
				// update failed
				LogUtils.f("update a ... but update error,update="
						+ updateResult);
			} else {
				LogUtils.f("update a ... but update failed,update="
						+ updateResult);
			}
		}

		return beanNew;
	}

}
