package com.sdnx.st.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * ���ݿ����������
 * 
 * @author ysj
 * 
 */
public class STDBManager {

	private static Logger logger = Logger.getLogger(STDBManager.class);
	private static STDBManager stDBManager = null;

	public static STDBManager getInstance() {
		if (stDBManager == null) {
			stDBManager = new STDBManager();
		}
		return stDBManager;
	}

	public Connection getConnection() throws Exception {
		Connection con = STConnectionManager.getInstance().getConnection();
		return con;
	}

	/**
	 * ���ݿ�ر�
	 * 
	 * @param rs
	 * @param sta
	 * @param con
	 */
	public void close(ResultSet rs, PreparedStatement pst, Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			logger.error("���ݿ�ر��쳣", e);
		}
	}
	
	/**
	 * ���ݿ�ر�
	 * 
	 * @param rs
	 * @param sta
	 */
	public void close(ResultSet rs, PreparedStatement pst) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
		} catch (Exception e) {
			logger.error("���ݿ�ر��쳣", e);
		}
	}
}
