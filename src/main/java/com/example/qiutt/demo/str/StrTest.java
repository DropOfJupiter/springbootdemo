package com.example.qiutt.demo.str;

import com.example.qiutt.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-09-29 15:26
 */
@Slf4j
public class StrTest {

	@Test
	public void upperCaseByIndex(){
		String str="23453";
		log.info(StringUtil.upperCaseByIndex(str));
	}
}
