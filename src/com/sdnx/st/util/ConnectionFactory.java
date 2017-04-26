package com.sdnx.st.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnectionFactory {
	
	private DataSource dataSource;
	
	private ConnectionFactory(){
		
	}
	
	private static class Inner{
		static ConnectionFactory cf = new ConnectionFactory();
	}
	
	public static ConnectionFactory getInstance(){
		return Inner.cf;
	}
	//获取连接
	public Connection getConnection() throws Exception{
		return getDataSource().getConnection();
	}
	//获取数据源
	private synchronized DataSource getDataSource() throws Exception{
		if(dataSource != null) return dataSource;
		Properties p = new Properties();
		InputStream is = ConnectionFactory.class.getResourceAsStream("db.properties");
		p.load(is);
		dataSource = BasicDataSourceFactory.createDataSource(p);
		return dataSource;
	}
}
