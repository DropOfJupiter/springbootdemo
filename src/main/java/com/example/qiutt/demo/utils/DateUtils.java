/**
 * aiya
 * 2017年7月18日
 */
package com.example.qiutt.demo.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author aiya
 *
 */
public class DateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	private ZoneId zone = ZoneId.systemDefault();



	/**
	 * 日期格式化风格枚举类
	 *
	 * @author Administrator
	 */
	public enum DateStyle {
		HH_MM("HH:mm"),
		HH_MM_SS("HH:mm:ss"),
		YYYY_MM("yyyy-MM"),
		YYYY_MM_DD("yyyy-MM-dd"),
		YYYY_MM_DD_HH("yyyy-MM-dd HH"),
		YYYY_MM_DD_HH_0_0("yyyy-MM-dd HH:00:00"),
		YYYY_MM_DD_HH_59_59("yyyy-MM-dd HH:59:59"),
		YYYY_MM_DD_23_59_59("yyyy-MM-dd 23:59:59"),
		YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
		YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
		YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS");

		private String value;


		DateStyle(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	/**
	 * 将日期的分钟与秒置为0
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinMinuteAndSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 返回传入日期的最小时间，例如2015-09-30 00:00:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getMinTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 返回传入日期的最大时间，例如2015-09-30 23:59:59
	 *
	 * @param date
	 * @return
	 */
	public static Date getMaxTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 *
	 * @param date     日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		int num = 0;
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			num = calendar.get(dateType);
		}
		return num;
	}

	/**
	 * 获取日期的天数。失败返回0。
	 *
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}

	/**
	 * 获取小时
	 *
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	public static boolean isSameHour(Date date1, Date date2) {
		return 0 == (DateUtils.getHour(date1)-DateUtils.getHour(date2));
	}

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	public static String dateToString(Date date, DateStyle pattern) {
		return DateFormatUtils.format(date, pattern.getValue());
	}

	public static Date strToDate(String dateStr, DateStyle pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue());
		return sdf.parse(dateStr);
	}

	/**
	 * 小时加减
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHour(Date date, int amount)  {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY,  amount);
		return calendar.getTime();
	}

	/**
	 * 日期加减
	 *
	 * @param date 原日期
	 * @param amount 加减天数
	 * @return
	 */
	public static Date addDate(Date date, int amount)  {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,  amount);
		return calendar.getTime();
	}

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

	public static Integer getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean isBetweenTimeRange(Date date, Date startDate, Date endDate) {
		return date.compareTo(startDate)>=0 && date.compareTo(endDate)<=0;
	}

	/**
	 * 获取下一个小时
	 *  如当前2019-11-12 14:18:31 返回 2019-11-12 15:00:00
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextHour(Date date) {
		date = DateUtils.addHour(date, 1);
		date = getMinMinuteAndSecond(date);
		return date;
	}

	/**
	 * 获得指定时间的前n月，或者后n月
	 *
	 * @param date
	 * @param months(preDays>0获取指定时间的后preDays天
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
			LOGGER.info(String.valueOf(e));
		}
		return date;
	}

	public static LocalDate date2LocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate;
	}

	public static LocalDateTime date2LocalDateTime(Date date){
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	public static Date localDateTime2Date(LocalDateTime localDateTime){
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return  date;
	}
}
