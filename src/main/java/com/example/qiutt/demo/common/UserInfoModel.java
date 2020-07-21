package com.example.qiutt.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-04-02 19:30
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInfoModel {
	private String name;
	private Integer age;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Date birthday;
	private String sex;
	private Double salary;
//
	private String fullName;
	private Job job;

	private List<String> hobbys;


	@Data
	public class Job{
		private String name;
	}

//	@Override
//	public String toString(){
//		StringBuilder stringBuilder=new StringBuilder();
//		if(Objects.nonNull(fullName)){
//			stringBuilder.append(fullName);
//		}
//		return stringBuilder.toString();
//	}

}
