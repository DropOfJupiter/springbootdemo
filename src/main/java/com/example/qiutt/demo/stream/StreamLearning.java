package com.example.qiutt.demo.stream;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2018-11-14 17:25
 */
@Slf4j
public class StreamLearning {

	@Test
	public void streamFilter() {
		List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		log.info("sum is:"+nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
	}

	@Test
	public void streamGroupBy(){
		PayTypeDetail[] payTypeDetails=new PayTypeDetail[4];
		payTypeDetails[0]=new PayTypeDetail(ConsumptPayType.BALANCE_CONSUMPT, BigDecimal.valueOf(1));
		payTypeDetails[1]=new PayTypeDetail(ConsumptPayType.ALIPAY_CONSUMPT, BigDecimal.valueOf(11));
		payTypeDetails[2]=new PayTypeDetail(ConsumptPayType.ALIPAY_CONSUMPT, BigDecimal.valueOf(22));
		payTypeDetails[3]=new PayTypeDetail(ConsumptPayType.BALANCE_CONSUMPT, BigDecimal.valueOf(10));

		List<PayTypeDetail> payTypeDetailList=Arrays.asList(payTypeDetails);
		log.info("数据初始化（分组前）:"+ JSONUtil.toJsonStr(payTypeDetailList));
		List<PayTypeDetail> result=new ArrayList<PayTypeDetail>();
		//先分组
		Map<ConsumptPayType,List<PayTypeDetail>> groupByResultMap=payTypeDetailList.stream().collect(Collectors.groupingBy(PayTypeDetail::getConsumptPayType));
		log.info("分组后:"+ JSONUtil.toJsonStr(groupByResultMap));
		//分组后累加，写法1
		groupByResultMap.forEach(new BiConsumer<ConsumptPayType,List<PayTypeDetail>>(){
			@Override
			public void accept(ConsumptPayType consumptPayType, List<PayTypeDetail> list) {
				BigDecimal totalAmountByType=list.stream().map(PayTypeDetail::getPayAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
				result.add(new PayTypeDetail(consumptPayType,totalAmountByType));
			}
		});
		log.info("分组后累加:"+ JSONUtil.toJsonStr(result));
		//分组后累加，写法2
//		groupByResultMap.forEach((consumptPayType,list)->{
//				BigDecimal totalAmountByType=list.stream().map(PayTypeDetail::getPayAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
//				result.add(new PayTypeDetail(consumptPayType,totalAmountByType));
//		});
//		log.info("分组后累加:"+ JSONUtil.toJsonStr(result));

	}

	@Test
	public void streamFilterWithUserInfoModel() {
		List<UserInfoModel> userInfoModels=new ArrayList<UserInfoModel>();
		for(int i=0;i<10;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10,35));
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});
		List<UserInfoModel> userInfoModelsAfterFilter=userInfoModels.stream().filter(u->{
			return u.getAge()>20;
		}).collect(Collectors.toList());

		userInfoModelsAfterFilter.forEach(p -> {
			log.info("过滤后用户大于20年龄，年龄{}", p.getAge());
		});
	}

	@Test
	public void streamCountWithUserInfoModel() {
		List<UserInfoModel> userInfoModels=new ArrayList<UserInfoModel>();
		for(int i=0;i<10;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10,35));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});

		log.info("男性的个数{}", userInfoModels.stream().filter(u->{return u.getAge()>20;}).count());

	}

	@Test
	public void ifPresent(){
		List<UserInfoModel> userInfoModels=new ArrayList<UserInfoModel>();
		for(int i=0;i<10;i++){
			UserInfoModel userInfoModel=new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10,35));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});
		Optional<UserInfoModel> model=userInfoModels.stream().filter(u->u.getSex().equals("男")).findFirst();
		if(model.isPresent()){
			model.get().setSex("111");
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄:{}", p.getSex());
		});	}


}
