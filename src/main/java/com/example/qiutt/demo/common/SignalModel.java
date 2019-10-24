package com.example.qiutt.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 19:30
 */
@Data
public class SignalModel {
	private Integer id;
	private String name;
	private Integer value;
	private Date startTime;

}
