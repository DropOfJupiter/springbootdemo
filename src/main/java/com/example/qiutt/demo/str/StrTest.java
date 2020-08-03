package com.example.qiutt.demo.str;

import com.example.qiutt.demo.utils.DateUtils;
import com.example.qiutt.demo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-09-29 15:26
 */
@Slf4j
public class StrTest {

	@Test
	public void upperCaseByIndex() {
		String str = "23453";
		log.info(StringUtil.upperCaseByIndex(str));
	}

	@Test
	public void encoding() throws UnsupportedEncodingException {

		System.out.println("中文");

		System.out.println("1、中文".getBytes());

		System.out.println("2、中文".getBytes("GB2312"));

		System.out.println("3、中文".getBytes("ISO8859_1"));

		System.out.println(new String("4、中文".getBytes()));

		System.out.println(new String("5、中文".getBytes(), "GB2312"));

		System.out.println(new String("6、中文".getBytes(), "ISO8859_1"));

		System.out.println(new String("7、中文".getBytes("GB2312")));

		System.out.println(new String("8、中文".getBytes("GB2312"), "GB2312"));

		System.out.println(new String("9、中文".getBytes("GB2312"), "ISO8859_1"));

		System.out.println(new String("10、中文".getBytes("ISO8859_1")));

		System.out.println(new String("11、中文".getBytes("ISO8859_1"), "GB2312"));

		System.out.println(new String("12、中文".getBytes("ISO8859_1"), "ISO8859_1"));

		System.out.println(new String("13、锟斤拷锟�".getBytes("UTF-8"), "GBK"));
		System.out.println(new String("14、锟斤拷锟�".getBytes(), "ISO8859_1"));
		System.out.println(new String("15、锟斤拷锟�".getBytes(), "GBK"));
		System.out.println(new String("16、锟斤拷锟�".getBytes(), "UTF-8"));

		System.out.println("Charset.defaultCharset():" + Charset.defaultCharset());

	}

	@Test
	public void getEncoding() throws UnsupportedEncodingException {
		String str = "你好";
		String[] encodes = new String[]{"GB2312","ISO-8859-1","UTF-8","GBK"};
		for(String encode:encodes){
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
				log.info(encode);
			}
		}
	}

	@Test
	public void test() throws ParseException {
		Date consumptionBeginTime = DateUtils.strToDate("2020-07-01 00:00:00.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		if (DateUtils.getThisMonthFirstTime().after(consumptionBeginTime)) {
			log.info("111");
		}
		Map<String,Integer> map=new HashMap<>();
		map.put("1",1);
		if(map.containsKey("1")){
			Integer integer=map.get("1");
			integer=11;
			map.put("1",integer);
		}
		log.info("{}",map.get("1"));
	}

	@Test
	public void getIntervalDays() throws ParseException {
		Date consumptionBeginTime = DateUtils.strToDate("2020-06-11 23:59:59.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		Date consumptionEndTime = DateUtils.strToDate("2020-05-31 23:59:59.000", DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS);
		consumptionEndTime=new Date();
		log.info("getIntervalDays:{}",DateUtils.getIntervalDays(consumptionBeginTime,consumptionEndTime)+1);
	}
}
