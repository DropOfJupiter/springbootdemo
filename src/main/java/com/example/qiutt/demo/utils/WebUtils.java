package com.example.qiutt.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe: http工具类
 * @author: william
 * @date:2012-6-12
 * @jdk:1.6
 * @version:1.0
 * 
 */
public class WebUtils {
	public static final String ENCODING = "utf-8";

	/**
	 * 抽取ROOT domain的模式
	 */
	private static final Pattern ROOT_DOMAIN_PATTERN = Pattern
			.compile(
					"(?<=//|\\.)[^.]*?\\.(com.cn|net.cn|org.cn|gov.cn|com.hk|com.tw|cn.com|com|cn|net|org|biz|info|cc|co|tv|me|tel|mobi|asia|hk|name|tm|sh|io|la|ws|vc|tw|pw|us|sc|cm|in|host|press|website|wang|香港|移动|公司|中国|网络)$",
					Pattern.CASE_INSENSITIVE);

	/**
	 * 获取根域名
	 * 
	 * @param request
	 * @return
	 * @author: JIANGWEI
	 * @date: 2014年6月18日
	 */
	public static String getRootDomain(String request) {
		Matcher matcher = ROOT_DOMAIN_PATTERN.matcher(request);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}
	}

	/**
	 * 获取根域名
	 *
	 * @param request
	 * @return
	 * @author: JIANGWEI
	 * @date: 2014年6月18日
	 */
	public static String getDomain(String request) {
		return request;
	}
}
