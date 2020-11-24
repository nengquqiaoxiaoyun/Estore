package com.itcast.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	public static DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 获取当前线程，用来进行事务的管理
	 * @return
	 */
	public static Connection getCurrentConnection(){
		Connection con = null;
		try {
			//从local中获取连接
			con = local.get();
			if(con==null){
				//如果本地中没有连接，从数据库连接池获取连接
				con = dataSource.getConnection();
				//将连接存放到local中
				local.set(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 释放资源
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void release(Connection con,Statement st,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
