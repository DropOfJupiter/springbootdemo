package com.example.qiutt.demo.stream;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import com.example.qiutt.demo.common.UserInfoModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
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

		nums.stream().filter(n -> n == null).collect(Collectors.toList()).forEach(b -> System.out.println(b));

		log.info("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());


	}

	@Test
	public void streamGroupBy() {
		PayTypeDetail[] payTypeDetails = new PayTypeDetail[4];
		payTypeDetails[0] = new PayTypeDetail(ConsumptPayType.BALANCE_CONSUMPT, BigDecimal.valueOf(1));
		payTypeDetails[1] = new PayTypeDetail(ConsumptPayType.ALIPAY_CONSUMPT, BigDecimal.valueOf(11));
		payTypeDetails[2] = new PayTypeDetail(ConsumptPayType.ALIPAY_CONSUMPT, BigDecimal.valueOf(22));
		payTypeDetails[3] = new PayTypeDetail(ConsumptPayType.BALANCE_CONSUMPT, BigDecimal.valueOf(10));

		List<PayTypeDetail> payTypeDetailList = Arrays.asList(payTypeDetails);
		log.info("数据初始化（分组前）:" + JSONUtil.toJsonStr(payTypeDetailList));
		List<PayTypeDetail> result = new ArrayList<PayTypeDetail>();
		//先分组
		Map<ConsumptPayType, List<PayTypeDetail>> groupByResultMap = payTypeDetailList.stream().collect(Collectors.groupingBy(PayTypeDetail::getConsumptPayType));
		log.info("分组后:" + JSONUtil.toJsonStr(groupByResultMap));
		//分组后累加，写法1
		groupByResultMap.forEach(new BiConsumer<ConsumptPayType, List<PayTypeDetail>>() {
			@Override
			public void accept(ConsumptPayType consumptPayType, List<PayTypeDetail> list) {
				BigDecimal totalAmountByType = list.stream().map(PayTypeDetail::getPayAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
				result.add(new PayTypeDetail(consumptPayType, totalAmountByType));
			}
		});
		log.info("分组后累加:" + JSONUtil.toJsonStr(result));
		//分组后累加，写法2
//		groupByResultMap.forEach((consumptPayType,list)->{
//				BigDecimal totalAmountByType=list.stream().map(PayTypeDetail::getPayAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
//				result.add(new PayTypeDetail(consumptPayType,totalAmountByType));
//		});
//		log.info("分组后累加:"+ JSONUtil.toJsonStr(result));

	}

	@Test
	public void streamFilterWithUserInfoModel() {
		List<UserInfoModel> userInfoModels = new ArrayList<UserInfoModel>();
		for (int i = 0; i < 10; i++) {
			UserInfoModel userInfoModel = new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10, 35));
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});

		userInfoModels.stream().map(userInfoModel -> {
			userInfoModel.setAge(userInfoModel.getAge() + 10);
			return userInfoModel;
		}).collect(Collectors.toList());

		userInfoModels.forEach(System.out::println);
		List<UserInfoModel> userInfoModelsAfterFilter = userInfoModels.stream().filter(u -> {
			return u.getAge() > 20;
		}).collect(Collectors.toList());

		userInfoModelsAfterFilter.forEach(p -> {
			log.info("过滤后用户大于20年龄，年龄{}", p.getAge());
		});
	}

	@Test
	public void streamCountWithUserInfoModel() {
		List<UserInfoModel> userInfoModels = new ArrayList<UserInfoModel>();
		Assert.isTrue(!CollectionUtils.isEmpty(userInfoModels), "userInfoModels is empty");

		for (int i = 0; i < 10; i++) {
			UserInfoModel userInfoModel = new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10, 35));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄{}", p.getAge());
		});
		Assert.isTrue(!CollectionUtils.isEmpty(userInfoModels), "1>0");
		log.info("男性的个数{}", userInfoModels.stream().filter(u -> {
			return u.getAge() > 20;
		}).count());

	}

	@Test
	public void ifPresent() {
		List<UserInfoModel> userInfoModels=init();

		Optional<UserInfoModel> model = userInfoModels.stream().filter(u -> u.getSex().equals("男")).findFirst();
		if (model.isPresent()) {
			model.get().setSex("111");
		}
		userInfoModels.forEach(p -> {
			log.info("用户年龄:{}", p.getSex());
		});
	}


	@Test
	public void allMatch() {
		List<UserInfoModel> userInfoModels=init();
		boolean allCleared=userInfoModels.stream().allMatch(userInfoModel -> userInfoModel.getSex().equals("男"));
		log.info("allCleared:{}",allCleared);
	}

	@Test
	public void max(){
		List<UserInfoModel> userInfoModels=init();
		UserInfoModel maxAge=userInfoModels.stream().max(new Comparator<UserInfoModel>() {
			@Override
			public int compare(UserInfoModel o1, UserInfoModel o2) {
				return o1.getAge()>o2.getAge()?1:-1;
			}
		}).get();
		log.info("maxAge:{}",maxAge.getAge());

	}

	private List<UserInfoModel> init() {
		List<UserInfoModel> userInfoModels = new ArrayList<UserInfoModel>();
		for (int i = 0; i < 10; i++) {
			UserInfoModel userInfoModel = new UserInfoModel();
			userInfoModel.setAge(RandomUtil.randomInt(10, 35));
			userInfoModel.setSex("男");
			userInfoModels.add(userInfoModel);
		}
		userInfoModels.forEach(p -> {
			log.info("init 用户年龄{}", p.getAge());
		});
		return userInfoModels;
	}

	@Test
	public void update() {
		List<UserInfoModel> userInfoModels=init();
		userInfoModels.stream().map(userInfoModel -> {
			userInfoModel.setAge(11);
			return userInfoModel;
		});
		for(UserInfoModel model:userInfoModels){
			model.setAge(12);
		}
		userInfoModels.forEach(p -> {
			log.info("修改 用户年龄{}", p.getAge());
		});
	}
}
