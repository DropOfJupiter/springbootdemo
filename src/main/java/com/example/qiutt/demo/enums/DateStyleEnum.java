package com.example.qiutt.demo.enums;

/**
 * Company: kpy Author: qiutt 2017年9月28日
 */
public enum DateStyleEnum {
	YYYY_MM("yyyy-MM", "年-月"), 
	YYYY_MM_DD("yyyy-MM-dd", "年-月-日"),
	YYYY_MM_DD_HH("yyyy-MM-dd HH","年-月-日 时"),
	YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm","年-月-日 时:分"), 
	YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", "年-月-日 时:分:秒"), 
	YYYY_MM_EN("yyyy/MM", "年/月"),
	YYYY_MM_DD_EN("yyyy/MM/dd", "年/月/日"), 
	YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", "年/月/日 时:分:秒"), 
	YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", "年/月/日 时:分"), 
	YYYY_MM_CN("yyyy年MM月", "yyyy年MM月"), 
	YYYY_MM_DD_CN("yyyy年MM月dd日","yyyy年MM月dd日"), 
	YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", "yyyy年MM月dd日 HH:mm"), 
	YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss","yyyy年MM月dd日 HH:mm:ss"),
	YYYY_MM_DD_HH_MM_SS_CN2("yyyy年MM月dd日 HH时mm分ss秒","yyyy年MM月dd日 HH时mm分ss秒"),
	YYYY_MM_DD_ISO8601("yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'"),
	YYYYMMDDHHMM("yyyyMMddHHmm", "yyyyMMddHHmm"), 
	YYYYMMDDHHMMSS("yyyyMMddHHmmss","yyyyMMddHHmmss"),
	YYYY_MM_DD_HHMM("yyyy-MM-dd-HHmm", "yyyy-MM-dd-HHmm"),
	HH_MM("HH:mm", "HH:mm"), 
	HH_MM_SS("HH:mm:ss", "HH:mm:ss"), 
	MM_DD("MM-dd", "MM-dd"), 
	MM_DD_HH_MM("MM-dd HH:mm","MM-dd HH:mm"), 
	MM_DD_HH_MM_SS("MM-dd HH:mm:ss", "MM-dd HH:mm:ss"), 
	MM_DD_EN("MM/dd", "MM/dd"), 
	MM_DD_HH_MM_EN("MM/dd HH:mm","MM/dd HH:mm"), 
	MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", "MM/dd HH:mm:ss"), 
	MM_DD_CN("MM月dd日","MM月dd日"),
	MM_DD_HH_MM_CN("MM月dd日 HH:mm", "MM月dd日 HH:mm"), 
	MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", "MM月dd日 HH:mm:ss");
	
	private String code;

	private String label;

	private DateStyleEnum(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return this.code;
	}

	public String getLabel() {
		return this.label;
	}
}
