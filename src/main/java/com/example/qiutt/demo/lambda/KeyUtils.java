package com.example.qiutt.demo.lambda;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-01-20 15:57
 */
public class KeyUtils {

	public static List<Integer> getIdByKeys(List<String> keys){
		List<Integer> idList=new ArrayList<Integer>(keys.size());
		for (String keyArray : keys) {
			Integer id= Integer.valueOf(StrUtil.subBetween(keyArray,"id=","&"));
			idList.add(id);
		}
		return idList;
	}

	public static List<Integer> getCityIdByKeys(List<String> keys){
		List<Integer> idList=new ArrayList<Integer>(keys.size());
		for (String keyArray : keys) {
			Integer id= Integer.valueOf(StrUtil.subBetween(keyArray,"cityId=","&"));
			idList.add(id);
		}
		return idList;
	}

	public static<String,Integer> List<Integer> getIdByKeys(List<String> keys, Function<String,Integer> function){
		List<Integer> idList=new ArrayList<Integer>(keys.size());
		for (String keyArray : keys) {
			Integer id= function.apply(keyArray);
			idList.add(id);
		}
		return idList;
	}
}
