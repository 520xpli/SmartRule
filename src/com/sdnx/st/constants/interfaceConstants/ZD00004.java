package com.sdnx.st.constants.interfaceConstants;

public enum ZD00004 {
	APPBUSITYPE             , //贷款产品
	CALLSTEP                , //规则调取阶段
	ZXLIST                  ; //客户征信信息列表
	
	public enum zxlist{
		CHECKOBJ                , //检查对象
		CUSTID                  , //客户编号
		CARDTYPE                , //证件类型
		CARDNUM                 , //证件号码
		THWROSTCLASS            , //当前他行贷款最差分类形态
		DBWROSTCLASS            , //对外担保贷款最差分类形态
		FRNUM                   , //贷款法人机构数
		NORMALCARDNUM           , //未销户信用卡发卡机构数
		USERATE                 , //未销户贷记卡与准贷记卡最近6个月平均使用率
		SIXMCHECKNUM            , //最近6个月征信查询次数
		ONEYCHECKNUM            , //最近12个月征信查询次数
		TOWYCHECKNUM            , //最近24个月征信查询次数
		THREEYCHECKNUM          , //最近36个月征信查询次数
		SIXMOVERDUETERM         , //最近6个月本息最大逾期期数
		TOWYOVERDUETERM         , //最近24个月本息最大逾期期数
		ONEYOVERDUETERM         , //最近12个月本息最大逾期期数
		SIXMOVERDUENUM          , //最近6个月本息逾期次数
		ONEYOVERDUENUM          , //最近12个月本息逾期次数
		TOWYOVERDUENUM          , //最近24个月本息逾期次数
		THREEYOVERDUENUM        , //最近36个月本息逾期次数
		ONEYKHNUM               , //最近12个月贷记卡开户个数
		MINOVERDUETDATE         , //最近一次本息逾期距今月份数
		DBSUMBALAMT             , //对外担保本金余额
		THREEYHIGHESTOVDTIMES   , //连续最高逾期次数
		ONEYTOTALOVDTIMES       , //累计逾期次数
		OVERDUETERM             , //当前逾期期数
		STATUS                  , //查询状态
		CNAME					; //客户名称
	}
}
