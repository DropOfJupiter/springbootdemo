package com.example.qiutt.demo.kafka;

import kafka.admin.AdminClient;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.Test;
import scala.collection.JavaConverters;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-09 10:12
 */
@Slf4j
public class KafkaTest {

	String zkURL="192.168.8.113:31152";
	//String zkURL="127.0.0.1:2181";
	String kafkaBrokers="127.0.0.1:9092";

	@Test
	public void getTopicByZk(){
		ZkUtils zkUtils = ZkUtils.apply(zkURL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
		Map<String, Properties> topics = JavaConverters.mapAsJavaMapConverter(AdminUtils.fetchAllTopicConfigs(zkUtils))
				.asJava();
		for (Map.Entry<String, Properties> entry : topics.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			log.info("topic:{}-----------value:[{}]",key,value);
			Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), key);
			// 查询topic-level属性
			Iterator it = props.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry1=(Map.Entry)it.next();
				Object key1 = entry.getKey();
				Object value1 = entry.getValue();
				log.info("topic1:{}-----------value1:[{}]",key1,value1);

			}

		}
		zkUtils.close();
	}

	@Test
	public void createTopicByZk(){
		ZkUtils zkUtils = ZkUtils.apply(zkURL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.createTopic(zkUtils,"qiutt_topic_1",1,1,new Properties(), RackAwareMode.Enforced$.MODULE$);
		zkUtils.close();
	}


//	@Test
//	public void getTopicByAdminClient(){
//		Properties props = new Properties();
//		// 只需要提供一个或多个 broker 的 IP 和端口
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
//		// 创建 AdminClient 对象
//		AdminClient client = AdminClient.create(props);
//		// 获取 topic 列表
//		Set topics = client.listTopics().names().get();
//		System.out.println(topics);
//	}
}
