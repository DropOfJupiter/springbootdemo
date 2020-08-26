package com.example.qiutt.demo.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-13 10:54
 */
@Slf4j
public class ThreadLocalTest {

	@Test
	public void test(){
		ThreadLocalContext1.set("userId",1);
		ThreadLocalContext2.set("userId",2);

		Thread t=Thread.currentThread();

	}
}
