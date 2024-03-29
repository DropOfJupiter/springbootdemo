package com.example.qiutt.demo.localtime;

import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-11-04 17:46
 */

@Slf4j
public class LocalTimeLearning {

	@Test
	public void test() {
		Date now = new Date();
		LocalTime localTime = LocalTime.now();
		String beginTime = "20:00";
		String endTime = "08:00";
		String strTime = localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime begin = LocalTime.parse(beginTime, DateTimeFormatter.ISO_LOCAL_TIME);
		LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ISO_LOCAL_TIME);
		log.info("localTime:{}，begin:{}，end:{}", strTime, begin, end);
		log.info("localDate:{}", LocalDate.parse("2019-11-03").getDayOfWeek().getValue());
		Instant instant = now.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		localTime = localDateTime.toLocalTime();
//		if(localTime.isBefore(end)&&localTime.isAfter(begin)){
//			log.info("在这个时间内");
//		}

		String testStr = "04:30:02";
		LocalTime testLocalTime = LocalTime.parse(testStr, DateTimeFormatter.ISO_LOCAL_TIME);
		if (begin.isBefore(end)) {
			//没有跨天
			if (testLocalTime.isAfter(begin) && testLocalTime.isBefore(end)) {
				log.info("在这个时间内");
			}
		} else {
			//跨天
			if (testLocalTime.isAfter(begin) || testLocalTime.isBefore(end)) {
				log.info("在这个时间内");
			}
		}
	}

	@Test
	public void localDateOperate() {
		Date begin = new Date();
		Instant instant = begin.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime beginLocalDate = LocalDateTime.ofInstant(instant, zone);
		LocalDateTime plusLocalDateTime = beginLocalDate.plusMonths(1);
		log.info("beginLocalDate:{},plusLocalDateTime:{}", beginLocalDate, plusLocalDateTime);

		log.info("addMonth:{}", DateUtils.addMonth(begin, 1));


		log.info("getFisrtDayOfMonth:{}",DateUtils.getFisrtDayOfMonth("2019-01","-"));

		log.info("getLastDayOfMonth:{}",DateUtils.getLastDayOfMonth("2019-01","-"));

		log.info("begin.getTime():{}",begin.getTime());
	}

	@Test
	public void test1() {
		try {
			Date time=DateUtils.strToDate("2020-08-30 16:04:55.000", DateUtils.DateStyle.YYYY_MM);
			Date time1= DateUtils.getLastDayOfMonth(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
