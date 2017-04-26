package com.sdnx.st.constants.interfaceConstants;

public enum ZD00003 {
	DEPTCODE          ,   //�����������
	APPBUSITYPE       ,   //�����Ʒ
	REQLMT            ,   //������
	IFNEWCUST         ,   //�Ƿ������ͻ�
	RELALOAN          ,   //�����ҵ�Ƿ�����������������
	CALLSTEP          ,   //�����ȡ�׶�
	CUSTLIST          ,   //"�ⲿʧ�Ż�ͻ�Υ��Υ�͡������Ⱥ���Ϣ����������"
	BNLIST            ,   //"1���ͻ��д������Ĵ�����Ϣ(�����������Ʒ)2������δ���������Ϣ3������δ��������弶����״̬"
	BWLIST            ,   // "����δ���������Ϣ �����ѽ��������Ϣ"
	EXPLIST           ,   // չ��(���ݷ���չ����Ȩ�����ڽ����ж�)
	BNWLIST_DB        ;   // "�ͻ����ⵣ������δ������� �ͻ����ⵣ������δ�������"
	
	
	public enum custlist{
		CHECKOBJ,		//������
		CUSTID,	        //�ͻ����
		CARDTYPE,	    //֤������
		CARDNUM,	    //֤������
		INSXLIST,	    //�Ƿ�������������
		INBWLIST,	    //�Ƿ��ں�������
		FAMASSET,	    //��ͥ���ʲ�
		FAMDEBT,        //��ͥ�ܸ�ծ
		DBSUMBALAMT,    //���ⵣ���������
		CNAME,			//�ͻ�����
		IFFREEZE		  ;   //�˻��Ƿ񶳽�

	}
	
	public enum bnlist{
		BUSICODE,	         //ҵ����	    
		CUSTID,	             //�ͻ����	    
		DEPTCODE,	         //��������	    
		BUSITYPE,	         //ҵ��Ʒ��	    
		BALAMT,	             //�������	    
		DEBTINTEREST,        //ǷϢ���	    
		FIVECLASS,	         //�弶����״̬	
		DELAYAMTDAYS,	     //������������    
		DELAYINTERESTDAYS,	 //��Ϣ��������    
		ACCOUNTSTATE;        //�˻�״̬      

	}
	
	public enum bwlist{
		CREDENTIALID,		//ҵ����	    
		OWNERID	    ,		//�ͻ����	    
		LOANSTATUS	,		//����״̬	    
		LOANBALANCE	,		//�������	    
		DEBTINTEREST,		//ǷϢ���	    
		CLOSEDATE   ;		//��������      

	}
	
	public enum explist{
		BUSICODE,  //ҵ����
		CUSTID	,  //�ͻ����
		LOANDATE; //��ʼ����
	}
	
	public enum bnwlist_db{
		BUSICODE	         ,  	//ҵ����	 
		CUSTID	             ,  	//�ͻ����	 
		FIVECLASS	         ,  	//�弶����	 
		BALAMT	             ,  	//�������	 
		DEBTINTEREST	     ,  	//ǷϢ���	 
		DELAYAMTDAYS	     ,  	//������������	
		DELAYINTERESTDAYS	 ,  	//��Ϣ��������	
		ISOUTTABLELOAN       ; 		//����/����  

	}
                            
}                           
