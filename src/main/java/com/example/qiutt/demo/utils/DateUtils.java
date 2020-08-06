/**
 * aiya
 * 2017年7月18日
 */
package com.example.qiutt.demo.utils;

import com.example.qiutt.demo.common.CalculateConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
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

	public static int getIntervalDays(Date date, Date otherDate) {
		int num = -1;
		final String pattern = "yyyy-MM-dd";
		String dateTmp = dateToString(date, pattern);
		String otherDateTmp = dateToString(otherDate, pattern);
		if (StringUtils.isNotBlank(dateTmp) && StringUtils.isNotBlank(otherDateTmp)) {
			long time = 0;
			try {
				time = Math
						.abs(stringToDate(dateTmp, pattern).getTime() - stringToDate(otherDateTmp, pattern).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}

	/**
	 * 日期转字符串
	 *
	 * @param date    日期
	 * @param pattern 日期格式
	 * @return {@link String}
	 */
	public static String dateToString(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 转化字符串日期
	 *
	 * @param dateString 字符串日期
	 * @param pattern    日期格式
	 * @return {@link Date}
	 */
	public static Date stringToDate(String dateString, String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateString);
	}

	/**
	 * 获取本月最早时间
	 *
	 * @return
	 */
	public static Date getThisMonthFirstTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}

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

	/**
	 * 获得一个月份的最后一天的23时59分59秒 例如： 传如的时间为 2017-07 ，得到时间为 2017-07-01:23:59:59
	 *
	 * @param date 日期 yyyy-MM
	 * @return {@link Date} yyyy-MM-dd 23:59:59
	 */
	public static Date getLastDayOfMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date lastDayOfMonth = calendar.getTime();
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

	/**
	 * 获取当前月天数
	 *
	 * @param date 日期
	 * @return {@link Integer} 天数
	 */
	public static Integer getCurrentMonthDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取两个时间间隔的月份
	 * <p>例如2017-07-03到2017-10-03 一共有三个月，分别是2017-07-03到2017-08-03；2017-08-03到2017-09-03；2017-09-03到2017-10-03；
	 * <p>例如2017-07-03到2017-10-13 一共有3.355=(总天数/30天)个月，分别是2017-07-03到2017-08-03；2017-08-03到2017-09-03；2017-09-03到2017-10-03；2017-10-03到2017-10-13
	 *
	 * @param beginDate 开始时间
	 * @param endDate   截止时间
	 * @return {@link BigDecimal}
	 */
	public static BigDecimal getIntervalMonth(Date beginDate, Date endDate) {
		Integer intervalDays = getIntervalDays(beginDate, endDate) + 1;
		Double intervalDaysDouble = Double.valueOf(intervalDays);
		Double double1 = Double.valueOf(30);
		Double temp = (Double) (intervalDaysDouble / double1);
		BigDecimal intervalMonths = BigDecimal.valueOf(temp).setScale(CalculateConstant.NORMAL_SCALE, CalculateConstant.NORMAL_ROUDING_MODE);
		return intervalMonths;
	}

	public static BigDecimal getIntervalMonth(DateTime start, DateTime end) {
		int months = Months.monthsBetween(start, end).getMonths()+1;
		return BigDecimal.valueOf(months);
	}


	/**
	 * 获取两个时间间隔几个自然月 <p>
	 * 例如2017-07-03到2017-10-03 一共有经过了四个月，分别是2017-07,2017-08,2017-09,2017-10
	 *
	 * @param beginDate 开始日期
	 * @param endDate   截止日期
	 * @return {@link Integer}
	 */
	public static Integer getIntervalNaturalMonth(Date beginDate, Date endDate) throws ParseException {
		final String pattern = "yyyy-MM-dd";
		final String formatBeginDate = dateToString(beginDate, pattern);
		final String formatEndDate = dateToString(endDate, pattern);
		beginDate = stringToDate(formatBeginDate, pattern);
		endDate = stringToDate(formatEndDate, pattern);
		Calendar calbegin = Calendar.getInstance();
		calbegin.setTime(beginDate);
		// 获得开始日期月份
		int monthBegin = calbegin.get(Calendar.MONTH) + 1;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		// 获得结束日期月份
		int monthEnd = calendar.get(Calendar.MONTH) + 1;
		int checkmonth = monthEnd - monthBegin + (calendar.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR)) * 12;
		checkmonth += 1;
		return Math.abs(checkmonth);
	}
	/**
	 * 获得一个月份的第一天的0时0分0秒 例如： 传如的时间为 2017-07 ，得到时间为 2017-07-01 ：00:00:00
	 *
	 * @param date 日期 yyyy-MM
	 * @return {@link Date} yyyy-MM-01 00:00:00
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date firstDayOfMonth = calendar.getTime();
		return firstDayOfMonth;
	}
}
