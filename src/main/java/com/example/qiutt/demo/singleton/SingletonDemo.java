package com.example.qiutt.demo.singleton;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-03 10:34
 */
public class SingletonDemo {
	public static void main(String[] args) {

		Singleton singleton = Singleton.getInstantce();
		System.out.println("counter1: " + singleton.counter1);
		System.out.println("counter2: " + singleton.counter2);
	}
}
