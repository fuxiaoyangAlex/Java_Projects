package org.xblo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Exception;
import java.io.FileWriter;
import java.io.PrintWriter;

// X Tools
public class XT {
	public static final String WCRLF = "\r\n"; // windows回车换行
	public static final String UCRLF = "\n"; // unix回车换行
	public static final String EFILE = "c:\\xblo_c\\xblo_e.log"; // 程序异常DUMP文件
	public static final String LFILE = "c:\\xblo_c\\xblo.log"; // LOG文件
	public static final String TNSFILE = "c:\\xblo_c\\tns.xml"; // LOG文件

	// 替换html标记
	public static String toHTML(String value) {
		value = value.replace("&", "&amp;");
		value = value.replace(" ", "&nbsp;");
		value = value.replace("<", "&lt;");
		value = value.replace(">", "&gt;");
		value = value.replace("\r\n", "<br>");
		return value;
	}

	// 字符型转数值型
	public static int toInt(String value) {
		int i = -1;
		if (value == null || value.equals(""))
			return i;
		try {
			i = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			i = -1;
			e.printStackTrace();
		}
		return i;
	}

	// 编码转换
	public static String toChinese(String value) {
		if (value == null)
			return "";
		try {
			value = new String(value.getBytes("ISO8859_1"), "gb2312");
			return value;
		} catch (Exception e) {
			return "";
		}
	}

	// 日期型转字符型
	public static String dateFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	// 记录异常
	public static void logE(Exception e) {
		Date date = new Date();
		System.out.println("");
		System.out.println("异常堆栈:");
		e.printStackTrace();
		System.out.println("捕获时间:");
		System.out.println(dateFormat(date));
		try {
			FileWriter fw = new FileWriter(EFILE, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("");
			pw.println("异常堆栈:");
			e.printStackTrace(pw);
			pw.println("捕获时间:");
			pw.println(dateFormat(date));
			pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// 记录异常
	public static void logE(Exception e, String extraInfo) {
		logE(e);
		System.out.println("附加信息:");
		System.out.println(extraInfo);
		try {
			FileWriter fw = new FileWriter(EFILE, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("附加信息:");
			pw.println(extraInfo);
			pw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// 写文件
	public static void writeFile(String filePath, String str) {
		try {
			FileWriter fw = new FileWriter(filePath, true);
			fw.write(str);
			fw.write(WCRLF);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 写文件
	public static void writeFile(String str) {
		String dt = dateFormat(new Date());
		String strWithDate = dt + ": " + str;
		System.out.println(strWithDate);
		writeFile(LFILE, strWithDate);
	}
}
