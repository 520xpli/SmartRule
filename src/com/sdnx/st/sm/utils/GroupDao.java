package com.sdnx.st.sm.utils;

import java.util.ArrayList;
import java.util.List;

public class GroupDao {
	
	public static final String ORG_CODE = "123";
	
	public static final String ORG_NAME = "滨州农商行";
	
	public static final GroupDao instance = new GroupDao();
	
	public static GroupDao getInstance(){
		return instance;
	}
	
	public List<Group> queryCityGroupList(String groupId){
		List<Group> result = new ArrayList<Group>();
		Group group = new Group();
		result.add(group);
		return result;
	}
	
	public String queryFrflagGroupId(String groupId){
		return ORG_CODE;
	}
	
	public boolean isTrueFrFlag(String groupId){
		return true;
	}
	
	public String queryValueByAppoint(String groupId, String orgname){
		return ORG_NAME;
	}
	
	public void queryOfficeUnitFrGroupList(String orgcode, List<String> orgIds){
		orgIds.add(ORG_CODE);
	}
}
