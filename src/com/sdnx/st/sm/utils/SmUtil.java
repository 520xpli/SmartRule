package com.sdnx.st.sm.utils;

public class SmUtil {
	
	private final static User DEFAULT_USER = new User();
	
	public static User getCurrentUser(){
		return DEFAULT_USER;
	}
	
	public static class User{
		private static final String DEFAULT_USERID = "1";
		private static final String DEFAULT_USERNAME = "ÖÇe´û";
		
		public String getId() {
			return DEFAULT_USERID;
		}

		public String getGroupId(){
			return GroupDao.ORG_CODE;
		}
		
		public String getName(){
			return DEFAULT_USERNAME;
		}
		
	}
}
