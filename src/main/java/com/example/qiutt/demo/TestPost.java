package com.example.qiutt.demo;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-02-28 17:27
 */
@Slf4j
public class TestPost {
	String url="http://kibana.kpy.com/elasticsearch/_msearch?rest_total_hits_as_int=true&ignore_throttled=true";

	public HashMap<String, Object> requestParams(){
		//可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("index", "app-dev-kpy-ddos-server-*");
		paramMap.put("ignore_unavailable", true);
		paramMap.put("preference", 1582861433772L);
		return paramMap;
	}

	public HashMap<String, String> requestHeaders(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("kbn-name", "kibana");
		paramMap.put("kbn-xpack-sig", "13488ec7bab51b6e554e2291f1a62052");
		//paramMap.put("preference", 1582861433772L);
		return paramMap;
	}
	@Test
	public void sendHutool(){
		String result = HttpUtil.post(url, requestParams());
		log.info("result:{}", result);
	}

	@Test
	public void sendHttpClient(){
		String requestParams = JSONObject.toJSONString(requestParams());
		HashMap<String, String> requestHeaders=requestHeaders();
		JSONObject jb=new JSONObject();
		jb.put("code",0);
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(300 * 1000)
					.setConnectTimeout(300 * 1000)
					.build();
			HttpPost post = new HttpPost(url);
			post.setConfig(requestConfig);
			post.setHeader("Content-Type","application/json;charset=utf-8");
			for (Map.Entry<String, String> m : requestHeaders.entrySet()) {
				post.setHeader(m.getKey(),m.getValue());
			}
			StringEntity postingString = new StringEntity(requestParams,
					"utf-8");
			post.setEntity(postingString);
			HttpResponse response = httpClient.execute(post);
			String content = EntityUtils.toString(response.getEntity());
			System.out.println(content);
		} catch (SocketTimeoutException e) {
			log.error("超时{}",JSONObject.toJSONString(e));
		} catch (Exception e) {
			log.error("异常{}",JSONObject.toJSONString(e));
		}
	}
}
