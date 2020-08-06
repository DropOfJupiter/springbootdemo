package com.example.qiutt.demo.singleton;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-03 10:34
 */
public class Singleton {
	//private static Singleton mInstance = new Singleton();// 位置1
	public static int counter1=3;
	public static int counter2 = 2;

    private static Singleton mInstance = new Singleton();// 位置2

	private Singleton() {
		counter1++;
		counter2++;
	}

	public static Singleton getInstantce() {
		return mInstance;
	}
}
