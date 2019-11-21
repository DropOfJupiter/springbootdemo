package com.example.qiutt.demo.stream;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qiutt.demo.common.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-10-23 18:43
 */
@Slf4j
public class StreamFindFirst {
	@Test
	public void findFirst() throws InterruptedException {
		List<StationStatusDTO> models = new ArrayList<StationStatusDTO>();
		for (int i = 0; i < 5; i++) {
			StationStatusDTO model = new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(3, 5));
			model.setValue(RandomUtil.randomInt(0, 2));
//			model.setValue(i%2==0?0:1);
			model.setStartTime(new Date());
			models.add(model);
			Thread.sleep(2000);
		}
		//正序
		models.sort(Comparator.comparing(StationStatusDTO::getStartTime));

		List<StationStatusDetailVO> detailVOS = new ArrayList<>();

		Map<Long, List<StationStatusDTO>> byStatusIdMap = models.stream().collect(Collectors.groupingBy(StationStatusDTO::getStatusId));

		byStatusIdMap.forEach(new BiConsumer<Long, List<StationStatusDTO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDTO> stationStatusDTOS) {
				//先根据该动作中开始时间升序
				stationStatusDTOS.sort(Comparator.comparing(StationStatusDTO::getStartTime));
				stationStatusDTOS.forEach(m -> {
					log.info("分组升序后数据{}", JSON.toJSONString(m));
				});
				Integer beginIndex = 0;
				StationStatusDTO statusDTO0 = stationStatusDTOS.get(beginIndex);
				Integer beginValue = statusDTO0.getValue();
				Date startTime = statusDTO0.getStartTime();

				while (beginIndex != stationStatusDTOS.size() - 1) {
					StationStatusDetailVO detailVO = new StationStatusDetailVO();
					detailVO.setStationId(statusDTO0.getStationId());
					detailVO.setStatusId(statusDTO0.getStatusId());
					detailVO.setStatusName(statusDTO0.getStatusName());
					for (int i = beginIndex + 1; i < stationStatusDTOS.size(); i++) {
						StationStatusDTO statusDTO1 = stationStatusDTOS.get(i);
						Integer statusValue1 = statusDTO1.getValue();
						if (!beginValue.equals(statusValue1)) {
							detailVO.setStartTime(startTime);
							detailVO.setEndTime(statusDTO1.getStartTime());
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()) / 1000);
							detailVOS.add(detailVO);
							beginIndex = i;
							beginValue = statusValue1;
							startTime = statusDTO1.getStartTime();
							break;
						} else if (i == stationStatusDTOS.size() - 1) {
							statusDTO1 = stationStatusDTOS.get(stationStatusDTOS.size() - 1);
							detailVO.setStartTime(startTime);
							detailVO.setEndTime(statusDTO1.getStartTime());
							detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()) / 1000);
							beginIndex = stationStatusDTOS.size() - 1;
							detailVOS.add(detailVO);
						}
					}
				}
				if (beginIndex == 0) {
					StationStatusDetailVO detailVO = new StationStatusDetailVO();
					detailVO.setStationId(statusDTO0.getStationId());
					detailVO.setStatusId(statusDTO0.getStatusId());
					detailVO.setStatusName(statusDTO0.getStatusName());
					detailVOS.add(detailVO);
				}
			}
		});
		detailVOS.forEach(m -> {
			log.info("状态明细处理后数据{}", JSON.toJSONString(m));
		});
		detailVOS.sort(Comparator.comparing(StationStatusDetailVO::getStartTime));
		detailVOS.forEach(m -> {
			log.info("状态明细排序后数据{}", JSON.toJSONString(m));
		});

		List<StationStatusSummaryVO> summaryVOS = new ArrayList<>();
		Map<Long, List<StationStatusDetailVO>> byStatusIdMap2 = detailVOS.stream().collect(Collectors.groupingBy(StationStatusDetailVO::getStatusId));
		byStatusIdMap2.forEach(new BiConsumer<Long, List<StationStatusDetailVO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDetailVO> stationStatusDetailVOS) {
				if (!CollectionUtils.isEmpty(stationStatusDetailVOS)) {
					Long sumDuration = stationStatusDetailVOS.stream().map(StationStatusDetailVO::getDuration).reduce(new BinaryOperator<Long>() {
						@Override
						public Long apply(Long long1, Long long2) {
							return long1 + long2;
						}
					}).get();
					summaryVOS.add(new StationStatusSummaryVO(statusId, stationStatusDetailVOS.get(0).getStatusName(), sumDuration));
				}
			}
		});
		summaryVOS.sort(Comparator.comparing(StationStatusSummaryVO::getStatusId));
		summaryVOS.forEach(m -> {
			log.info("状态汇总处理后数据{}", JSON.toJSONString(m));
		});
	}

	@Test
	public void statusCount() throws InterruptedException {
		List<StationStatusDTO> models = new ArrayList<StationStatusDTO>();
		for (int i = 0; i < 10; i++) {
			StationStatusDTO model = new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(3, 5));
			model.setValue(RandomUtil.randomInt(0, 2));
			//model.setValue(i%2==0?0:1);
			model.setStartTime(new Date());
			models.add(model);
			Thread.sleep(500);
		}
		//正序
		models.sort(Comparator.comparing(StationStatusDTO::getStartTime));

		List<StationStatusDetailVO> detailVOS = new ArrayList<>();
		//状态开始标志
		Integer startFlag = 1;
		Map<Long, List<StationStatusDTO>> byStatusIdMap = models.stream().collect(Collectors.groupingBy(StationStatusDTO::getStatusId));
		byStatusIdMap.forEach(new BiConsumer<Long, List<StationStatusDTO>>() {
			@Override
			public void accept(Long statusId, List<StationStatusDTO> stationStatusDTOS) {
				stationStatusDTOS.forEach(m -> {
					log.info("状态初始数据{}", JSON.toJSONString(m));
				});
				recursion(stationStatusDTOS);
			}

			private void recursion(List<StationStatusDTO> stationStatusDTOS) {
				log.info("当前递归的集合{}", JSONObject.toJSONString(stationStatusDTOS));
				List<StationStatusDTO> tmp = stationStatusDTOS;
				//找到集合中状态开始的记录
				Optional<StationStatusDTO> stationStatusDTO = stationStatusDTOS.stream().filter(s -> s.getValue().equals(startFlag)).findFirst();
				if (!stationStatusDTO.isPresent()) {
					return;
				}
				Integer beginIndex = stationStatusDTOS.indexOf(stationStatusDTO.get());
				StationStatusDTO beginStatusDTO = stationStatusDTOS.get(beginIndex);
				Date startTime = beginStatusDTO.getStartTime();
				StationStatusDetailVO detailVO = new StationStatusDetailVO();
				detailVO.setStatusId(beginStatusDTO.getStatusId());
				if(beginIndex==stationStatusDTOS.size() - 1){
					detailVO.setStartTime(beginStatusDTO.getStartTime());
					detailVO.setEndTime(new Date());
					detailVO.setDuration((detailVO.getEndTime().getTime() - detailVO.getStartTime().getTime()));
					detailVOS.add(detailVO);
				}else{
					for (int i = beginIndex + 1; i < stationStatusDTOS.size(); i++) {
						StationStatusDTO statusDTO1 = stationStatusDTOS.get(i);
						Integer statusValue1 = statusDTO1.getValue();
						if (!startFlag.equals(statusValue1)) {
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
		detailVOS.sort(Comparator.comparing(StationStatusDetailVO::getStartTime));
		detailVOS.forEach(m -> {
			log.info("状态明细排序后数据{}", JSON.toJSONString(m));
		});
	}
}
