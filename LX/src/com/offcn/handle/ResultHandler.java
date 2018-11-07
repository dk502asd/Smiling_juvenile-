package com.offcn.handle;

import java.sql.ResultSet;

public interface ResultHandler {

	/**
	 * 把数据库的查询结果封装成一个Bean/List<Bean>
	 * @param rs  数据库的查询结果
	 * @return
	 */
	public Object handler(ResultSet rs);
}
