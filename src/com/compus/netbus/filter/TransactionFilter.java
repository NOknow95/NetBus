package com.compus.netbus.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		JDBCUtils.realseConnection();

//		Connection conn = JDBCUtils.getConnection();
//
//		// 设置手动提交事务
//		try {
//			conn.setAutoCommit(false);
//
			chain.doFilter(request, response);
//
//			conn.commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// 如果有异常回滚事物
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//
//			LogUtils.f("回滚事物");
//
//		} finally {
//			JDBCUtils.realseConnection();
//		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
