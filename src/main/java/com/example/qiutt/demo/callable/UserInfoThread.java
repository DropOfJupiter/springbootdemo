package com.example.qiutt.demo.callable;

import com.example.qiutt.demo.common.UserInfoModel;

import java.util.concurrent.Callable;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 19:31
 */
public class UserInfoThread implements Callable<UserInfoModel> {
	private String name;

	public UserInfoThread(String name) {
		this.name = name;
	}

	@Override
	public UserInfoModel call() throws Exception {
		UserInfoModel userInfoModel=new UserInfoModel();
		userInfoModel.setName(name);
		return userInfoModel;
	}
}
