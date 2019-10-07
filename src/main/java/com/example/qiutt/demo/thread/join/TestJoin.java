package com.example.qiutt.demo.thread.join;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-10-06 11:41
 */
@Slf4j
public class TestJoin {

	@Test
	public void testJoin() throws InterruptedException {
		log.info("主线程：[{}]开始",Thread.currentThread().getName());
		ThreadTest threadTestA=new ThreadTest("A");
		ThreadTest threadTestB=new ThreadTest("B");
		ThreadTest threadTestC=new ThreadTest("C");

		threadTestA.start();
		//查看join源码，会看到其实join()方法的底层是利用wait()方法实现的
		//也就是说主线程调用threadTestA.join()其实是调用了threadTestA.wait()，主线程先获得了threadTestA对象的锁，使主线程进入了threadTestA对象的等待池
		//也就是说主线程只能等threadTestA
		threadTestB.start();
		//threadTestA.join();
		threadTestC.start();
		log.info("主线程：[{}]结束",Thread.currentThread().getName());
	}

	class ThreadTest extends Thread {
		private String name;
		public ThreadTest(String name){
			this.name=name;
		}
		public void run(){
			log.info("子线程:[{}]开始",this.name);
			for(int i=1;i<=5;i++){
				log.info(name+"-"+i);
			}
			log.info("子线程:[{}]结束",this.name);
		}
	}
}
