package com.example.qiutt.demo.async;

import com.example.qiutt.demo.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-12-20 11:07
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {

	@Autowired
	private AsyncExample asyncExample;

	@Test
	public void testAsync() throws Exception {
		long start = System.currentTimeMillis();
		log.info("当前线程{}",Thread.currentThread().getName());
		//AsyncExample asyncExample=new AsyncExample();
		Future<String> task1 = asyncExample.doTaskOne();
		Future<String> task2 = asyncExample.doTaskTwo();
		Future<String> task3 = asyncExample.doTaskThree();
		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

}
