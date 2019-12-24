package com.example.qiutt.demo.hutool;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-05-15 17:22
 */
@Slf4j
public class StringUtils {

	@Test
	public void test(){
		Long long1=10L;
		Long long2=2L;
		String str1="合并起来是"+long1+"-"+long2;
		log.info("str：{}", new Random().nextInt(2));

		String resourceUUIDs="mcc-3p00003odxmx,mcc-3p00003odxmy";
		if(org.springframework.util.StringUtils.isEmpty(resourceUUIDs)) {
			log.info("resourceUUIDs为空");
			return;
		}
		List<String> uuidList= StrUtil.split(resourceUUIDs,',');
		log.info("resourceUUIDs="+ resourceUUIDs);

		log.info("stringBuilder="+ JSONUtil.toJsonStr(uuidList));

		String resourceUUIDs1="mcc-3p00003odxmx1";
		log.info("array="+ resourceUUIDs.contains(resourceUUIDs1));

		Multimap<String,Integer> map= LinkedListMultimap.create();
		map.put("1",1);
		map.put("1",2);
		log.info("map="+ JSONUtil.toJsonStr(map));
		map.asMap().entrySet().forEach(entry->{
			String str=entry.getKey();
			Collection<Integer> values=entry.getValue();
			log.info("key:"+str);
			for (Integer i:values){
				log.info("value:"+i);
			}
		});

		String[] strings=new String[]{"1","2"};
		log.info("strings="+ JSONUtil.toJsonStr(strings));



	}
}
