package com.example.qiutt.demo.collectors;

import cn.hutool.core.util.RandomUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-06-25 19:39
 */
@Slf4j
public class ListLearning {
	@Test
	public void addTest(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");


	}

	@Test
	public void subList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			list.add(i+1);
		}
		list.forEach(p -> {
			log.info("用户年龄{}", p);
		});
		List<Integer> subList=list.subList(2,list.size());
		subList.forEach(p -> {
			log.info("裁剪后用户年龄{}", p);
		});
	}
}
