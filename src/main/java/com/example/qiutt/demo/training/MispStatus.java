package com.example.qiutt.demo.training;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qiutt.demo.common.StationStatusDTO;
import com.example.qiutt.demo.common.StationStatusDetailVO;
import com.example.qiutt.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-12-23 18:21
 */
@Slf4j
public class MispStatus {

	private final static Integer STATUS_START_FLAG = 1;

	private final static Integer STATUS_END_FLAG = 0;

	@Test
	public void statusCount() throws InterruptedException {
		List<StationStatusDTO> models = new ArrayList<StationStatusDTO>();
		for (int i = 0; i < 10; i++) {
			StationStatusDTO model = new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(4, 5));
			model.setValue(RandomUtil.randomInt(0, 2));
			//model.setValue(i%2==0?0:1);
			model.setStartTime(DateUtils.addHour(new Date(),RandomUtil.randomInt(-2,2)));
			model.setStartTime(new Date(model.getStartTime().getTime()+RandomUtil.randomInt(-5000,5000)));
			models.add(model);
		}
		//正序
		models.sort(Comparator.comparing(StationStatusDTO::getStartTime));

		List<StationStatusDetailVO> detailVOS = new ArrayList<>();
		Map<Long, List<StationStatusDTO>> byStatusIdMap = models.stream().collect(Collectors.groupingBy(StationStatusDTO::getStatusId));
		byStatusIdMap.forEach(new BiConsumer<Long, List<StationStatusDTO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDTO> stationStatusDTOS) {
				DateUtils.DateStyle pattern = DateUtils.DateStyle.YYYY_MM_DD_HH;
				log.info("----------------");
				stationStatusDTOS.forEach(m -> {
					log.info("状态初始数据{}", m.toString());
				});//根据时间分组
				Map<String, List<StationStatusDTO>> detailByTimeMap = stationStatusDTOS.stream().collect
						(Collectors.groupingBy(d -> {
							return DateUtils.dateToString(d.getStartTime(), pattern);
						}));
				detailByTimeMap.forEach(new BiConsumer<String, List<StationStatusDTO>>() {
					@Override
					public void accept(String startTimeStr, List<StationStatusDTO> stationStatusDTOS1) {
						try {
							List<StationStatusDTO> tmp = stationStatusDTOS1;
							Integer originSize=stationStatusDTOS1.size();
							Date initStartTime=DateUtils.strToDate(startTimeStr,pattern);
							LocalDateTime initStartLocalDateTime=DateUtils.date2LocalDateTime(initStartTime);
							LocalDateTime localDateTime=LocalDateTime.of(initStartLocalDateTime.getYear(),initStartLocalDateTime.getMonth(),initStartLocalDateTime.getDayOfMonth(),
									pattern.equals(DateUtils.DateStyle.YYYY_MM_DD)?23:initStartLocalDateTime.getHour(),59,59,0);
							Date initEndTime=DateUtils.localDateTime2Date(localDateTime);
							stationStatusDTOS1.sort(Comparator.comparing(StationStatusDTO::getStartTime));
							if(!stationStatusDTOS1.get(0).getValue().equals(STATUS_START_FLAG)){
								StationStatusDTO begin=new StationStatusDTO();
								BeanUtils.copyProperties(stationStatusDTOS1.get(0),begin);
								begin.setValue(STATUS_START_FLAG);
								begin.setStartTime(initStartTime);
								tmp.add(begin);
							}
							if(!stationStatusDTOS1.get(originSize-1).getValue().equals(STATUS_END_FLAG)){
								StationStatusDTO end=new StationStatusDTO();
								BeanUtils.copyProperties(stationStatusDTOS1.get(0),end);
								end.setValue(STATUS_END_FLAG);
								end.setStartTime(initEndTime);
								tmp.add(end);
							}
							tmp.sort(Comparator.comparing(StationStatusDTO::getStartTime));
							tmp.forEach(m -> {
								log.info("状态补充后数据{}", m.toString());
							});//根据时间分组
							recursion(stationStatusDTOS1);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
			}
			private void recursion(List<StationStatusDTO> stationStatusDTOS) {
				List<StationStatusDTO> tmp = stationStatusDTOS;
				//找到集合中状态开始的记录
				Optional<StationStatusDTO> stationStatusDTO = stationStatusDTOS.stream().filter(s -> s.getValue().equals(STATUS_START_FLAG)).findFirst();
				if (!stationStatusDTO.isPresent()) {
					return;
				}
				Integer beginIndex = stationStatusDTOS.indexOf(stationStatusDTO.get());
				StationStatusDTO beginStatusDTO = stationStatusDTOS.get(beginIndex);
				Date startTime = beginStatusDTO.getStartTime();
				StationStatusDetailVO detailVO = new StationStatusDetailVO();
				detailVO.setStatusId(beginStatusDTO.getStatusId());
				if (beginIndex == stationStatusDTOS.size() - 1) {
					detailVO.setStartTime(beginStatusDTO.getStartTime());
					detailVO.setEndTime(new Date());
					detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
					detailVOS.add(detailVO);
				} else {
					for (int i = beginIndex + 1; i < stationStatusDTOS.size(); i++) {
						StationStatusDTO statusDTO1 = stationStatusDTOS.get(i);
						Integer statusValue1 = statusDTO1.getValue();
						if (!STATUS_START_FLAG.equals(statusValue1)) {
							detailVO.setStartTime(startTime);
							detailVO.setEndTime(statusDTO1.getStartTime());
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
							detailVOS.add(detailVO);
							recursion(tmp.subList(i + 1, tmp.size()));
							break;
						} else if (i == stationStatusDTOS.size() - 1) {
							detailVO.setStartTime(beginStatusDTO.getStartTime());
							detailVO.setEndTime(new Date());
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
							detailVOS.add(detailVO);
						}
					}
				}
			}
		});
		detailVOS.sort(Comparator.comparing(StationStatusDetailVO::getStatusId).thenComparing(StationStatusDetailVO::getStartTime));
		log.info("----------------");

		detailVOS.forEach(m -> {
			log.info("状态明细排序后数据{}", m.toString());
		});
	}

	@Test
	public void statusCount2() throws InterruptedException {
		List<StationStatusDTO> stationStatusDTOS = new ArrayList<StationStatusDTO>();
		for (int i = 0; i < 10; i++) {
			StationStatusDTO model = new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(4, 5));
			model.setValue(RandomUtil.randomInt(0, 2));
			//model.setValue(i%2==0?0:1);
			model.setStartTime(new Date());
			stationStatusDTOS.add(model);
			Thread.sleep(1000);
		}
		stationStatusDTOS.sort(Comparator.comparing(StationStatusDTO::getStatusId));

		stationStatusDTOS.forEach(s -> {
			log.info("状态初始数据{}", s.toString());
		});

		DateUtils.DateStyle pattern = DateUtils.DateStyle.YYYY_MM_DD;
		//正序
		stationStatusDTOS.sort(Comparator.comparing(StationStatusDTO::getStartTime));

		List<StationStatusDetailVO> detailVOS = new ArrayList<>();

		Map<Long, List<StationStatusDTO>> byStatusIdMap = stationStatusDTOS.stream().collect(Collectors.groupingBy(StationStatusDTO::getStatusId));

		byStatusIdMap.forEach(new BiConsumer<Long, List<StationStatusDTO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDTO> stationStatusDTOS) {
				//根据时间分组
				Map<String, List<StationStatusDTO>> detailByTimeMap = stationStatusDTOS.stream().collect
						(Collectors.groupingBy(d -> {
							return DateUtils.dateToString(d.getStartTime(), pattern);
						}));
				detailByTimeMap.forEach(new BiConsumer<String, List<StationStatusDTO>>() {
					@Override
					public void accept(String startTimeStr, List<StationStatusDTO> stationStatusDTOS1) {
						try {

							Date initStartTime = DateUtils.strToDate(startTimeStr, pattern);
							LocalDateTime initStartLocalDateTime = DateUtils.date2LocalDateTime(initStartTime);
							LocalDateTime localDateTime = LocalDateTime.of(initStartLocalDateTime.getYear(), initStartLocalDateTime.getMonth(), initStartLocalDateTime.getDayOfMonth(),
									pattern.equals(DateUtils.DateStyle.YYYY_MM_DD) ? 23 : initStartLocalDateTime.getHour(), 59, 59, 0);
							Date initEndTime = DateUtils.localDateTime2Date(localDateTime);
							stationStatusDTOS1.sort(Comparator.comparing(StationStatusDTO::getStartTime));
							recursion(stationStatusDTOS1, initStartTime, initEndTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
			}

			private void recursion(List<StationStatusDTO> stationStatusDTOS, Date initStartTime, Date initEndTime) throws ParseException {

				if (CollectionUtils.isEmpty(stationStatusDTOS)) {
					return;
				}
				List<StationStatusDTO> tmp = stationStatusDTOS;
				StationStatusDetailVO detailVO = new StationStatusDetailVO();
				StationStatusDTO beginStatusDTO = stationStatusDTOS.get(0);
				StationStatusDTO endStatusDTO = stationStatusDTOS.get(stationStatusDTOS.size() - 1);

				//第一个就是开始，那么更新开始时间
				if (beginStatusDTO.getValue().equals(STATUS_START_FLAG)) {
					initStartTime = beginStatusDTO.getStartTime();
				}
				//最后一个就是结束，那么就更新结束时间
				if (!endStatusDTO.getValue().equals(STATUS_START_FLAG)) {
					initEndTime = endStatusDTO.getStartTime();
				}
				log.info("状态{}的开始时间{},结束时间{}", beginStatusDTO.getStatusId(), initStartTime, initEndTime);

				if (stationStatusDTOS.size() == 1) {
					detailVO.setStartTime(initStartTime);
					detailVO.setEndTime(initEndTime);
					detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
					detailVOS.add(detailVO);
				} else {
					for (int i = 0; i < stationStatusDTOS.size(); i++) {
						StationStatusDTO statusDTO1 = stationStatusDTOS.get(i);
						Integer statusValue1 = statusDTO1.getValue();
						if (!STATUS_START_FLAG.equals(statusValue1)) {
							detailVO.setStartTime(initStartTime);
							detailVO.setEndTime(statusDTO1.getStartTime());
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
							detailVOS.add(detailVO);
							recursion(tmp.subList(i + 1, tmp.size()), initStartTime, initEndTime);
							break;
						} else if (i == stationStatusDTOS.size() - 1) {
							detailVO.setStartTime(beginStatusDTO.getStartTime());
							detailVO.setEndTime(initEndTime);
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
							detailVOS.add(detailVO);
						}
					}
				}
			}
		});
		detailVOS.sort(Comparator.comparing(StationStatusDetailVO::getStatusId).reversed().thenComparing(StationStatusDetailVO::getStartTime));
		detailVOS.forEach(d -> {
			log.info("状态明细计算后数据{}", d.toString());
		});
	}

	@Test
	public void statusCount3() throws InterruptedException {
		List<StationStatusDTO> stationStatusDTOS = new ArrayList<StationStatusDTO>();
		for (int i = 0; i < 10; i++) {
			StationStatusDTO model = new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(4, 5));
			model.setValue(RandomUtil.randomInt(0, 2));
			//model.setValue(i%2==0?0:1);
			model.setStartTime(new Date());
			stationStatusDTOS.add(model);
			Thread.sleep(1000);
		}
		stationStatusDTOS.sort(Comparator.comparing(StationStatusDTO::getStatusId));

		stationStatusDTOS.forEach(s -> {
			log.info("状态初始数据{}", s.toString());
		});

		DateUtils.DateStyle pattern = DateUtils.DateStyle.YYYY_MM_DD;
		//正序
		stationStatusDTOS.sort(Comparator.comparing(StationStatusDTO::getStartTime));

		List<StationStatusDetailVO> detailVOS = new ArrayList<>();

		Map<Long, List<StationStatusDTO>> byStatusIdMap = stationStatusDTOS.stream().collect(Collectors.groupingBy(StationStatusDTO::getStatusId));

		byStatusIdMap.forEach(new BiConsumer<Long, List<StationStatusDTO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDTO> stationStatusDTOS) {
				//根据时间分组
				Map<String, List<StationStatusDTO>> detailByTimeMap = stationStatusDTOS.stream().collect
						(Collectors.groupingBy(d -> {
							return DateUtils.dateToString(d.getStartTime(), pattern);
						}));
				detailByTimeMap.forEach(new BiConsumer<String, List<StationStatusDTO>>() {
					@Override
					public void accept(String startTimeStr, List<StationStatusDTO> stationStatusDTOS1) {
						try {

							Date initStartTime = DateUtils.strToDate(startTimeStr, pattern);
							LocalDateTime initStartLocalDateTime = DateUtils.date2LocalDateTime(initStartTime);
							LocalDateTime localDateTime = LocalDateTime.of(initStartLocalDateTime.getYear(), initStartLocalDateTime.getMonth(), initStartLocalDateTime.getDayOfMonth(),
									pattern.equals(DateUtils.DateStyle.YYYY_MM_DD) ? 23 : initStartLocalDateTime.getHour(), 59, 59, 0);
							Date initEndTime = DateUtils.localDateTime2Date(localDateTime);
							stationStatusDTOS1.sort(Comparator.comparing(StationStatusDTO::getStartTime));
							recursion(stationStatusDTOS1, initStartTime, initEndTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
			}

			private void recursion(List<StationStatusDTO> stationStatusDTOS, Date initStartTime, Date initEndTime) throws ParseException {

				if (CollectionUtils.isEmpty(stationStatusDTOS)) {
					return;
				}
				List<StationStatusDTO> tmp = stationStatusDTOS;
				StationStatusDetailVO detailVO = new StationStatusDetailVO();
				StationStatusDTO beginStatusDTO = stationStatusDTOS.get(0);
				StationStatusDTO endStatusDTO = stationStatusDTOS.get(stationStatusDTOS.size() - 1);

				//第一个就是开始，那么更新开始时间
				if (beginStatusDTO.getValue().equals(STATUS_START_FLAG)) {
					initStartTime = beginStatusDTO.getStartTime();
				}
				//最后一个就是结束，那么就更新结束时间
				if (!endStatusDTO.getValue().equals(STATUS_START_FLAG)) {
					initEndTime = endStatusDTO.getStartTime();
				}
				log.info("状态{}的开始时间{},结束时间{}", beginStatusDTO.getStatusId(), initStartTime, initEndTime);

				Integer beginIndex = 0;
				while (beginIndex != stationStatusDTOS.size()) {
					for (int i = beginIndex; i < stationStatusDTOS.size(); i++) {
						StationStatusDTO statusDTOi = stationStatusDTOS.get(i);
						for (int j = i + 1; j < stationStatusDTOS.size(); j++) {
							StationStatusDTO statusDTOj = stationStatusDTOS.get(j);
							if (!statusDTOi.getValue().equals(statusDTOj.getValue())) {
								detailVO.setStationId(beginStatusDTO.getStationId());
								detailVO.setStatusId(beginStatusDTO.getStatusId());
								if(statusDTOi.getValue().equals(STATUS_START_FLAG)){
									detailVO.setStartTime(statusDTOi.getStartTime());
									detailVO.setEndTime(statusDTOj.getStartTime());
								}else{
									detailVO.setStartTime(initStartTime);
									detailVO.setEndTime(statusDTOj.getStartTime());
									initStartTime=statusDTOj.getStartTime();
								}
								detailVO.setDuration(detailVO.getEndTime().getTime()-detailVO.getStartTime().getTime());
								detailVOS.add(detailVO);
								beginIndex = j+1;
								break;
							}
						}
						if(beginIndex!=i){
							break;
						}
					}
				}
			}
		});
		detailVOS.sort(Comparator.comparing(StationStatusDetailVO::getStatusId).reversed().thenComparing(StationStatusDetailVO::getStartTime));
		detailVOS.forEach(d -> {
			log.info("状态明细计算后数据{}", d.toString());
		});
	}
}
