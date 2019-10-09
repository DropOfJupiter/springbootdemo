package com.example.qiutt.demo;

import cn.hutool.core.util.RandomUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-10-09 16:22
 */
@Slf4j
public class comparator {
	@Test
	public void sorted(){
		List<UserInfoModel> userInfoModels=new ArrayList<UserInfoModel>();
		for(int i=0;i<10;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10,35));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		//正序
		userInfoModels.sort(Comparator.comparing(UserInfoModel::getAge));
		userInfoModels.forEach(p -> {
			log.info("正序后用户年龄{}", p.getAge());
		});

		//正序
		userInfoModels.sort(Comparator.comparing(UserInfoModel::getAge).reversed());
		userInfoModels.forEach(p -> {
			log.info("倒序后用户年龄{}", p.getAge());
		});
	}
}
