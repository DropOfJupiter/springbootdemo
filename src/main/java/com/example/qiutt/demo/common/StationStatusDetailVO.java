package com.example.qiutt.demo.common;

import lombok.Data;

import java.util.Date;

/**
 * @author qiutt
 * @description:工位状态分布-明细
 * @create 2019-10-23 14:47
 */
@Data
public class StationStatusDetailVO {
	//工位ID
	private Long stationId;

	//状态ID
	private Long statusId;

	//状态名称
	private String statusName;

	//状态发生时间
	private Date startTime;

	//状态结束时间
	private Date endTime;

	//状态发生时长（分钟为单位）
	private Long duration;
}
