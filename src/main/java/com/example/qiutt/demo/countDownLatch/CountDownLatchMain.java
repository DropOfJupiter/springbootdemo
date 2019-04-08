package com.example.qiutt.demo.countDownLatch;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 17:40
 */
public class CountDownLatchMain {
	public static void main(String[] args)
	{
		boolean result = false;
		try {
			result = ApplicationStartUpUtils.checkExternalServices();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("External services validation completed !! Result was :: "+ result);
	}
}
