package src.main.java.com.example.qiutt.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiutt
 * @description:no description
 * @create 2018-11-14 17:25
 */
public class StreamLearning {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		System.out.println("sum is:"+nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
	}

}
