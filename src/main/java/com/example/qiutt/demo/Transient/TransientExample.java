package com.example.qiutt.demo.Transient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author qiutt
 * @description:
 * Java中transient关键字的作用，简单地说，就是让某些被修饰的成员属性变量不被序列化，这一看好像很好理解，就是不被序列化，那么什么情况下，一个对象的某些字段不需要被序列化呢？如果有如下情况，可以考虑使用关键字transient修饰：
	1、类中的字段值可以根据其它字段推导出来，如一个长方形类有三个属性：长度、宽度、面积（示例而已，一般不会这样设计），那么在序列化的时候，面积这个属性就没必要被序列化了；
	2、其它，看具体业务需求吧，哪些字段不想被序列化；
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
