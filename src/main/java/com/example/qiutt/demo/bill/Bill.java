package com.example.qiutt.demo.bill;

import com.example.qiutt.demo.common.CalculateConstant;
import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-05 16:03
 */
@Slf4j
public class Bill {

	@Test
	public void test1() throws ParseException {
		String beginStr="2019-05-09 14:08:28.000";
		String endStr="2020-05-08 23:59:59.000";
		BigDecimal payPrice  = BigDecimal.valueOf(481248d);

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime start = formatter.parseDateTime(beginStr);
		DateTime end = formatter.parseDateTime(endStr);
		int months = Months.monthsBetween(start, end).getMonths()+1;
		BigDecimal monthUnitPayPrice1 = processAmount(payPrice, BigDecimal.valueOf(months));
		log.info("一共有{}个月，月摊分：{}",months,monthUnitPayPrice1);

		final Date billBeginTime = DateUtils.stringToDate(beginStr,"yyyy-MM-dd HH:mm:ss");
		final Date billEndTime = DateUtils.stringToDate(endStr,"yyyy-MM-dd HH:mm:ss");
		Integer intervalNaturalMonth = DateUtils.getIntervalNaturalMonth(billBeginTime, billEndTime);
		BigDecimal monthUnitPayPrice2 = processAmount(payPrice, BigDecimal.valueOf(intervalNaturalMonth));
		log.info("一共有{}个自然月，月摊分：{}",intervalNaturalMonth,monthUnitPayPrice2);

	}

	@Test
	public void test() throws ParseException {
		String beginStr="2019-01-05 19:17:07.000";
		String endStr="2020-01-04 23:59:59.000";
		final Date billBeginTime = DateUtils.stringToDate(beginStr,"yyyy-MM-dd HH:mm:ss");
		final Date billEndTime = DateUtils.stringToDate(endStr,"yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime start = formatter.parseDateTime(beginStr);
		DateTime end = formatter.parseDateTime(endStr);
		// 计算账单开始时间到结束时间月份间隔（一共有几个月）
		// 例如2017-07-03到2017-10-03
		// 一共有三个月，分别是2017-07-03到2017-08-03；2017-08-03到2017-09-03；2017-09-03到2017-10-03；
//		BigDecimal intervalMonth = DateUtils.getIntervalMonth(billBeginTime, billEndTime).compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.valueOf(1L)
//				: DateUtils.getIntervalMonth(billBeginTime, billEndTime);
		BigDecimal intervalMonth1 = DateUtils.getIntervalMonth(start, end).compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.valueOf(1L)
				: DateUtils.getIntervalMonth(start, end);
		// 计算账单开始时间到结束时间月份跨越几个自然月（一共经历了几个月)
		// 例如2017-07-03到2017-10-03 一共有经过了四个月，分别是2017-07,2017-08,2017-09,2017-10
		Integer intervalNaturalMonth = DateUtils.getIntervalNaturalMonth(billBeginTime, billEndTime);
		// 实际每月摊分的成本并不一定等于订单展示的月单价！！！(比如说买一年打8.5折，买十个月送两个月)
		// 因为退订产生的流水账单金额为负数，因此这里取绝对值，保证用于计算的金额为正数
		BigDecimal totalPrice  = BigDecimal.valueOf(53628d);

		BigDecimal payPrice  = BigDecimal.valueOf( 42902.40d);
//		BigDecimal monthUnitOriPrice = processAmount(totalPrice, BigDecimal.valueOf(12));
//
//		BigDecimal monthUnitPayPrice = processAmount(payPrice, BigDecimal.valueOf(12));
		BigDecimal monthUnitOriPrice = processAmount(totalPrice, intervalMonth1);

		BigDecimal monthUnitPayPrice = processAmount(payPrice, intervalMonth1);
		log.info("一共有{}个月，经历了{}个自然月，原价摊分月单价：{}，实付摊分月单价：{}",intervalMonth1,intervalNaturalMonth,monthUnitOriPrice,monthUnitPayPrice);

		BigDecimal sumOriConsumptionPrice = BigDecimal.ZERO;
		BigDecimal sumConsumptionPrice = BigDecimal.ZERO;
		BigDecimal oriConsumptionPrice = BigDecimal.ZERO;
		BigDecimal consumptionPrice = BigDecimal.ZERO;

		// 循环需要预生产月度消耗的月份
		for (int i = 0; i < intervalNaturalMonth; i++) {
			Date currentMonth = DateUtils.getRecentMonth(billBeginTime, i, "yyyy-MM");
			Date consumptionBeginTime = null;
			Date consumptionEndTime = null;
			if (i == 0) {
				consumptionBeginTime = billBeginTime;
			} else {
				// 消耗开始时间取大的那个
				consumptionBeginTime = DateUtils.getFirstDayOfMonth(currentMonth).before(billBeginTime) ? billBeginTime
						: DateUtils.getFirstDayOfMonth(currentMonth);
			}
			if (i == intervalNaturalMonth - 1) {
				consumptionEndTime = billEndTime;
			} else {
				// 消耗结束时间取小的那个
				consumptionEndTime = DateUtils.getLastDayOfMonth(currentMonth).after(billEndTime) ? billEndTime
						: DateUtils.getLastDayOfMonth(currentMonth);
			}
			// 当月实际服务时长
			Integer serviceTimes = DateUtils.getIntervalDays(consumptionBeginTime, consumptionEndTime) + 1;
			// 当月总天数
			Integer daysOfMonth = DateUtils.getCurrentMonthDays(currentMonth);
			if (i == intervalNaturalMonth - 1) {
				oriConsumptionPrice = totalPrice.subtract(sumOriConsumptionPrice);
				consumptionPrice = payPrice.subtract(sumConsumptionPrice);
			} else {
				oriConsumptionPrice = processAmount(monthUnitOriPrice, daysOfMonth, serviceTimes);
				consumptionPrice = processAmount(monthUnitPayPrice, daysOfMonth, serviceTimes);
			}
			sumOriConsumptionPrice = sumOriConsumptionPrice.add(oriConsumptionPrice);

			sumConsumptionPrice = sumConsumptionPrice.add(consumptionPrice);
			System.out.println(oriConsumptionPrice);
			//log.info("第{}个月{} ，原价摊分  {}，折后价摊分  {}",i,DateUtils.dateToString(currentMonth,"yyyy-MM"),oriConsumptionPrice,consumptionPrice);
		}

	}

	/**
	 * 摊分到每月的实际金额
	 */
	private BigDecimal processAmount(BigDecimal amount, Integer daysOfMonth, Integer serviceTimes) {
		return processAmount(amount.divide(BigDecimal.valueOf(daysOfMonth), CalculateConstant.NORMAL_SCALE, CalculateConstant.NORMAL_ROUDING_MODE)
				.multiply(BigDecimal.valueOf(serviceTimes)));
	}
	/**
	 * 额度取精度，精确到六位小数并且四舍五入
	 */
	private BigDecimal processAmount(BigDecimal amount) {
		amount = amount.setScale(CalculateConstant.NORMAL_SCALE, CalculateConstant.NORMAL_ROUDING_MODE);
		return amount;
	}

	/**
	 * 判断金额是否需要取反
	 */
	private BigDecimal processAmount(BigDecimal amount, Boolean isInVaild) {
		amount = isInVaild ? amount.negate() : amount;
		return amount;
	}

	/**
	 * 平均每月需要支付多少
	 */
	private BigDecimal processAmount(BigDecimal amount, BigDecimal intervalMonth) {
		return amount.divide(intervalMonth, CalculateConstant.NORMAL_SCALE, CalculateConstant.NORMAL_ROUDING_MODE);
	}
}
