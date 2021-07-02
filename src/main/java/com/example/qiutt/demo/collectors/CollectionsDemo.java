package com.example.qiutt.demo.collectors;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-09-07 17:10
 */
@Slf4j
public class CollectionsDemo {

	@Test
	public void test(){
		ArrayList<String> list = new ArrayList<String>();

		log.info("{}","2020-01".compareTo("2019-01"));
		log.info("{}","2020-01".compareTo("2020-01"));
		log.info("{}","2020-01".compareTo("2023-01"));

	}
}
