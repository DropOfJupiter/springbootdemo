package com.example.qiutt.demo.enums;

/**
 * @author qiutt
 * @description:取值上限倍数
 * @create 2019-10-17 11:04
 */
public enum MaxValueMultipleEnum {

	THREE_TIMES_BASE_VALUE("threeTimesBaseValue", "三倍标准值",3,"BASE_VALUE"),
	FOUR_TIMES_BASE_VALUE("fourTimesBaseValue", "四倍标准值",4,"BASE_VALUE"),
	UNLIMIT("unlimit", "不限制",null,"BASE_VALUE");

	private String code;
	private String label;
	private Integer times;
	private String filed;

	private MaxValueMultipleEnum(String code, String label, Integer times, String filed) {
		this.code = code;
		this.label = label;
		this.times=times;
		this.filed=filed;
	}

	public String getCode() {
		return this.code;
	}

	public String getLabel() {
		return this.label;
	}

	public Integer getTimes() {
		return times;
	}

	public String getFiled() {
		return filed;
	}


	public static MaxValueMultipleEnum mapperFromCode(String value) {
		for(MaxValueMultipleEnum maxValueMultipleEnum:MaxValueMultipleEnum.values()){
			if(maxValueMultipleEnum.getCode().equals(value)){
				return maxValueMultipleEnum;
			}
		}
		return null;
	}
}
