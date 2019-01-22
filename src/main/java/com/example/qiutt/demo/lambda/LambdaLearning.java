package com.example.qiutt.demo.lambda;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-01-20 15:47
 */
@Slf4j
public class LambdaLearning {

	@Test
	public void getIdByKeys() {
		//数据初始化
		List<String> keys = new ArrayList<String>();
		String key1 = "id=1&cityId=11&";
		String key2 = "id=2&cityId=22&";
		String key3 = "id=3&cityId=33&";
		String key4 = "id=4&cityId=44&";

		keys.add(key1);
		keys.add(key2);
		keys.add(key3);
		keys.add(key4);

		KeyUtils.getIdByKeys(keys).forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer id) {
				log.info("无函数式编程，通过实例化匿名类实现accept来获取id:"+id);
			}
		});

		KeyUtils.getIdByKeys(keys).forEach(x -> log.info("无函数式编程，通过lambda直接来获取id:"+x));

		KeyUtils.getCityIdByKeys(keys).forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer id) {
				log.info("无函数式编程，通过实例化匿名类实现accept来获取cityId:"+id);
			}
		});

		KeyUtils.getCityIdByKeys(keys).forEach(x -> log.info("无函数式编程，通过lambda直接来获取cityId:"+x));

		KeyUtils.getIdByKeys(keys, new Function<String, Integer>() {
			@Override
			public Integer apply(String keyArray) {
				return Integer.valueOf(StrUtil.subBetween(keyArray, "id=", "&"));
			}
		}).forEach(x -> log.info("函数式编程，通过实例化匿名类实现accept来获取id:"+x));

		KeyUtils.getIdByKeys(keys, new Function<String, Integer>() {
			@Override
			public Integer apply(String keyArray) {
				return Integer.valueOf(StrUtil.subBetween(keyArray, "cityId=", "&"));
			}
		}).forEach(x -> log.info("函数式编程，通过实例化匿名类实现accept来获取cityId"+x));

		KeyUtils.getIdByKeys(keys, keyArray-> {
				return Integer.valueOf(StrUtil.subBetween(keyArray, "id=", "&"));
		}).forEach(x -> log.info("函数式编程，通过lambda直接来获取id:"+x));

		KeyUtils.getIdByKeys(keys, keyArray-> {
			return Integer.valueOf(StrUtil.subBetween(keyArray, "cityId=", "&"));
		}).forEach(x -> log.info("函数式编程，通过lambda直接来获取cityId:"+x));
	}

	@Test
	public void calculate(){

	}


}
