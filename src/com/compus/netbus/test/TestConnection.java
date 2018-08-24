package com.compus.netbus.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.compus.netbus.utils.JDBCUtils;
import com.compus.netbus.utils.LogUtils;

public class TestConnection {

	@Test
	public void test() {
		Connection connection = JDBCUtils.getConnection();
		LogUtils.f(connection);
	}

}
