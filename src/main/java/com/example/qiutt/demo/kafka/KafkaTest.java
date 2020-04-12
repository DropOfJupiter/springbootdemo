package com.example.qiutt.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.qiutt.demo.JsonObject;
import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.admin.RackAwareMode;
import kafka.cluster.BrokerEndPoint;
import kafka.cluster.Cluster;
import kafka.server.ConfigType;
import kafka.utils.Json;
import kafka.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-09 10:12
 */
@Slf4j
public class KafkaTest {

	String zkURL="192.168.16.127:2181";
	String broker="192.168.16.127:9092";
//	String zkURL="192.168.8.113:31152";
//	String broker="192.168.8.113:30092";

	@Test
	public void getTopicByZk(){
		List<String> list=new ArrayList<>();
		list.clear();
//		String[] strings=new String[]{"1","2"};
//		for(int i=0;i<strings.length;i++){
//			strings[i]="0";
//		}
//		log.info("s:{}",strings.toString());
//		ZkUtils zkUtils = KafkaUtils.getZkUtils(zkURL);
//		List<String> topics=KafkaUtils.listTopicsByZK(zkUtils);
//		zkUtils.close();
	}

	@Test
	public void getTopicByAdminClent() throws ExecutionException, InterruptedException {
		Properties properties = new Properties();
		properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, broker);
		KafkaUtils.listTopicsByAdminClient(properties);
	}


	@Test
	public void createTopicByZk(){
		ZkUtils zkUtils = ZkUtils.apply(zkURL, 30000, 30000, JaasUtils.isZkSecurityEnabled());
		AdminUtils.createTopic(zkUtils,"user_order_info_topic",1,1,new Properties(), RackAwareMode.Enforced$.MODULE$);
		zkUtils.close();
	}


	@Test
	public void getTopicByAdminClient() throws ExecutionException, InterruptedException {
		Properties props = new Properties();
		// 只需要提供一个或多个 broker 的 IP 和端口
		props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, broker);
		// 创建 AdminClient 对象
		AdminClient client = AdminClient.create(props);
		// 获取 topic 列表
		ListTopicsResult topics = client.listTopics();

		System.out.println("aaa:"+JSON.toJSONString(topics));
	}

	//发送消息
	@Test
	public void send(){
		KafkaUtils.produce(zkURL,broker,"user_order_info_topic","bye bye1");
	}

	//发送消息
	@Test
	public void consume(){
		KafkaUtils.consume(zkURL,broker,"qiutt_topic_1");
	}
}
