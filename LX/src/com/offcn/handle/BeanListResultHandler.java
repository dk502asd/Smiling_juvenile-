package com.offcn.handle;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BeanListResultHandler implements ResultHandler {

	//需要处理的字节码文件
	private Class clazz;    //Student.class

	//通过构造方法给字节码文件赋值
	public BeanListResultHandler(Class clazz){
		this.clazz = clazz;
	}

	@Override
	/**
	 * 返回一个Bean
	 */
	public Object handler(ResultSet rs) {
		// TODO Auto-generated method stub
		//有了字节码文件，就可以给对象去赋值
		List list = new ArrayList();
		try {

			//拿到元数据
			ResultSetMetaData metaData = rs.getMetaData();
			//拿到当前行的列数
			int columnCount = metaData.getColumnCount();
			//循环给obj对象的属性赋值
			while(rs.next()){
				//调用无参构造方法创建对象
				Object obj = clazz.newInstance();
				for (int i = 1; i <=columnCount; i++) {
					//因为列名跟属性名是一致的
					//拿到每个列的列名
					String columnName = metaData.getColumnName(i);
					//通过反射拿到属性 Field
					//getDeclaredField 可以获取私有的属性
					Field field = clazz.getDeclaredField(columnName);
					field.setAccessible(true);
					//metaData是元数据，拿不了值的,只能通过ResultSet拿值
					field.set(obj, rs.getObject(columnName));
				}
				list.add(obj);
			}
			return list;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
