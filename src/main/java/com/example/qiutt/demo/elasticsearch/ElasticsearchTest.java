package com.example.qiutt.demo.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-03-27 14:26
 */
@Slf4j
public class ElasticsearchTest {

	private TransportClient initElasticSearchClient() throws IOException {
		//on startup
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.8.113"), 31848));
		return client;
	}
	@Test
	public void getIndexContent() throws IOException {
		TransportClient client = initElasticSearchClient();
		String esIndex = "flink-12345678";
		SearchResponse searchResponse = client.prepareSearch(esIndex)
				.setTypes("doc")
				.setSearchType(SearchType.QUERY_AND_FETCH)
				//.setFetchSource(new String[]{"the_field_you_want_to_fetch"}, null)
				//.setQuery(QueryBuilders.termsQuery("your_query_key", "your_query_value"))
				.execute()
				.actionGet();
		int count = 0;
		for (SearchHit hit : searchResponse.getHits()) {
			String getSourceAsString = hit.getSourceAsString();
			log.info("getSourceAsString:{}",getSourceAsString);
		}
	}
}
