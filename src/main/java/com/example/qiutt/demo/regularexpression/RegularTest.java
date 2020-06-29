package com.example.qiutt.demo.regularexpression;

import com.example.qiutt.demo.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-09-26 17:43
 */
@Slf4j
public class RegularTest {

	@Test
	public void  test(){
		String request="admin.test.kaopuyun.com";
		log.info(WebUtils.getRootDomain(request));
	}

	@Test
	public void getNumber(){
		String string ="3Month";
		String regEx="[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(string);
		Integer times=Integer.valueOf(m.replaceAll(""));
		System.out.println(times);
	}
}
