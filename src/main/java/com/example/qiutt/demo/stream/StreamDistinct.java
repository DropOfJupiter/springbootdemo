package com.example.qiutt.demo.stream;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.example.qiutt.demo.common.UserInfoModel;
import com.example.qiutt.demo.enums.MaxValueMultipleEnum;
import com.example.qiutt.demo.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-08-21 14:26
 */
@Slf4j
public class StreamDistinct {
	@Test
	public void streamDistinctByKey() {
		List<UserInfoModel> userInfoModels=new ArrayList<UserInfoModel>();
		for(int i=0;i<10;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10,16));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});
		//根据指定字段进行去重
		List<UserInfoModel> userInfoModels1=userInfoModels.stream()
				.collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(u ->u.getAge()))), ArrayList::new));
		userInfoModels1.forEach(System.out::println);
	}

	@Test
	public void test() throws IllegalAccessException {
//		UserInfoModel userInfoModel= new UserInfoModel();
//		userInfoModel.setFullName(null);
		UserInfoModel userInfoModel=UserInfoModel.builder().fullName(null).build();
		log.info("{}，{}",userInfoModel.toString(),userInfoModel.hashCode());
		if(!ReflectUtils.checkFieldAllNull(userInfoModel)){
			log.info("{}", JSON.parseObject(userInfoModel.toString(),UserInfoModel.class));
		}

	}
	@Test
	public void fromEnum(){
		String str="FOUR_TIMES_BASE_VALUE";
		log.info("{}", MaxValueMultipleEnum.valueOf(str).getCode());
	}
}
