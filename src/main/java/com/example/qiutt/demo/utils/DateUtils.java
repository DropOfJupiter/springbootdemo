package com.example.qiutt.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-11-05 15:29
 */
@Slf4j
public class DateUtils {

	/**
	 * 日期加减
	 *
	 * @param date 原日期
	 * @param amount 加减天数
	 * @return
	 */
	public static Date addMonth(Date date, int amount)  {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,  amount);
		return calendar.getTime();
	}

	/**
	 * 获得某月第一天
	 * @param date
	 * @return
	 */
	public static Date getFisrtDayOfMonth(String date,String split)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,Integer.valueOf(date.split(split)[0]));
		//设置月份
		cal.set(Calendar.MONTH, Integer.valueOf(date.split(split)[1])-1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date firstDayOfMonth = cal.getTime();
		return firstDayOfMonth;
	}

	/**
	 * 获得某月最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(String date,String split)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,Integer.valueOf(date.split(split)[0]));
		//设置月份
		cal.set(Calendar.MONTH, Integer.valueOf(date.split(split)[1])-1);
		//获取某月最小天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date lastDayOfMonth = cal.getTime();
		return lastDayOfMonth;
	}

	/**
	 * 获得指定时间的前n月，或者后n月 added by qiutt 20170523
	 *
	 * @param date
	 * @param preDays(preDays>0获取指定时间的后preDays天
	 *            preDays<0获取指定时间的前preDays天)
	 */
	public static Date getRecentMonth(Date date, Integer months,String dateStyleEnum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat(dateStyleEnum);
		String dateStr = format.format(calendar.getTime());
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			log.info(String.valueOf(e));
		}
		return date;

	}


}
