package com.example.qiutt.demo.str;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author qiutt
 * @description: new String()创建几个对象？有你不知道的
 * https://mp.weixin.qq.com/s/rr7oRPp0ajZj3Y8qTnKUPw
 * @create 2020-08-26 14:42
 */
@Slf4j
public class StringObject {

	/**
	 * 采用字面值的方式创建一个字符串时（仅与常量池有关，与堆无关）
	 * 如果常量池中有对象，就返回该对象引用地址；
	 * 如果常量池中没有对象，就在常量池中创建对象
	 */
	@Test
	public void test(){
		String s1 = "abc";//因为常量池中没有，所以会在常量池中创建"xyz"对象
		String s2 = "abc";//因为常量池中没有，所以会在常量池中创建"xyz"对象，直接返回常量池中已存在的对象
		System.out.println(s1==s2);//因此s1和s2都是指向常量池里的同一个"xyz"对象，所以结果是true
	}


	/**
	 * 采用new的方式创建一个字符串时（无论常量池是否有，都会在堆上创建对象）
	 */
	@Test
	public void test1(){
		String s3 = new String("xyz");//因为常量池中没有，所以会在常量池中创建"xyz"对象，然后因为有new，所以在堆上创建对象，s3指向的是堆上创建的"xyz"
		String s4 = new String("xyz");//因为常量池中没有，所以不会在常量池中创建"xyz"对象，然后因为有new，所以在堆上创建对象，s4指向的是堆上创建的"xyz"
		System.out.println(s3==s4);//因此s3和s4分别指向堆上的两个不同"xyz"对象，所以结果是false
	}

	@Test
	public void test2(){
		String s3 = "xyz";//s3指向常量池中的"xyz"
		String s4 = new String("xyz");//因为常量池中已经有xyz了，所以不会在常量池中创建，但是都会在堆上创建对象，s4指向堆上创建的"xyz"
		System.out.println(s3==s4);//所以是false


	}


	@Test
	public void test3(){
		//new String("he"):堆上中创建有"he"对象，常量池中有这对象的引用；new String("llo"):堆上中分别创建有"llo"对象，常量池中有这对象的引用；s1:堆上创建的对象"hello"
		String s1=new String("he")+new String("llo");
		//new String("h"):堆上中分别创建有"h"对象，常量池中有这对象的引用；new String("ello"):堆上中分别创建有"ello"对象，常量池中有这对象的引用；s2:堆上创建的新对象"hello"
		String s2=new String("h")+new String("ello");
		//s1.intern()：在常量池中没有"hello"，所以在常量池中创建一个引用，指向s1对应的堆对象
		String s3=s1.intern();
		//s2.intern()：在常量池中有"hello"，直接返回这个常量池的引用，也就是指向s1对应的堆对象
		String s4=s2.intern();
		System.out.println(s1==s2);//false
		System.out.println(s1==s3);//true
		System.out.println(s1==s4);//true
		System.out.println(s2==s4);//false
	}
}
