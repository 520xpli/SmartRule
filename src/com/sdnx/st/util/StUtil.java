package com.sdnx.st.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

public class StUtil {
	private static Logger logger = Logger.getLogger(StUtil.class);
	
	/**
	 * ���ݱ��ʽ������
	 * @param expression ������ʽ,���磺#{a}+(#{b}-#{c})/#{d}
	 * @param variables  ��������,map��key��value����String����
	 * @return ���ؼ������ַ���
	 * @throws EvaluationException
	 */
	public static String getResult(String expression,Map<String,Object> variables) throws EvaluationException{
		Evaluator evaluator = new Evaluator();
		if(variables != null){
			evaluator.setVariables(variables);
		}
		String result =  evaluator.evaluate(expression) ;
		return result;
	}
	
/*	public static void main(String[] args) {
		StUtil util = new StUtil();
		String test = "#{cr_overdueCountIn12M}*3";
//		test = test.replaceAll("#\\{.\\}", "1");
		System.out.println(test);
		Map map = new HashMap();
		map.put("cr_overdueCountIn12M", "32");
		Evaluator evaluator = new Evaluator();
		try {
			System.out.println(util.getResult(test, map));
		} catch (EvaluationException e) {
			e.printStackTrace();
		}
	}*/
	public static int strToInt(String str){
		if(str == null)
			return 0;
		if(str.length() == 0)
			return 0;
		try{
			BigDecimal bd = new BigDecimal(str);
			if(bd.compareTo(new BigDecimal(Integer.MAX_VALUE)) < 0)
				return Integer.valueOf(new BigDecimal(str).toPlainString());
			else
				return Integer.MAX_VALUE;
		}catch(Exception e){
			for(int i = 0; i < str.length(); i++){
		    	if(str.charAt(i) != ' '){
					str = str.substring(i);
					break;
				}
			}
	    	str = str.replaceAll("[^0-9-+]", "a");
			if(str.indexOf("a") != -1)
				str = str.substring(0, str.indexOf("a"));
			if(str.length() == 0)
				return 0;
			char first = str.charAt(0);
			str = str.substring(1);
			if(str.indexOf("-") != -1)
				str = str.substring(0, str.indexOf("-"));
			if(str.indexOf("+") != -1)
				str = str.substring(0, str.indexOf("+"));
			str = ((first == '+') ? "" : first) + str;
			if(str.length() == 0)
				return 0;
			if(str.length() == 1 && str.equals("-"))
				return 0;
			try{
				return Integer.valueOf(str);
			}catch(Exception e1){
			    if(str.indexOf("-") != -1){
					return Integer.MIN_VALUE;
				}
				return Integer.MAX_VALUE;
			}
		}
	}
	public static boolean checkException(String expression){
		if(expression.matches("^.*(#\\{[^#]*\\}){2,}.*")){
			return false;
		}
		expression = expression.replaceAll("#\\{.\\}", "1");
		Map<String, Object> map = new HashMap<String, Object>();
		Evaluator evaluator = new Evaluator();
		try {
			StUtil.getResult(expression, map);
			return true;
		} catch (EvaluationException e) {
			return false;
		}
	}
	public static boolean isValidDate(String date){
		if(null == date)
			return false;
		if(date.length() < 8)
			return false;
		try {
			boolean result = false;
			SimpleDateFormat formatter;
			char dateSpace = date.charAt(4);
			String format[];
			if((dateSpace == '-') || (dateSpace == '/')){
				format = new String[4];
				String strDateSpace = Character.toString(dateSpace);
				format[0] = "yyyy" + strDateSpace + "MM" + strDateSpace + "dd";
				format[1] = "yyyy" + strDateSpace + "MM" + strDateSpace + "d";
				format[2] = "yyyy" + strDateSpace + "M" + strDateSpace + "dd";
				format[3] = "yyyy" + strDateSpace + "M" + strDateSpace + "d";
			}
			else{
				format = new String[1];
				format[0] = "yyyyMMdd";
			}
			for(int i = 0; i < format.length; i++){
				String aFormat = format[i];
				formatter = new SimpleDateFormat(aFormat);
				formatter.setLenient(false);
				String tmp = formatter.format(formatter.parse(date));
				if(date.equals(tmp)){
					result = true;
					break;
				}
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	//�ж����㹫ʽ�Ƿ���ȷ
	public static boolean checkExpression(String expression, Map<String, Object> map){
		expression = expression.replaceAll(" ", "");
		if(expression.matches("^.*[^><,=\\+\\-\\*\\/\\(\\&\\|](#\\{[^#]*\\}).*"))
			return false;
		if(expression.matches("^.*(#\\{[^#]*\\})[^><,=\\+\\-\\*\\/\\)\\&\\|].*"))
			return false;
		try {
			StUtil.getResult(expression, map);
			return true;
		} catch (EvaluationException e) {
			e.printStackTrace();
			return false;
		}
	}
	//�ж��ַ����ǲ���double
	public static boolean strIsDouble(String str){
		if(str == null || str.length() == 0) return false;
		if("0".equals(str)) return true;
		if((str.charAt(0) == '0' && (str.length() > 1 && str.charAt(1) != '.' && str.charAt(1) != 'E' && str.charAt(1) != 'e' )) || str.charAt(0) == ' ') return false;
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//�ж������Ƿ�Ϊ��
	public static boolean judgeIsNull(String appointVariable){
		boolean b = false;
		if(appointVariable!=null && !appointVariable.equals("0")
				&&!appointVariable.equals("")){
			b = true;
		}
		return b;
	}
	
	//ǧ��λ��ʽ�����
	public static String formatAmount(String amount){
		DecimalFormat format = new DecimalFormat();
		if(amount.contains(".")){
			if(amount.split("\\.")[1].length() == 3){
				format.applyPattern("##,##0.000");
			}else{
				format.applyPattern("##,##0.00");
			}
		}
		else format.applyPattern("##,###.##");
		double tmp = Double.valueOf(amount);
		return format.format(tmp);
	}
	
	public static String formatAmountAll(String amount){
		if(!strIsDouble(amount)){
			return amount;
		}
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("##,##0.00");
		double tmp = Double.valueOf(amount);
		return format.format(tmp);
	}
	
	public static String MD5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for(int offset = 0; offset < b.length; offset++){
				i = b[offset];
				if(i < 0) i += 256;
				if(i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			
		}
		return str;
	}
	
	public static String strAddOne(String str){
		if(str == null || str.length() == 0) return str;
		char[] ch = str.toCharArray();
		for(int i = ch.length - 1; i >= 0; i--){
			if(ch[i] == 90) ch[i] = 65;
			else if(ch[i] == 122) ch[i] = 97;
			else if(ch[i] == 57) ch[i] = 48;
			else ch[i]++;
		}
		return String.valueOf(ch);
	}
	
	public static class PinYin2Abbreviation {

		// �������ĵı��뷶Χ��B0A1��45217��һֱ��F7FE��63486��
		private static int BEGIN = 45217;
		private static int END = 63486;

		// ������ ĸ��ʾ�����������GB2312�еĳ��ֵĵ�һ�����֣�Ҳ����˵�������Ǵ�������ĸa�ĵ�һ�����֡�
		// i, u, v��������ĸ, �Զ��������ǰ�����ĸ
		private static char[] chartable = { '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', 'Ŷ', 'ž', '��', 'Ȼ', '��', '��', '��', '��', '��', '��', 'ѹ', '��', };

		// ��ʮ������ĸ�����Ӧ��ʮ�߸��˵�
		// GB2312�뺺������ʮ���Ʊ�ʾ
		private static int[] table = new int[27];

		// ��Ӧ����ĸ�����
		private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };

		// ��ʼ��
		static {
			for (int i = 0; i < 26; i++) {
				table[i] = gbValue(chartable[i]);// �õ�GB2312�������ĸ����˵��ʮ���ơ�
			}
			table[26] = END;// ������β
		}

		// ------------------------public������------------------------
		// ����һ���������ֵ��ַ�������һ������ƴ������ĸ���ַ��� ����Ҫ��һ��������˼·���£�һ�����ַ����롢�жϡ����

		public static String cn2py(String SourceStr) {
			String Result = "";
			int StrLength = SourceStr.length();
			int i;
			try {
				for (i = 0; i < StrLength; i++) {
					Result += Char2Initial(SourceStr.charAt(i));
				}
			} catch (Exception e) {
				Result = "";
				e.printStackTrace();
			}
			return Result;
		}

		// ------------------------private������------------------------
		/**
		 * �����ַ�,�õ�������ĸ,Ӣ����ĸ���ض�Ӧ�Ĵ�д��ĸ,�����Ǽ��庺�ַ��� '0' ����* ����
		 */
		private static char Char2Initial(char ch) {
			// ��Ӣ����ĸ�Ĵ���Сд��ĸת��Ϊ��д����д��ֱ�ӷ���
			if (ch >= 'a' && ch <= 'z') {
				return (char) (ch - 'a' + 'A');
			}
			if (ch >= 'A' && ch <= 'Z') {
				return ch;
			}
			// �Է�Ӣ����ĸ�Ĵ���ת��Ϊ����ĸ��Ȼ���ж��Ƿ������Χ�ڣ�
			// �����ǣ���ֱ�ӷ��ء�
			// ���ǣ���������ڵĽ����жϡ�
			int gb = gbValue(ch);// ����ת������ĸ
			if ((gb < BEGIN) || (gb > END))// ���������֮ǰ��ֱ�ӷ���
			{
				return ch;
			}
			int i;
			for (i = 0; i < 26; i++) {// �ж�ƥ��������䣬ƥ�䵽��break,�ж��������硰[,)��
				if ((gb >= table[i]) && (gb < table[i + 1])) {
					break;
				}
			}
			if (gb == END) {// ����GB2312�������Ҷ�
				i = 25;
			}
			return initialtable[i]; // ����������У���������ĸ
		}

		/**
		 * ȡ�����ֵı��� cn ���� ����
		 */
		private static int gbValue(char ch) {// ��һ�����֣�GB2312��ת��Ϊʮ���Ʊ�ʾ��
			String str = new String();
			str += ch;
			try {
				byte[] bytes = str.getBytes("GB2312");
				if (bytes.length < 2) {
					return 0;
				}
				return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
			} catch (Exception e) {
				return 0;
			}
		}

	}
	
	public static void main(String[] args) throws Exception {
/*		BufferedReader reader = new BufferedReader(new FileReader(new File("D:/one.txt")));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:/property.csv")));
		String tmp = null;
		while((tmp = reader.readLine()) != null){
			String[] str = tmp.split(",");
			char[] ch = str[1].toCharArray();
			for(int i = ch.length - 1; i >= 0; i--){
				if(ch[i] == 90) ch[i] = 65;
				else if(ch[i] == 122) ch[i] = 97;
				else if(ch[i] == 57) ch[i] = 48;
				else ch[i]++;
			}
			writer.write(str[0] + "," + String.valueOf(ch) + "," + str[2] + "\n");
		}
		reader.close();
		writer.close();*/
		Map v = new HashMap<String, String>();
		v.put("smallProduct", "'a'");
		v.put("ifFixedWork", "'a'");
		v.put("applyLimit", "1");
		String rule = "!(#{smallProduct}=='a')";
		System.out.println(getResult(rule, v));
		System.out.println(Double.valueOf("0e-7"));
	}
}
