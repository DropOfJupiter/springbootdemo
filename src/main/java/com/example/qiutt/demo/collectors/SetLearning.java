package com.example.qiutt.demo.collectors;

import cn.hutool.json.JSONUtil;
import com.example.qiutt.demo.stream.ConsumptPayType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2019-03-27 18:21
 */
@Slf4j
public class SetLearning {

	@Test
	public void testSet(){
		Set<ResourceUUIDAndType> set=new HashSet<ResourceUUIDAndType>();
		ResourceUUIDAndType resourceUUIDAndType1=new ResourceUUIDAndType("111", ConsumptPayType.ALIPAY_CONSUMPT);
		set.add(resourceUUIDAndType1);
		resourceUUIDAndType1=new ResourceUUIDAndType("222",ConsumptPayType.BALANCE_CONSUMPT);
		set.add(resourceUUIDAndType1);
		resourceUUIDAndType1=new ResourceUUIDAndType("111",ConsumptPayType.ALIPAY_CONSUMPT);
		set.add(resourceUUIDAndType1);
		log.info(JSONUtil.toJsonStr(set));

		List<ResourceUUIDAndType> list=new ArrayList<>();
		resourceUUIDAndType1=new ResourceUUIDAndType("111", ConsumptPayType.ALIPAY_CONSUMPT);
		list.add(resourceUUIDAndType1);
		resourceUUIDAndType1=new ResourceUUIDAndType("222",ConsumptPayType.BALANCE_CONSUMPT);
		list.add(resourceUUIDAndType1);
		resourceUUIDAndType1=new ResourceUUIDAndType("111",ConsumptPayType.ALIPAY_CONSUMPT);
		list.add(resourceUUIDAndType1);
		list=list.stream().distinct().collect(Collectors.toList());
		log.info(JSONUtil.toJsonStr(list));

		list=list.stream().filter(distinctByKey(item->item.getResourceUUID())).collect(Collectors.toList());
		log.info(JSONUtil.toJsonStr(list));
		HashSet<ConsumptPayType> ConsumptPayTypeList=new HashSet<ConsumptPayType>();
		ConsumptPayType consumptPayType=null;
		for (ResourceUUIDAndType resourceUUIDAndType : list) {
			if(consumptPayType!=null){
				if(consumptPayType.equals(resourceUUIDAndType.getType())){
					continue;
				}else{
					consumptPayType = resourceUUIDAndType.getType();
				}
			}else{
				consumptPayType = resourceUUIDAndType.getType();
			}
			//ResourceUUIDAndType没有重写equals和hashCode，所以用这个方式来去重
			ConsumptPayTypeList.add(consumptPayType);
		}
		log.info(JSONUtil.toJsonStr(ConsumptPayTypeList));

	}

	class ResourceUUIDAndType{
		private String resourceUUID;
		private ConsumptPayType type;

		public ResourceUUIDAndType(String resourceUUID, ConsumptPayType type) {
			this.resourceUUID = resourceUUID;
			this.type= type;
		}

		public String getResourceUUID() {
			return resourceUUID;
		}

		public void setResourceUUID(String resourceUUID) {
			this.resourceUUID = resourceUUID;
		}

		public ConsumptPayType getType() {
			return type;
		}

		public void setType(ConsumptPayType type) {
			this.type = type;
		}

//		@Override
//		public boolean equals(Object obj) {
//			if(!(obj instanceof ResourceUUIDAndType)) {
//				return false;
//			}
//			ResourceUUIDAndType b = (ResourceUUIDAndType)obj;
//			if(this.resourceUUID.equals(b.getResourceUUID())&&this.type.equals(b.getType())) {
//				return true;
//			}
//			return false;
//		}
//
//		@Override
//		public int hashCode() {
//			log.info("type.hashCode()："+type.hashCode());
//			return type.hashCode();
//		}
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object,Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	@Test
	public void addAll(){
		Set set= new HashSet();
		set.add("1");

		set.addAll(Arrays.asList("1"));
		set.forEach(s->{
			log.info(""+s);
		});
	}

	@Test
	public void retainAll(){
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		set1.add("abc");
		set1.add("123");
		set1.add("ABC");
//		set2.add("abc1");
//		set2.add("abc2");
//		set2.add("abc3");
		//此处注意，retainAll之后，set1的内容将会发生改变，改变为两个集合的交集内容，
		//如不想set1的内容发生改变，则新建一个List保存
		set2.retainAll(set1);
		System.out.println("交集元素个数是："+set2.size());
	}

	@Test
	public void retainAll1(){
		String str="1,2,3";
		String[] strs=str.split(",");
		Integer[] array = (Integer[]) ConvertUtils.convert(strs, Integer.class);
		log.info("{}",array.toString());
	}
}
