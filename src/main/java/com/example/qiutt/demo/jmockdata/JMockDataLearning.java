package com.example.qiutt.demo.jmockdata;

import com.alibaba.fastjson.JSONObject;
//import com.github.jsonzou.jmockdata.JMockData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-12-25 11:15
 */
@Slf4j
public class JMockDataLearning {

	@Test
	public void mock() throws ClassNotFoundException {
		AtomicInteger completeCount = new AtomicInteger(0);
		AtomicInteger completeCount1 = new AtomicInteger(0);
		log.info("completeCount==completeCount1:{}",completeCount.get()==completeCount1.get());
//		Map<String, Class> tableStructure = new HashMap<>();
//		tableStructure.put("ID", Integer.class);
//		tableStructure.put("NAME", String.class);
//		tableStructure.put("AGE",Integer.class);
//
//		List<Map<String, Object>> list = new ArrayList<>(3);
//		for (int i = 0; i < 3; i++) {
//			Map<String, Object> mockMap = new HashMap<>(tableStructure.size());
//			for (Map.Entry<String, Class> entry : tableStructure.entrySet()) {
//				String key = entry.getKey();
//				Class value = entry.getValue();
//				//mockMap.put(key, JMockData.mock(value));
//			}
//			list.add(mockMap);
//		}
//		log.info("list:{}",JSONObject.toJSONString(list));
	}

	@Test
	public void mock2() throws ClassNotFoundException {
		List<Map<String, Object>> list = new ArrayList<>(3);
		Map<String, String> tableStructure = new HashMap<>();
		tableStructure.put("ID", "java.lang.Integer");
		tableStructure.put("NAME", "java.lang.String");
		tableStructure.put("AGE","java.lang.Integer");
		Set<String> keys = tableStructure.keySet().stream().sorted().collect(Collectors.toSet());
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = tableStructure.get(key);
			log.info("{}:{}",key,value);
		}
//		for (int i = 0; i < 3; i++) {
//			Map<String, Object> mockMap = new HashMap<>(tableStructure.size());
//			for (Map.Entry<String, String> entry : tableStructure.entrySet()) {
//				String key = entry.getKey();
//				String value = entry.getValue();
//				mockMap.put(key, JMockData.mock(Class.forName(value)));
//			}
//			list.add(mockMap);
//		}
		log.info("list:{}",JSONObject.toJSONString(list));
	}
}
