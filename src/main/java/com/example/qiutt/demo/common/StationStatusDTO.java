package com.example.qiutt.demo.common;

import com.example.qiutt.demo.utils.DateUtils;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author qiutt
 * @description:工位状态分布
 * @create 2019-10-23 14:32
 */
@Data
public class StationStatusDTO {

	//工位ID
	private Long stationId;

	//状态ID
	private Long statusId;

	//状态名称
	private String statusName;

	//状态发生时间
	private Date startTime;

	//状态值（1结束，0 开始）
	//TODO 感觉应该是0结束，1开始
	private Integer value;

	@Override
	public String toString(){
		return this.getStatusId()+"-"+ DateUtils.dateToString(this.getStartTime(), DateUtils.DateStyle.YYYY_MM_DD_HH_MM_SS)+"-"+this.getValue();
	}
}
