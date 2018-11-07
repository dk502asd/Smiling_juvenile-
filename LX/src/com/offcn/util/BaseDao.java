package com.offcn.util;

import com.offcn.handle.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	public static int executeUpdate(String sql,Object[]obj){
		Connection conn = C3p0Util.getConnection();
		PreparedStatement pstmt = null;
		int j = 0 ;
		try {
			pstmt = conn.prepareStatement(sql);
			if(obj!=null&&obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					pstmt.setObject(i+1,obj[i]);
				}
			}
			j = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3p0Util.close(conn, pstmt,null);
		}
		return j;
	}
	
	public static Object executeQuery(String sql,Object[]obj,ResultHandler handler){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = C3p0Util.getConnection();
			pstmt = conn.prepareStatement(sql);
			if(obj!=null&&obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					pstmt.setObject(i+1,obj[i]);
				}
			}
			rs = pstmt.executeQuery();
			Object object = handler.handler(rs);
			return object;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			C3p0Util.close(conn, pstmt, rs);
		}
		return null;
	}
}
