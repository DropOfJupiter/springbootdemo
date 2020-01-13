package com.example.qiutt.demo.collectors;

import cn.hutool.core.util.RandomUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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

	@Test
	public void changeList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			list.add(i+1);
		}
		list.forEach(p -> {
			log.info("初始化用户年龄{}", p);
		});
		change(list);
		list.forEach(p -> {
			log.info("change用户年龄{}", p);
		});
	}

	private void change(ArrayList<Integer> list) {
		for(int i=0;i<5;i++){
			list.set(i,100);
		}
	}

	@Test
	public void removeNull(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<5;i++){
			list.add(i%2==0?i+1:null);
		}
		log.info("list初始化大小：{}", list.size());
		list.removeAll(Collections.singleton(null));
		log.info("list删除空记录后大小：{}", list.size());

	}
}
