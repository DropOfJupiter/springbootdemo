package com.example.qiutt.demo.date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.qiutt.demo.common.CalculateConstant;
import com.example.qiutt.demo.enums.ChargeUnitEnum;
import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

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
		Date time = DateUtils.strToDate("2020-09-06 11:59:32.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date time1 = DateUtils.strToDate("2020-10-05 23:59:59.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);

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

	@Test
	public void getIntervals() throws ParseException {
		Date time = DateUtils.strToDate("2020-05-20 15:30:26.313", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		//Date time1 = DateUtils.strToDate("2020-05-23 00:00:00.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date time1 = DateUtils.strToDate("2020-05-22 23:59:59.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);

		log.info("相隔MONTH:{}",DateUtils.getIntervals(time,time1, ChargeUnitEnum.MONTH));
		log.info("相隔DAY:{}",DateUtils.getBillingIntervalDays(time,time1));
		log.info("相隔HOUR:{}",DateUtils.getIntervals(time,time1, ChargeUnitEnum.HOUR));
		log.info("相隔MINUTE:{}",DateUtils.getIntervals(time,time1, ChargeUnitEnum.MINUTE));
		log.info("相隔SECOND:{}",DateUtils.getIntervals(time,time1, ChargeUnitEnum.SECOND));
	}

	@Test
	public void longToDate() throws ParseException {
		String productConfigInfo="{\"unitPrice\":[\"{\\\"unitPrice\\\":100,\\\"itemName\\\":\\\"maxBandwidth\\\",\\\"unitName\\\":\\\"Gbps\\\"}\"],\"instanceChargeType\":\"POST_PAID\",\"origProdConfigJson\":{\"instanceChargeType\":\"POST_PAID\",\"origProdConfigJson\":{\"instanceChargeType\":\"POST_PAID\",\"multiItems\":[{\"elasticBandwidth\":250,\"bandwidthType\":\"BGP\",\"multiItemId\":1},{\"elasticBandwidth\":250,\"bandwidthType\":\"TELE\",\"multiItemId\":2},{\"elasticBandwidth\":250,\"bandwidthType\":\"CNC\",\"multiItemId\":3}],\"chargeMode\":\"\",\"publicIpUUIDs\":\"\",\"guranteeBandwidth\":100,\"autoRenew\":\"INFINITE\",\"ipCount\":3,\"region\":\"demo-1\",\"bandwidthTypes\":\"\",\"bandwidthSerial\":\"Anti\"},\"multiItems\":[{\"elasticBandwidth\":200,\"bandwidthType\":\"BGP\",\"multiItemId\":1},{\"elasticBandwidth\":200,\"bandwidthType\":\"TELE\",\"multiItemId\":2},{\"elasticBandwidth\":200,\"bandwidthType\":\"CNC\",\"multiItemId\":3}],\"chargeMode\":\"\",\"publicIpUUIDs\":\"\",\"guranteeBandwidth\":100,\"autoRenew\":\"INFINITE\",\"ipCount\":3,\"region\":\"demo-1\",\"bandwidthTypes\":\"\",\"bandwidthSerial\":\"Anti\"},\"multiItems\":[{\"elasticBandwidth\":150,\"bandwidthType\":\"BGP\",\"multiItemId\":1},{\"elasticBandwidth\":150,\"bandwidthType\":\"TELE\",\"multiItemId\":2},{\"elasticBandwidth\":150,\"bandwidthType\":\"CNC\",\"multiItemId\":3}],\"publicIpUUIDs\":\"\",\"guranteeBandwidth\":100,\"bandwidthTypes\":\"\",\"refundBeginTime\":1605066437000,\"chargeMode\":\"\",\"expireTimeBeforeRefund\":1607616000000,\"autoRenew\":\"INFINITE\",\"ipCount\":3,\"region\":\"demo-1\",\"bandwidthSerial\":\"Anti\"}";
		JSONObject jsonObject= JSON.parseObject(productConfigInfo);
		Long expireTimeBeforeRefund= (Long) jsonObject.get("expireTimeBeforeRefund");
		Date expireTimeBeforeRefundDate=new Date(expireTimeBeforeRefund);
		log.info("expireTimeBeforeRefund：{},expireTimeBeforeRefundDate:{}",expireTimeBeforeRefund,expireTimeBeforeRefundDate);
		JSONArray unitPricesArrays= (JSONArray) jsonObject.get("unitPrice");
		unitPricesArrays.size();
		String[] configUnitPriceArray = new String[2];
		for(int i=0;i<unitPricesArrays.size();i++){
			String unitPrice= (String) unitPricesArrays.get(i);
			JSONObject unitPriceJSON= JSON.parseObject(unitPrice);
			String unitName= (String) unitPriceJSON.get("unitName");
			if(Objects.nonNull(unitPriceJSON.get("unitPrice"))){
				log.info("unitPriceJSON.get(\"unitPrice\") class:{}",unitPriceJSON.get("unitPrice").getClass());
			}
			Object price= Objects.nonNull(unitPriceJSON.get("price"))? unitPriceJSON.get("price"):
					unitPriceJSON.get("unitPrice");
			if(StringUtils.isEmpty(configUnitPriceArray[1])){
				configUnitPriceArray[0]=price.toString();
				configUnitPriceArray[1]=unitName;
			}else if(configUnitPriceArray[1].equals(unitName)){
				//多个计费项时，同个单位的累加
				configUnitPriceArray[0]=new BigDecimal(configUnitPriceArray[0]).add(new BigDecimal(price.toString())).toString();
			}else{
				log.error("存在多个计费单价不同的计费项");
			}
		}
		log.info("单价：{}，单位：{}",configUnitPriceArray[0],configUnitPriceArray[1]);
	}

	@Test
	public void test1222(){
		List<BigDecimal> bigDecimalList=new ArrayList<BigDecimal>();
		bigDecimalList.add(new BigDecimal("3.75"));
		bigDecimalList.add(new BigDecimal("7.50"));
		bigDecimalList.add(new BigDecimal("30.00"));
		BigDecimal sum=bigDecimalList.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totl=new BigDecimal("24.97");
		List<BigDecimal> results=new ArrayList<BigDecimal>();
		log.info("sum:{}",sum);
		for(int i=0;i<bigDecimalList.size();i++){
			BigDecimal result1=
					totl.multiply(
							bigDecimalList.get(i))
									.divide(sum, CalculateConstant.UNSUBSCRIBE_PRODUCT_SCALE, CalculateConstant.DEFAULT_PRICE_DISPLAY_ROUDING_MODE);
			log.info("result1:{}",result1);
		}
	}
}
