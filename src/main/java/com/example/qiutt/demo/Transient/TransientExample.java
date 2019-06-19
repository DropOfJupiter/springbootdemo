package com.example.qiutt.demo.Transient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-06-19 14:28
 */
public class TransientExample {
	public static void main(String args[]) throws Exception {
		Rectangle rectangle = new Rectangle(3,4);
		System.out.println("1.原始对象\n"+rectangle);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("rectangle"));
		// 往流写入对象
		o.writeObject(rectangle);
		o.close();

		// 从流读取对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("rectangle"));
		Rectangle rectangle1 = (Rectangle)in.readObject();
		System.out.println("2.反序列化后的对象\n"+rectangle1);
		rectangle1.setArea();
		System.out.println("3.恢复成原始对象\n"+rectangle1);
		in.close();
	}
}
