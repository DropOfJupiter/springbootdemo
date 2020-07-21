package com.example.qiutt.demo.utils;

import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-07-17 15:33
 */
@Slf4j
public class ReflectUtils {
	public static Boolean checkFieldAllNull(Object obj) throws IllegalAccessException {
		Boolean allNull=true;
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(obj) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
				allNull=false;
				break;
			}
		}
		return allNull;
	}
}
