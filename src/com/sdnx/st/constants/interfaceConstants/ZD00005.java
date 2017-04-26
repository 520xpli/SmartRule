package com.sdnx.st.constants.interfaceConstants;

public enum ZD00005 {
	APPBUSITYPE   ,  //贷款产品
	IFNEWCUST     ,  //是否新增客户
	CALLSTEP      ,  //规则调取阶段
	CUSTLIST      ;  //对外担保本金余额
    
	public enum custlist{
		CHECKOBJ	 ,   		//检查对象	    
		CUSTID	     ,   		//客户编号	    
		CARDTYPE	 ,   		//证件类型	    
		CARDNUM	     ,   		//证件号码	    
		FAMASSET	 ,   		//家庭总资产	    
		FAMDEBT	     ,   		//家庭总负债	    
		DBSUMBALAMT  ,  		//对外担保本金余额  
		CNAME        ;			//客户名称
	}

}
