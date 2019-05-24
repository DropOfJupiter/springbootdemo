package com.example.qiutt.demo.callable;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 19:43
 */
@Slf4j
public class CallableMain {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		Stopwatch stopwatch = new Stopwatch();
//		stopwatch.start();
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//配置核心线程数
		executor.setCorePoolSize(5);
		//配置最大线程数
		executor.setMaxPoolSize(20);
		//配置队列大小
		executor.setQueueCapacity(Integer.MAX_VALUE);
		//配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix("Async-service-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//执行初始化
		executor.initialize();
		List<Future<UserInfoModel>> userInfoModels = new CopyOnWriteArrayList<Future<UserInfoModel>>();
		//CountDownLatch countDownLatch = new CountDownLatch(100000);
		for (int i = 0; i < 10000000; i++) {
			//创建线程，提交到线程池
			Future<UserInfoModel> userInfoModel = executor.submit(new UserInfoThread(String.valueOf(i)));
			userInfoModels.add(userInfoModel);
			//countDownLatch.countDown();
		}
//		try {
//			countDownLatch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("阻塞异常");
//		}
		/**
		 * 如果没有如下代码。程序执行之后仍旧会有线程池里的线程处于waiting的状态
		 */
		//不能直接executor.shutdown();所以用这个土办法控制线程池关闭
		AtomicInteger successCount=new AtomicInteger(0);
		while (successCount.equals(userInfoModels.size())) {
			for (Future<UserInfoModel> userInfoModelFuture : userInfoModels) {
				if (userInfoModelFuture.isDone()) {
					successCount.incrementAndGet();
					continue;
				} else {
					break;
				}
			}
		}
		executor.shutdown();
		//stopwatch.stop();
		//log.info("calleble获取[{}]的数据，耗时[{}]", userInfoModels.size(), stopwatch.elapsedTime(TimeUnit.MILLISECONDS));

	}
}
