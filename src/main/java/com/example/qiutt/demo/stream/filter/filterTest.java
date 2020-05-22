package com.example.qiutt.demo.stream.filter;

import cn.hutool.core.util.RandomUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-08 11:58
 */
@Slf4j
public class filterTest {

	@Test
	public void filter(){
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
		List<UserInfoModel> filterList=userInfoModels.stream().filter(new Predicate<UserInfoModel>() {
			@Override
			public boolean test(UserInfoModel userInfoModel) {
				return userInfoModel.getAge()>13;
			}
		}).collect(Collectors.toList());
		filterList.forEach(p -> {
			log.info("过滤后用户年龄{}", p.getAge());
		});
	}

}
