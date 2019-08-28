package com.example.qiutt.demo.jackson;

import com.alibaba.fastjson.JSON;
import com.example.qiutt.demo.common.UserInfoModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-08-26 19:56
 */
@Slf4j
public class JacksonSerialization {

	@Test
	public void nullDateSerialize() throws IOException {
		UserInfoModel model=new UserInfoModel();
		model.setName("测试空的时间属性序列化");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);//默认
		String modelJson=mapper.writeValueAsString(model);

		log.info("modelJson:{}",modelJson);

		UserInfoModel read=mapper.readValue(modelJson,UserInfoModel.class);

		log.info("read:{}", JSON.toJSONString(read));

	}
}
