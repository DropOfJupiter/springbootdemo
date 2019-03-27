package com.example.qiutt.demo.Collectors;

import cn.hutool.json.JSONUtil;
import com.example.qiutt.demo.stream.ConsumptPayType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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

		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof ResourceUUIDAndType)) {
				return false;
			}
			ResourceUUIDAndType b = (ResourceUUIDAndType)obj;
			if(this.resourceUUID.equals(b.getResourceUUID())&&this.type.equals(b.getType())) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			log.info("type.hashCode()："+type.hashCode());
			return type.hashCode();
		}
	}
}
