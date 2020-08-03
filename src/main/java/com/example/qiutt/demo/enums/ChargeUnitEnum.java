package com.example.qiutt.demo.enums;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-07-28 15:09
 */
public enum ChargeUnitEnum {
	MINUTE("Minute", "分"),
	HOUR("Hour", "时"),
	DAY("Day", "日"),
	MONTH("Month", "月"),
	GB("GB", "GB"),
	MB("MB", "MB"),
	GBPS("Gbps", "Gbps"),
	MBPS("Mbps", "Mbps"),
	TIME("Time", "Time"),
	PERIOD_MONTH("PeriodMonth", "周期月"),
	USE_DAYS("UseDays", "使用天数"),
	USE_YEARS("UseYears", "年数"),
	PERSON_NUM("PersonNum", "人数"),
	PER("Per", "个"),
	CAB("Cab", "柜"),
	PLAT("Plat", "台");

	private String code;
	private String label;

	private ChargeUnitEnum(String code, String label) {
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
