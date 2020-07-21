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


}
