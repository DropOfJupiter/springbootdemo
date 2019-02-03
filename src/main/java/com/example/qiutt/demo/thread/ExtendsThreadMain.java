package com.example.qiutt.demo.thread;

import com.example.qiutt.demo.DemoApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-02-03 18:41
 */
public class ExtendsThreadMain {
	public static void main(String[] args) {
		CountOperate countOperate=new CountOperate();

		Thread thread=new Thread(countOperate);
		thread.setName("A");
		thread.start();

		thread=new Thread(countOperate);
		thread.setName("B");
		thread.start();
	}
}
