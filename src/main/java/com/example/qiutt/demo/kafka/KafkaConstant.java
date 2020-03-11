package com.example.qiutt.demo.kafka;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-11 11:34
 */
public class KafkaConstant {
	public static final String COMSUMERS_PATH = "/consumers";
	public static final String BROKER_IDS_PATH = "/brokers/ids";
	public static final String BROKER_TOPICS_PATH = "/brokers/topics";
	public static final String CONFIG_TOPICS_PATH = "/config/topics";
	public static final String CONFIG_CHANGES_PATH = "/config/changes";
	public static final String CONTROLLER_PATH = "/controller";
	public static final String CONTROLLER_EPOCH_PATH = "/controller_epoch";
	public static final String ADMIN_PATH = "/admin";
	public static final String ADMIN_REASSGIN_PARTITIONS_PATH = "/admin/reassign_partitions";
	public static final String ADMIN_DELETE_TOPICS_PATH = "/admin/delete_topics";
	public static final String ADMIN_PREFERED_REPLICA_ELECTION_PATH = "/admin/preferred_replica_election";
	public static final String HOST = "host";
	public static final String PORT = "port";
	public static final String KEY_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
	public static final String VALUE_SERIALIZER="org.apache.kafka.common.serialization.StringSerializer";
}
