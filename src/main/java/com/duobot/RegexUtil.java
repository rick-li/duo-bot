package com.duobot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static String extractPhonenum(String input) {
		Pattern p = Pattern
				.compile("(?<!(\\d)|(=))((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}(?!\\d)");
		Matcher m = p.matcher(input);
		if(m.find()){
			return m.group();
		}
		return "";
	}
	
	public static String extractDate(String input) {
		Pattern p = Pattern
				.compile("20\\d{2}-\\d{1,2}-\\d{2}");
		Matcher m = p.matcher(input);
		if(m.find()){
			return m.group();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
		
	}
}
