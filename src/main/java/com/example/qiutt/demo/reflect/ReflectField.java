package com.example.qiutt.demo.reflect;

import com.alibaba.fastjson.JSON;
import com.example.qiutt.demo.common.UserInfoModel;
import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-11-27 14:52
 */
@Slf4j
public class ReflectField {

	@Test
	public void getField() throws IllegalAccessException {
//		UserInfoModel m=new UserInfoModel();
//		m.setAge(22);
//		m.setName("test");
//		Field beginField = ReflectionUtils.findField(m.getClass(), "name", String.class);
//		beginField.setAccessible(true);
//		Object value=beginField.get(m);
//		log.info("value:{}",value);
		double d=Math.random();
		Boolean isTrue=Boolean.TRUE;
		log.info("isTrue:[{}]，Math.random()*10:[{}]",d,d*10);
		List<String> list=new ArrayList<>();
		log.info("是否为空[{}]",CollectionUtils.isEmpty(list));
	}
}
