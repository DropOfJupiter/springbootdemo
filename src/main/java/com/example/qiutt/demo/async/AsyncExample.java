package com.example.qiutt.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author qiutt
 * @description:
 * @Async  必须不同类间调用： A类--》B类.C方法()（@Async注释在B类/方法中），
 * 如果在同一个类中调用，会变同步执行,例如:A类.B()-->A类.@Async C()，原因是：底层实现是代理对注解扫描实现的，B方法上没有注解，没有生成相应的代理类。(当然把@Async加到类上也能解决但所有方法都异步了，一般不这么用！)
 * @create 2019-12-20 11:31
 */
@Slf4j
@Component
public class AsyncExample {

	private static Random random=new Random();

	@Async
	public Future<String> doTaskOne() throws Exception {
		log.info("{}开始做doTaskOne",Thread.currentThread().getName());
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(1000));
		long end = System.currentTimeMillis();
		log.info("完成doTaskOne，耗时：{}毫秒", (end - start));
		return new AsyncResult<String>("完成doTaskOne，耗时："+ (end - start)+"毫秒" );
	}

	@Async
	public Future<String> doTaskTwo() throws Exception {
		log.info("{}开始做doTaskTwo",Thread.currentThread().getName());
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(1000));
		long end = System.currentTimeMillis();
		log.info("完成doTaskTwo，耗时：{}毫秒", (end - start));

		return new AsyncResult<String>("完成doTaskTwo，耗时："+ (end - start)+"毫秒" );
	}

	@Async
	public Future<String> doTaskThree() throws Exception {
		log.info("{}开始做doTaskThree",Thread.currentThread().getName());
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(1000));
		long end = System.currentTimeMillis();
		log.info("完成doTaskThree，耗时：{}毫秒", (end - start));

		return new AsyncResult<String>("完成doTaskThree，耗时："+ (end - start)+"毫秒" );
	}
}
