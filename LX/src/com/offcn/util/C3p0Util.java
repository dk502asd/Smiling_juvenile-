package com.offcn.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class C3p0Util {
	private static DataSource dataSource = null;
	
	static{
		dataSource = new ComboPooledDataSource();
		//C3p0Util.class.getClassLoader().getResourceAsStream("c3p0.properties");
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			//Connection conn = DriverManager.getConnection(url);  
			conn = dataSource.getConnection();					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	} 
	
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs!=null)
			rs.close();
			if(pstmt!=null)
			pstmt.close();
			if(conn!=null)
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
