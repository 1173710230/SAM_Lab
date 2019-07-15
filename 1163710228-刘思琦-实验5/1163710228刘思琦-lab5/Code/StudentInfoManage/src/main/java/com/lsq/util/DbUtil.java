package com.lsq.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	private String dbUrl1 = "jdbc:mysql://localhost:3306/db_studentInfo1?characterEncoding=utf8";
	private String dbUrl2 = "jdbc:mysql://localhost:3306/db_studentInfo2?characterEncoding=utf8";
	private String dbUrl3 = "jdbc:mysql://localhost:3306/db_studentInfo3?characterEncoding=utf8";
	private String dbUserName = "root";
	private String dbPassword = "123456";
	private String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon1() throws Exception {
		Class.forName(jdbcName);
		Connection con1 = DriverManager.getConnection(dbUrl1, dbUserName, dbPassword);
		return con1;
	}

	public Connection getCon2() throws Exception {
		Class.forName(jdbcName);
		Connection con2 = DriverManager.getConnection(dbUrl2, dbUserName, dbPassword);
		return con2;
	}

	public Connection getCon3() throws Exception {
		Class.forName(jdbcName);
		Connection con3 = DriverManager.getConnection(dbUrl3, dbUserName, dbPassword);
		return con3;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param con1
	 * @throws Exception
	 */
	public void closeCon1(Connection con1) throws Exception {
		if (con1 != null) {
			con1.close();
		}
	}


	public void closeCon2(Connection con2) throws Exception {
		if (con2 != null) {
			con2.close();
		}
	}


	public void closeCon3(Connection con3) throws Exception {
		if (con3 != null) {
			con3.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon1();
			System.out.println("数据库1连接成功");
			dbUtil.getCon2();
			System.out.println("数据库2连接成功");
			dbUtil.getCon3();
			System.out.println("数据库3连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
