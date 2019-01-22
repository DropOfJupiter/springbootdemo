package com.example.qiutt.demo.lambda;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-01-20 17:13
 */
public class Calculator {
	private Integer a;

	private Integer b;

	private BigDecimal r;

	public Calculator(Integer a, Integer b, BiFunction<Integer,Integer,BigDecimal> biFunction ) {
		r=biFunction.apply(a,b);
	}
}
