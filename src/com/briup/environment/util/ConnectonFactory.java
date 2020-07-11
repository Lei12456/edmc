package com.briup.environment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectonFactory {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static String user = "briup";
	private static String password = "briup";
	private static Connection conn = null;
	
	
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(ResultSet rs,Statement statement , Connection conn) {
		try {
			if(rs!=null)rs.close();
			if(statement!=null)statement.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(Statement st,Connection conn) {
			this.close(null,st, conn);
	}
	
}
