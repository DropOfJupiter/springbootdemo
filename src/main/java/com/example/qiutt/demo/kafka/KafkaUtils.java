package com.example.qiutt.demo.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import kafka.admin.AdminUtils;
import kafka.cluster.Broker;
import kafka.utils.ZkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.security.JaasUtils;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import org.apache.logging.log4j.util.Strings;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import scala.collection.JavaConversions;
import scala.collection.JavaConverters;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-10 16:18
 */
@Slf4j
public class KafkaUtils {

	private static final int SESSION_TIMEOUT = 1000 * 30; // 与 zookeeper 连接的 session 的过期时间
	private static final int CONNECT_TIMEOUT = 5000; // 连接 zookeeper 的超时时间

	/**
	 * 获取ZkUtils
	 */
	public static ZkUtils getZkUtils(String zkUrls) {
		ZkUtils zkUtils = null;
		try {
			zkUtils = ZkUtils.apply(zkUrls, SESSION_TIMEOUT, CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
		} catch (Exception e) {
			log.error("initialize zkUtils failed!");
			e.printStackTrace();
		}
		return zkUtils;
	}

	/**
	 * 获取kafka brokers
	 */
	public static List<String> getBrokers(String zkUrls) {
		ZkUtils zkUtils = getZkUtils(zkUrls);
		List<Broker> set2 = JavaConversions.seqAsJavaList(zkUtils.getAllBrokersInCluster());
		log.info("allBrokersInCluster:{}", JSON.toJSONString(set2));
		return null;
	}

	/**
	 * 获取所有brokers
	 */
	public static String getKafkaBrokersByZk(String zkUrls) throws IOException, KeeperException, InterruptedException {
		StringBuilder kafkaBrokers = new StringBuilder();
		ZooKeeper zk = new ZooKeeper(zkUrls, 10000, null);
		List<String> ids = zk.getChildren(KafkaConstant.BROKER_IDS_PATH, false);
		for (String id : ids) {
			String brokerInfo = new String(zk.getData(KafkaConstant.BROKER_IDS_PATH + "/" + id, false, null));
			JSONObject jsonObject = JSON.parseObject(brokerInfo);
			String brokerHost = jsonObject.getString(KafkaConstant.HOST);
			int brokerPort = jsonObject.getInteger(KafkaConstant.PORT);
			if (!brokerHost.isEmpty() && brokerPort != -1) {
				kafkaBrokers.append(brokerHost);
				kafkaBrokers.append(":");
				kafkaBrokers.append(brokerPort);
				kafkaBrokers.append(",");
			}
		}
		if (kafkaBrokers.length() > 1) {
			kafkaBrokers.setLength(kafkaBrokers.length() - 1);
		}
		log.info("kafkaBrokers:{}", kafkaBrokers);
		return kafkaBrokers.toString();
	}

	public static KafkaAdminClient getKafkaAdminClient(String zkUrls, String brokerUrls) {
		if (Strings.isBlank(brokerUrls)) {
			try {
				brokerUrls = getKafkaBrokersByZk(zkUrls);
				Properties properties = new Properties();
				properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokerUrls);
				return (KafkaAdminClient) AdminClient.create(properties);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取所有topic
	 */
	public static List<String> listTopicsByZK(ZkUtils zkUtils) {
		Map<String, Properties> topics = JavaConverters.mapAsJavaMapConverter(AdminUtils.fetchAllTopicConfigs(zkUtils))
				.asJava();
		List<String> list = topics.keySet().stream().map(k -> {
			log.info("topic:{}", k);
			return k;
		}).collect(Collectors.toList());
		return list;
	}

	/**
	 * 获取所有topic
	 */
	public static List<String> listTopicsByAdminClient(Properties properties) throws ExecutionException, InterruptedException {
		AdminClient adminClient = AdminClient.create(properties);
		ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
		listTopicsOptions.listInternal(false);
		ListTopicsResult listTopicsResult = adminClient.listTopics(listTopicsOptions);
		Collection<TopicListing> listings = listTopicsResult.listings().get();
		List<String> list = listings.stream().map(k -> {
			log.info("topic:{}", k.toString());
			return k.toString();
		}).collect(Collectors.toList());
//		Map<String, Properties> topics;
//		topics = JavaConverters.mapAsJavaMapConverter(AdminUtils.fetchAllTopicConfigs(zkUtils))
//				.asJava();
//		List<String> list=topics.keySet().stream().map(k->{
//			log.info("topic:{}",k);
//			return k;
//		}).collect(Collectors.toList());
		return list;
	}


	public static void produce(String zkUrls, String brokerUrls, String topic, String msg) {
		Properties props = new Properties();
		props.put("bootstrap.servers", brokerUrls);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", KafkaConstant.KEY_SERIALIZER);
		props.put("value.serializer", KafkaConstant.VALUE_SERIALIZER);

		Producer<String, String> producer = new KafkaProducer<>(props);
		try {
			RecordMetadata recordMetadata = producer.send(new ProducerRecord<String, String>(topic, "Key" + msg, "Value" + msg)).get();
			log.info("recordMetadata:{}",JSON.toJSONString(recordMetadata));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		producer.close();
	}

	public static void consume(String zkUrls, String brokerUrls, String topic) {
		Properties props = new Properties();
		props.put("bootstrap.servers", brokerUrls);
		props.put("group.id", "qiutt_group_id_test");
		props.put("enable.auto.commit", "true");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topic));
		final int minBatchSize = 200;
		List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				log.info("record-topic:{},record-key:{},record-value:{}", record.topic(), record.key(), record.value());
				buffer.add(record);
			}
			if (buffer.size() >= minBatchSize) {
				//insertIntoDb(buffer);
				consumer.commitSync();
				buffer.clear();
			}
		}
	}
}
