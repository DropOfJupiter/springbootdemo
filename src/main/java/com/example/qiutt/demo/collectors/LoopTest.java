package com.example.qiutt.demo.collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qiutt
 * @description:不同集合循环效率比较
 * 实现RandomAccess接口的List可以通过for循环来遍历数据比使用iterator遍历数据更高效，
 * 未实现RandomAccess接口的List可以通过iterator遍历数据比使用for循环来遍历数据更高效。
 * @create 2020-08-28 10:55
 */
@Slf4j
public class LoopTest {

	@Test
	public void test(){
		long arrayListIndexedTime = arrayListIndexed();
		long arrayListIteratorTime = arrayListIterator();
		long linkedListIndexedTime = linkedListIndexed();
		long linkedListIteratorTime = linkedListIterator();
		System.out.println("测试ArrayList(实现了RandomAccess)通过for遍历所消耗时间：" + arrayListIndexedTime);
		System.out.println("测试ArrayList(实现了RandomAccess)通过iterator遍历所消耗时间：" + arrayListIteratorTime);
		System.out.println("测试LinkedList(没有实现了RandomAccess)通过for遍历所消耗时间：" + linkedListIndexedTime);
		System.out.println("测试LinkedList(没有实现了RandomAccess)通过iterator遍历所消耗时间：" + linkedListIteratorTime);
	}

	//测试ArrayList通过for遍历所消耗时间
	public static long arrayListIndexed() {
		List<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}
		//记录开始时间
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i);
		}
		//记录结束时间
		long endTime = System.currentTimeMillis();
		//遍历消耗时间
		long resultTime = endTime - startTime;
		return resultTime;
	}

	//测试ArrayList通过iterator遍历所消耗时间
	public static long arrayListIterator() {
		List<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}
		//记录开始时间
		long startTime = System.currentTimeMillis();
		Iterator<Integer> iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		//记录结束时间
		long endTime = System.currentTimeMillis();
		//遍历消耗时间
		long resultTime = endTime - startTime;
		return resultTime;
	}

	//测试LinkedList通过for遍历所消耗时间
	public static long linkedListIndexed() {
		List<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		//记录开始时间
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < linkedList.size(); i++) {
			linkedList.get(i);
		}
		//记录结束时间
		long endTime = System.currentTimeMillis();
		//遍历消耗时间
		long resultTime = endTime - startTime;
		return resultTime;
	}

	//测试LinkedList通过iterator遍历所消耗时间
	public static long linkedListIterator() {
		List<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}
		//记录开始时间
		long startTime = System.currentTimeMillis();
		Iterator<Integer> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		//记录结束时间
		long endTime = System.currentTimeMillis();
		//遍历消耗时间
		long resultTime = endTime - startTime;
		return resultTime;
	}
}
