package com.example.qiutt.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-09-29 15:23
 */
@Slf4j
public class StringUtil {


	public static String upperCaseByIndex(String string){
		char[] ch=string.toCharArray();
		if (ch[0]>='a'&&ch[0]<='z')
			ch[0]= (char) (ch[0]-32);
		return  new String(ch);
	}
}
