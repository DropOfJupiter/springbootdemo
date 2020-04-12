package com.example.qiutt.demo.kafka;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-20 14:12
 */
@Data
@Builder
public class OrderInfo {
	private String userName;
	private String orderId;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	private Date createTime;

	private String createTimeStr;
	private Long createTimeStamp;
	private Integer amount;
}
