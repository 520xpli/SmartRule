package com.sdnx.st.util;

import java.sql.Connection;

/**
 * 连接管理器
 * @author ysj
 *
 */
public class STConnectionManager {
	private static STConnectionManager stConnectionManager = null;
	public static STConnectionManager getInstance() {
		if (stConnectionManager == null) {
			stConnectionManager = new STConnectionManager();
		}
		return stConnectionManager;
	}

	public Connection getConnection(){
//		Connection conn = null;
//		try {
//			String dataSource = PPDataSourceUtil.getInstance().getDataSource("RuleProcessService_" + Thread.currentThread().getId());
//			if(PPStringUtil.isNotNullAndBank(dataSource)) {
//				conn = JDBCDataSource.getJDBCDataSource(dataSource).getConnection();
//			}else {
//				conn = JDBCDataSource.getJDBCDataSource().getConnection();
//			}
//		} catch (Exception e) {
//			logger.error("获取数据库连接异常", e);
//			throw new Exception(e);
//		}
		Connection conn = null;
		try{
//			Class.forName("com.ibm.db2.jcc.DB2Driver");
//			conn = DriverManager.getConnection("jdbc:db2://51.96.4.68:50000/wfngcms", "ngcms" , "ngcms");
//			conn = DriverManager.getConnection("jdbc:db2://51.96.4.68:50000/zdhngcms", "ngcms" , "ngcms");
			conn = ConnectionFactory.getInstance().getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getSelfTransActionConnection(){
		Connection conn = null;
		try{
			conn = ConnectionFactory.getInstance().getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
