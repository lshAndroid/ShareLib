package com.jdjtlibrary.wssocket;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 * @author chenfei
 */
public class RegexKit {
	/**10进制数*/
	private static final String REGEX_NUMBER_DECIMAL = "^[0-9]+$";
	/**二进制数*/
	private static final String REGEX_NUMBER_BINARY = "^[0-1]+$";
	/**16进制数*/
	private static final String REGEX_NUMBER_HEX = "^[0-9a-fA-F]+$";


	/**
	 * 验证是否为10进制字符串<p>
	 * 只是纯数字验证，若前面有+-号，或为小数函数会返回false
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isDecimalNumber(String str) {
		return check(str, REGEX_NUMBER_DECIMAL);
	}

	/**
	 * 验证是否为2进制字符串
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isBinaryNumber(String str){
		return check(str, REGEX_NUMBER_BINARY);
	}

	/**
	 * 验证是否为16进制字符串
	 * @param str 待验证的字符串
	 * @return
	 */
	public static boolean isHexNumber(String str){
		return check(str, REGEX_NUMBER_HEX);
	}

	/**
	 * 是否匹配正则表达式
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean check(String str, String regex){
		if(str == null && regex == null)return true;
		if(str == null || regex == null)return false;
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(str);
		return matcher.matches();
	}

	/**
	 * 查找第一个匹配的字符串
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String findFirst(String str, String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.find() ? matcher.group() : null;
	}

	/**
	 * 查找所有匹配的字符串
	 * @param regex
	 * @param source
	 * @return
	 */
	public static ArrayList<String> findAll(String source, String regex){
		ArrayList<String> res = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while(matcher.find()){
			res.add(matcher.group());
		}
		return res;
	}
}