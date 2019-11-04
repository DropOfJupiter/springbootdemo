package com.example.qiutt.demo.localtime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-11-04 17:46
 */

@Slf4j
public class LocalTimeLearning {

	@Test
	public void test(){
		Date now=new Date();
		LocalTime localTime=LocalTime.now();
		String beginTime="20:00";
		String endTime="08:00";
		String strTime=localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime begin=LocalTime.parse(beginTime,DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime end=LocalTime.parse(endTime,DateTimeFormatter.ISO_LOCAL_TIME);
		log.info("localTime:{}，begin:{}，end:{}",strTime,begin,end);
		log.info("localDate:{}", LocalDate.parse("2019-11-03").getDayOfWeek().getValue());
		Instant instant = now.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		localTime = localDateTime.toLocalTime();
//		if(localTime.isBefore(end)&&localTime.isAfter(begin)){
//			log.info("在这个时间内");
//		}

		String testStr="04:30:02";
		LocalTime testLocalTime=LocalTime.parse(testStr,DateTimeFormatter.ISO_LOCAL_TIME);
		if(begin.isBefore(end)){
			//没有跨天
			if(testLocalTime.isAfter(begin)&&testLocalTime.isBefore(end)){
				log.info("在这个时间内");
			}
		}else{
			//跨天
			if(testLocalTime.isAfter(begin)||testLocalTime.isBefore(end)){
				log.info("在这个时间内");
			}
		}
	}
}
