package com.example.qiutt.demo.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-03-27 18:23
 */
@Slf4j
public class PerformanceWithForCycle {

	@Test
	public void  PerformanceWithForCycle(){
//		List<Integer> integers=new ArrayList<>(100000000);
//		Stopwatch stopwatch=new Stopwatch();
//		stopwatch.start();
//		for (Integer integer:integers){
//			integer=integer+1;
//			integers.add(integer);
//		}
//		stopwatch.stop();
//		log.info("for循环耗时[{}]",stopwatch.elapsed(TimeUnit.NANOSECONDS));
//
//		integers=new ArrayList<>(100000000);
//		stopwatch=new Stopwatch();
//		stopwatch.start();
//		integers.stream().parallel().map(Integer->{
//			return Integer.intValue()+1;
//		}).collect(Collectors.toList());
//		stopwatch.stop();
//		log.info("stream循环耗时[{}]",stopwatch.elapsed(TimeUnit.NANOSECONDS));

	}
}
