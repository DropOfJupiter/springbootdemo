package com.example.qiutt.demo.guava;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-24 16:43
 */
@Slf4j
public class ArrayTest {

	@Test
	public void arraySplit(){
		String[] strings=new String[]{"11","221","222","223","224","225","2241","2252"};
		List list= Arrays.asList(strings);
		int size=2;
		int everysize=strings.length/size;
		for(int x=0;x<size;x++){
			String[] string=ArrayUtil.sub(strings,x*everysize,x*everysize+everysize);
			log.info(JSONUtil.toJsonStr(string));
		}
		String[] string0=ArrayUtil.sub(strings,0,3);

		String[] string1=ArrayUtil.sub(strings,3,6);
			log.info(JSONUtil.toJsonStr(string0));
		log.info(JSONUtil.toJsonStr(string1));


	}

	@Test
	public void test1(){
		Map<String,BigDecimal> map=new HashMap<>();
		UserInfoModel model=new UserInfoModel();
		log.info("年龄"+Optional.ofNullable(model.getAge()).orElse(11));
	}
}
