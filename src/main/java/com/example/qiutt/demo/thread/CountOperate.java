package com.example.qiutt.demo.thread;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-02-03 18:38
 */
@Slf4j
public class CountOperate extends Thread{
	public CountOperate() {
		log.info("CountOperate--begin:");
		log.info("Thread.currentThread().getName():"+Thread.currentThread().getName());
		// isAlive的作用是测试线程是否处于活动状态（所谓活动状态就是指线程已经启动，但是尚未终止），这段其实点击isAlive可以看到解释
		log.info("Thread.currentThread().isAlive():"+Thread.currentThread().isAlive());
		log.info("this.getName():"+this.getName());
		log.info("this.isAlive():"+this.isAlive());
		log.info("CountOperate--end:");
	}

	@Override
	public void run() {
		super.run();
		log.info("run--begin:");
		log.info("Thread.currentThread().getName():"+Thread.currentThread().getName());
		log.info("Thread.currentThread().isAlive():"+Thread.currentThread().isAlive());
		log.info("this.getName():"+this.getName());
		log.info("this.isAlive():"+this.isAlive());
		log.info("run--end:");
	}
}
