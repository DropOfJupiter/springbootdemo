package com.example.qiutt.demo.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 17:31
 */
public class ApplicationStartUpUtils {
	private static List<BaseHealthChecker> _services;

	private static CountDownLatch _latch;

	//单例
	private final static ApplicationStartUpUtils INSTANCE = new ApplicationStartUpUtils();

	public static ApplicationStartUpUtils getInstance() {
		return INSTANCE;
	}

	public static boolean checkExternalServices() throws Exception {
		//Initialize the latch with number of service checkers
		_latch = new CountDownLatch(3);

		//All add checker in lists
		_services = new ArrayList<BaseHealthChecker>();
		_services.add(new NetworkHealthChecker(_latch));
		_services.add(new CacheHealthChecker(_latch));
		_services.add(new DatabaseHealthChecker(_latch));

		//Start service checkers using executor framework
		Executor executor = Executors.newFixedThreadPool(_services.size());

		for (final BaseHealthChecker v : _services) {
			executor.execute(v);
		}

		//Now wait till all services are checked
		_latch.await();

		//Services are file and now proceed startup
		for (final BaseHealthChecker v : _services) {
			if (!v.isServiceUp()) {
				return false;
			}
		}
		return true;
	}
}
