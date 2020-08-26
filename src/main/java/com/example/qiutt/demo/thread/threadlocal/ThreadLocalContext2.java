package com.example.qiutt.demo.thread.threadlocal;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-13 10:52
 */
@Slf4j
public abstract class ThreadLocalContext2 {
	protected static ThreadLocal<JSONObject> context = InheritableThreadLocal.withInitial(JSONObject::new);
	
	public static void set(String name, Object value) {
		context.get().put(name, value);
	}

}
