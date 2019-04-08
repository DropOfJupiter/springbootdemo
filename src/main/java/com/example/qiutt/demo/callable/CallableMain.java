package com.example.qiutt.demo.callable;

import com.example.qiutt.demo.common.UserInfoModel;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 19:43
 */
@Slf4j
public class CallableMain {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Stopwatch stopwatch=new Stopwatch();
		stopwatch.start();
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//配置核心线程数
		executor.setCorePoolSize(12);
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
		List<UserInfoModel> userInfoModels=new ArrayList<>(1000000);
		CountDownLatch countDownLatch = new CountDownLatch(userInfoModels.size());
		for(int i=0;i<1000000;i++){
			FutureTask<UserInfoModel> futureTask = new FutureTask<UserInfoModel>(new UserInfoThread(String.valueOf(i)));
			executor.execute(futureTask);
			while (futureTask.isDone()){
				userInfoModels.add(futureTask.get());
				countDownLatch.countDown();
			}
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("阻塞异常");
		}
		stopwatch.stop();
		log.info("calleble获取[{}]的数据，耗时[{}]",userInfoModels.size(),stopwatch.elapsedTime(TimeUnit.SECONDS));

		stopwatch=new Stopwatch();
		stopwatch.start();
		List<UserInfoModel> userInfoModels1=new ArrayList<>(1000000);
		for(int i=0;i<1000000;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setName(String.valueOf(i));
			userInfoModels1.add(userInfoModel);
		}
		stopwatch.stop();
		log.info("for获取[{}]的数据，耗时[{}]",userInfoModels1.size(),stopwatch.elapsedTime(TimeUnit.SECONDS));
	}
}
