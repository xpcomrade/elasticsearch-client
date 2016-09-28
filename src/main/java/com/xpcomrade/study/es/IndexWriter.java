package com.xpcomrade.study.es;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public final class IndexWriter {
    static Logger logger = LoggerFactory.getLogger(IndexWriter.class);

    private static TransportClient client;

    static final String index = "hmuserlog_20160928";
    static final String type = "apilog";

    private IndexWriter(){

    }

    static {
        initClient();
    }

    private static synchronized void initClient() {
        ElasticsearchConfig config = new ElasticsearchConfig();
        try {
            client = config.client();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Initialize es client cause an exception, {}", e);
        }
    }

    public static void submit(List<Map<String, Object>> resultList) {

        BulkProcessor processor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {

            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

            }
        }).build();

        for (Map<String, Object> map : resultList) {
            processor.add(new IndexRequest(index, type, map.get("id").toString()).source(map));
        }

        processor.close();
    }

}
