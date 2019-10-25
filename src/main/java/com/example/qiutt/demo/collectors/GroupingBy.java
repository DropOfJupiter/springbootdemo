package com.example.qiutt.demo.collectors;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.example.qiutt.demo.common.StationStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-10-25 16:46
 */
@Slf4j
public class GroupingBy {


	@Test
	public void groupingByKey() throws InterruptedException {
		List<StationStatusDTO> models=new ArrayList<StationStatusDTO>();
		for(int i=0;i<10;i++){
			StationStatusDTO model=new StationStatusDTO();
			model.setStatusId(RandomUtil.randomLong(3,5));
			model.setValue(RandomUtil.randomInt(0,2));
			model.setStartTime(new Date());
			models.add(model);
			Thread.sleep(2000);
		}
		Map<String,List<StationStatusDTO>> results=models.stream().collect(Collectors.groupingBy(m->{
			return  DateUtil.format(m.getStartTime(),"yyyy-MM-dd HH:mm:ss");
		}));

		results.forEach(new BiConsumer<String, List<StationStatusDTO>>() {
			@Override
			public void accept(String s, List<StationStatusDTO> stationStatusDTOS) {
				log.info("自定义分组规则,key：[{}]，value:[{}]",s, JSON.toJSONString(stationStatusDTOS));
			}
		});
	}
}
