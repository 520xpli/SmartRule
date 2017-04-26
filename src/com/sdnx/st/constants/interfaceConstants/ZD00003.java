package com.sdnx.st.constants.interfaceConstants;

public enum ZD00003 {
	DEPTCODE          ,   //申请机构代码
	APPBUSITYPE       ,   //贷款产品
	REQLMT            ,   //申请额度
	IFNEWCUST         ,   //是否新增客户
	RELALOAN          ,   //相关企业是否存在用信余额贷款余额
	CALLSTEP          ,   //规则调取阶段
	CUSTLIST          ,   //"外部失信或客户违法违纪、不良嗜好信息名单黑名单"
	BNLIST            ,   //"1、客户有贷款余额的贷款信息(机构、贷款产品)2、表内未结清贷款信息3、表内未结清贷款五级分类状态"
	BWLIST            ,   // "表外未结清贷款信息 表外已结清贷款信息"
	EXPLIST           ,   // 展期(根据发起展期授权的日期进行判断)
	BNWLIST_DB        ;   // "客户对外担保表内未结清贷款 客户对外担保表外未结清贷款"
	
	
	public enum custlist{
		CHECKOBJ,		//检查对象
		CUSTID,	        //客户编号
		CARDTYPE,	    //证件类型
		CARDNUM,	    //证件号码
		INSXLIST,	    //是否在老赖名单内
		INBWLIST,	    //是否在黑名单内
		FAMASSET,	    //家庭总资产
		FAMDEBT,        //家庭总负债
		DBSUMBALAMT,    //对外担保本金余额
		CNAME,			//客户名称
		IFFREEZE		  ;   //账户是否冻结

	}
	
	public enum bnlist{
		BUSICODE,	         //业务编号	    
		CUSTID,	             //客户编号	    
		DEPTCODE,	         //所属机构	    
		BUSITYPE,	         //业务品种	    
		BALAMT,	             //贷款余额	    
		DEBTINTEREST,        //欠息金额	    
		FIVECLASS,	         //五级分类状态	
		DELAYAMTDAYS,	     //本金逾期天数    
		DELAYINTERESTDAYS,	 //利息逾期天数    
		ACCOUNTSTATE;        //账户状态      

	}
	
	public enum bwlist{
		CREDENTIALID,		//业务编号	    
		OWNERID	    ,		//客户编号	    
		LOANSTATUS	,		//贷款状态	    
		LOANBALANCE	,		//贷款余额	    
		DEBTINTEREST,		//欠息金额	    
		CLOSEDATE   ;		//结清日期      

	}
	
	public enum explist{
		BUSICODE,  //业务编号
		CUSTID	,  //客户编号
		LOANDATE; //开始日期
	}
	
	public enum bnwlist_db{
		BUSICODE	         ,  	//业务编号	 
		CUSTID	             ,  	//客户编号	 
		FIVECLASS	         ,  	//五级分类	 
		BALAMT	             ,  	//贷款余额	 
		DEBTINTEREST	     ,  	//欠息金额	 
		DELAYAMTDAYS	     ,  	//本金逾期天数	
		DELAYINTERESTDAYS	 ,  	//利息逾期天数	
		ISOUTTABLELOAN       ; 		//表内/表外  

	}
                            
}                           
