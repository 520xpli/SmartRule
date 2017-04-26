package com.sdnx.st.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parameter {
	//��ʼ����ȡ����
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
	//׼��ӿڽ��״���
	public static String admit;
	//�����ӿڽ��״���
	public static String rate;
	//socket����������ݱ����ʽ
	public static String charSet;
	//��־�ļ�Ŀ¼
	public static String logAddr;
	//ͨ������
	public static String passCode;
	//׼��ӿ��������ݱ��ĳ���
	public static Integer admitMessageLength;
	//�����ӿ�������ݱ��ĳ���
	public static Integer rateMessageLength;
	//���ݿ�schema
	public static String schema;
	//�������˿�
	public static Integer port;
	//׼�뷵���ַ�����
	public static Integer admitBackLength;
	//���������ַ�����
	public static Integer rateBackLength;
	//׼�뷵���ֽڳ���
	public static Integer admitBackByteLength;
	//���������ֽڳ���
	public static Integer rateBackByteLength;
}
