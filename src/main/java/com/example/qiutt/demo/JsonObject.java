package com.example.qiutt.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-09-23 17:36
 */
@Slf4j
public class JsonObject {

	@Test
	public void getValueByKey(){
		UserInfoModel model=new UserInfoModel();
		model.setFullName("qiutt");

		JSONObject jsonObject=JSONObject.parseObject(JSONObject.toJSONString(model));
		String fullName= (String) jsonObject.get("fullName");
		log.info("fullName:[{}]",fullName);
	}
}
