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
	 * ��ȡ��ǰ�̣߳�������������Ĺ���
	 * @return
	 */
	public static Connection getCurrentConnection(){
		Connection con = null;
		try {
			//��local�л�ȡ����
			con = local.get();
			if(con==null){
				//���������û�����ӣ������ݿ����ӳػ�ȡ����
				con = dataSource.getConnection();
				//�����Ӵ�ŵ�local��
				local.set(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �ͷ���Դ
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
