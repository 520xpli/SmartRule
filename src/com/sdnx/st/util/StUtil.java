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
	 * 根据表达式计算结果
	 * @param expression 计算表达式,列如：#{a}+(#{b}-#{c})/#{d}
	 * @param variables  参数集合,map的key、value都是String类型
	 * @return 返回计算结果字符串
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
	
	//判断运算公式是否正确
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
	//判断字符串是不是double
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
	//判断数据是否为空
	public static boolean judgeIsNull(String appointVariable){
		boolean b = false;
		if(appointVariable!=null && !appointVariable.equals("0")
				&&!appointVariable.equals("")){
			b = true;
		}
		return b;
	}
	
	//千分位格式化金额
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

		// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
		private static int BEGIN = 45217;
		private static int END = 63486;

		// 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
		// i, u, v都不做声母, 自定规则跟随前面的字母
		private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };

		// 二十六个字母区间对应二十七个端点
		// GB2312码汉字区间十进制表示
		private static int[] table = new int[27];

		// 对应首字母区间表
		private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };

		// 初始化
		static {
			for (int i = 0; i < 26; i++) {
				table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
			}
			table[26] = END;// 区间表结尾
		}

		// ------------------------public方法区------------------------
		// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出

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

		// ------------------------private方法区------------------------
		/**
		 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　* 　　
		 */
		private static char Char2Initial(char ch) {
			// 对英文字母的处理：小写字母转换为大写，大写的直接返回
			if (ch >= 'a' && ch <= 'z') {
				return (char) (ch - 'a' + 'A');
			}
			if (ch >= 'A' && ch <= 'Z') {
				return ch;
			}
			// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
			// 若不是，则直接返回。
			// 若是，则在码表内的进行判断。
			int gb = gbValue(ch);// 汉字转换首字母
			if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
			{
				return ch;
			}
			int i;
			for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
				if ((gb >= table[i]) && (gb < table[i + 1])) {
					break;
				}
			}
			if (gb == END) {// 补上GB2312区间最右端
				i = 25;
			}
			return initialtable[i]; // 在码表区间中，返回首字母
		}

		/**
		 * 取出汉字的编码 cn 汉字 　　
		 */
		private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
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
