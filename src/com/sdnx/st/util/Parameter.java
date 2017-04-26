package com.sdnx.st.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parameter {
	//初始化获取参数
	static{
		Properties p = new Properties();
		InputStream is = Parameter.class.getResourceAsStream("parameter.properties");
		try {
			p.load(is);
			admit = p.getProperty("admit").trim();
			rate = p.getProperty("rate").trim();
			charSet = p.getProperty("charSet").trim();
			logAddr = p.getProperty("logAddr").trim();
			passCode = p.getProperty("passCode").trim();
			admitMessageLength = p.getProperty("admitMessageLength") == null ? null : Integer.valueOf(p.getProperty("admitMessageLength").trim());
			rateMessageLength = p.getProperty("rateMessageLength") == null ? null : Integer.valueOf(p.getProperty("rateMessageLength").trim());
			schema = p.getProperty("schema") == null ? null : p.getProperty("schema");
			port = p.getProperty("port") == null ? null : Integer.valueOf(p.getProperty("port").trim());
			admitBackLength = p.getProperty("admitBackLength") == null ? null : Integer.valueOf(p.getProperty("admitBackLength").trim());
			rateBackLength = p.getProperty("rateBackLength") == null ? null : Integer.valueOf(p.getProperty("rateBackLength").trim());
			admitBackByteLength = p.getProperty("admitBackByteLength") == null ? null : Integer.valueOf(p.getProperty("admitBackByteLength").trim());
			rateBackByteLength = p.getProperty("rateBackByteLength") == null ? null : Integer.valueOf(p.getProperty("rateBackByteLength").trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//准入接口交易代码
	public static String admit;
	//评级接口交易代码
	public static String rate;
	//socket输入输出数据编码格式
	public static String charSet;
	//日志文件目录
	public static String logAddr;
	//通过编码
	public static String passCode;
	//准入接口输入数据报文长度
	public static Integer admitMessageLength;
	//评级接口输出数据报文长度
	public static Integer rateMessageLength;
	//数据库schema
	public static String schema;
	//服务器端口
	public static Integer port;
	//准入返回字符长度
	public static Integer admitBackLength;
	//评级返回字符长度
	public static Integer rateBackLength;
	//准入返回字节长度
	public static Integer admitBackByteLength;
	//评级返回字节长度
	public static Integer rateBackByteLength;
}
