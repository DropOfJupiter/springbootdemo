package com.example.qiutt.demo.hashcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiutt
 * @description:重写了equals但没有重写hashCode
 *  https://mp.weixin.qq.com/s/nQ-g2rdZ6XU-OV9gg6228A
 * @create 2020-08-28 10:02
 */
@Slf4j
public class HashCode {

	@Test
	public void test() {
		String hello = "hello";

		Map<String, String> map1 = new HashMap<>();
		String s1 = new String("key");
		String s2 = new String("key");
		map1.put(s1, hello);
		System.out.println("String 有重写了equals和hashCode方法");

		System.out.println("s1.equals(s2):" + s1.equals(s2));
		System.out.println("map1.get(s1):" + map1.get(s1));
		System.out.println("map1.get(s2):" + map1.get(s2));

		System.out.println("-----------------------");

		Map<Key, String> map2 = new HashMap<>();
		Key k1 = new Key("A");
		Key k2 = new Key("A");
		map2.put(k1, hello);
		System.out.println("Key只重写了equals而没有重写hashCode");
		System.out.println("k1.equals(k2):" + k1.equals(k2));
		System.out.println("map2.get(k1):" + map2.get(k1));
		System.out.println("map2.get(k2):" + map2.get(k2));
	}

	class Key {

		private String k;

		public Key(String key) {
			this.k = key;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Key) {
				Key key = (Key) obj;
				return k.equals(key.k);
			}
			return false;
		}
	}
}
