package com.offcn.handle;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArrayResultHandler implements ResultHandler {
	
		@Override
		public Object handler(ResultSet rs) {
			List list = new ArrayList();
			try {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while(rs.next()){
					for (int i = 1; i <=columnCount; i++) {
						String columnName = metaData.getColumnName(i);
						Object columnValue = rs.getObject(columnName);
						list.add(columnValue);
					}
				}
				return list;
			}  catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
}
