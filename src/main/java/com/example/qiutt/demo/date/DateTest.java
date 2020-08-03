package com.example.qiutt.demo.date;

import com.example.qiutt.demo.common.CalculateConstant;
import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-07-09 17:25
 */
@Slf4j
public class DateTest {

	@Test
	public void getCurrentMonthDays() throws ParseException {
		Date time = DateUtils.strToDate("2020-05-09 14:42:17.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Integer daysOfMonth=DateUtils.getCurrentMonthDays(time);
		log.info("{}",DateUtils.getCurrentMonthDays(time));

		BigDecimal amount=new BigDecimal("67");
		BigDecimal result=amount.divide(BigDecimal.valueOf(daysOfMonth), CalculateConstant.NORMAL_SCALE, CalculateConstant.NORMAL_ROUDING_MODE)
				.multiply(BigDecimal.valueOf(23));
		log.info("{}",result);


	}

	@Test
	public void getIntervalMonth() throws ParseException {
		Date time = DateUtils.strToDate("2020-05-09 14:42:17.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date time1 = DateUtils.strToDate("2020-06-08 14:42:17.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);

		log.info("{}",DateUtils.getIntervalMonth(time,time1));
		log.info("{}",DateUtils.addMonth(time,1));

	}

	@Test
	public void getIntervalHours() throws ParseException {
		Date time = DateUtils.strToDate("2020-05-09 10:42:17.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date time1 = DateUtils.strToDate("2020-06-08 15:59:59.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Long millisecond=time1.getTime()-time.getTime();//毫秒差
		Float hours=millisecond/1000/60/60f;
		log.info("{}",millisecond);
		log.info("{}",hours);
		log.info("{}",BigDecimal.valueOf(millisecond));

		log.info("{}",BigDecimal.valueOf(hours));

		log.info("{}",BigDecimal.valueOf(0.2).setScale(0,BigDecimal.ROUND_UP));
//		BigDecimal value=BigDecimal.valueOf(52.205);
//		Double double1=value.doubleValue();
//		log.info("double1 {}",double1);
//		log.info("double1 {}",value.longValue());
//		Long part1=value.longValue();
//		BigDecimal value1=value.subtract(BigDecimal.valueOf(part1));
//		Long part2=value1.longValue();
//		log.info("part1 {}",part1);
//		log.info("part2 {}",value1.toString());



	}
}
