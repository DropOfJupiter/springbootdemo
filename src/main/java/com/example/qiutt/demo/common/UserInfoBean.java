package com.example.qiutt.demo.common;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-08-04 17:52
 */
public class UserInfoBean {
	private Integer userId;

	private Integer domainId;

	private Set<Integer> domainIds;

	private String userName;

	private String fullName;

	private String phone;

	private String email;

	private Byte userStatus;

	private Byte roleId;

	private Byte accountType;

	private Integer tenantId;

	private Integer managerId;

	private Integer levelId;

	private String corpName;

	private String qq;

	private Byte regwap;

	private Date regTime;
	/**
	 * 折扣
	 */
	private Double balance;

	/** 身份证号码 */
	private String carNo;

	/** 是否实名 */
	private String realNameAuthStatus;

	/**
	 * 对应权限模块uaa中的userId
	 */
	private Long uaaUserId;

	/**
	 * 用户power:Ordinary,Management
	 */
	private String userPower;
}
