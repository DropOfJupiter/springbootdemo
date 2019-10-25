package com.example.qiutt.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qiutt
 * @description:工位状态分布-汇总
 * @create 2019-10-23 14:47
 */
@AllArgsConstructor
@Data
public class StationStatusSummaryVO {

	//状态ID
	private Long statusId;

	//状态名称
	private String statusName;

	//状态累计发生时长（分钟为单位）
	private Long duration;
}
