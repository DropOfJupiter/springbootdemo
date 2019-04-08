package com.example.qiutt.demo.countDownLatch;

import lombok.extern.slf4j.Slf4j;
import zipkin2.Call;

import java.util.concurrent.CountDownLatch;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 17:28
 */
@Slf4j
public class NetworkHealthChecker extends BaseHealthChecker {
	public NetworkHealthChecker (CountDownLatch latch)  {
		super("Network Service", latch);
	}

	@Override
	public void verifyService()
	{
		log.info("[{}] is checking",this.getServiceName());
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		log.info("[{}] is UP",this.getServiceName());
	}
}
