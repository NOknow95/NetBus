package com.compus.netbus.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.compus.netbus.utils.JDBCUtils;
import com.compus.netbus.utils.LogUtils;

public class BaseDao<T> {
	//QueryRunner进行增删改查的类
	private QueryRunner runner = new QueryRunner();

	private Class<T> type;

	public BaseDao() {
		// 创建了一个无参的构造器，这个构造器是由子类调用
		// UserDao extends BaseDao<User>
		Class cla = this.getClass();

		ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();

		Type[] types = pt.getActualTypeArguments();

		this.type = (Class<T>) types[0];
	}

	/**
	 * 批量操作数据库
	 * 
	 * @param sql
	 * @param params
	 *            二维数组 一维：次数 二维：参数
	 */
	public void batchUpdate(String sql, Object[][] params) {
		Connection conn = JDBCUtils.getConnection();
		int[] batch = null;
		try {
			batch = runner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
	}

	/**
	 * 从数据库中查询到单一的值
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql, Object... params) {
		LogUtils.f("getSingleValue:sql=" + sql);

		Connection conn = JDBCUtils.getConnection();
		Object object = null;
		try {
			// ScalarHandler 会返回查询到的第一行第一列的数据
			object = runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return object;
	}

	/**
	 * 查询一个对象
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql, Object... params) {
		LogUtils.f("getBean:sql=" + sql);

		T t = null;
		Connection conn = JDBCUtils.getConnection();
		try {
			t = runner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return t;
	}
	
	
	/**
	 * 查询一个对象
	 * 
	 * @param <K>
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public <K> K getBean(Class<K> class1, String sql, Object... params) {
		LogUtils.f("getBean:sql=" + sql);

		K t = null;
		Connection conn = JDBCUtils.getConnection();
		try {
			t = runner.query(conn, sql, new BeanHandler<K>(class1), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return t;
	}

	/**
	 * 查询一组对象列表
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql, Object... params) {
		LogUtils.f("getBeanList:sql=" + sql);

		List<T> list = null;
		Connection conn = JDBCUtils.getConnection();
		try {
			list = runner
					.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return list;
	}

	/**
	 * 更新数据库的方法
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		LogUtils.f("update:sql=" + sql);

		int count = 0;

		Connection conn = JDBCUtils.getConnection();
		try {
			// LogUtils.f("update 1:count=" + count);
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
			count = -1;
			// LogUtils.f("update 2:count=" + count);
			// e.printStackTrace();
//			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return count;
	}

	public Object getAValue(String sql, Object... params) {
		LogUtils.f("getAValue:sql=" + sql);

		Object o = null;

		Connection conn = JDBCUtils.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			o = resultSet.getObject(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.realseConnection();
		}
		return o;
	}

	public QueryRunner getRunner() {
		return runner;
	}
}
