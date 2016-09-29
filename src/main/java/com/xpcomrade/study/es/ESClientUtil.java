package com.xpcomrade.study.es;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class ESClientUtil {

    static Logger logger = LoggerFactory.getLogger(ESClientUtil.class);

    private ESClientUtil(){

    }


    public static void createIndex(TransportClient client, String indexName) {
        if (null == client) {
            throw new NullPointerException("TransportClient is null");
        }

        if(isIndexExists(client, indexName)) {
            throw new RuntimeException("Index  " + indexName + " already exits!");
        }
        CreateIndexRequest cIndexRequest = new CreateIndexRequest(indexName);
        CreateIndexResponse cIndexResponse = client.admin().indices().create(cIndexRequest).actionGet();
        if (cIndexResponse.isAcknowledged()) {
            logger.info("Create INDEX [{}] successfully！", indexName);
        } else {
            logger.info("Fail to create INDEX [{}]", indexName);
        }
    }

    public static boolean isIndexExists(TransportClient client, String indexName) {
        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(inExistsRequest).actionGet();
        if (inExistsResponse.isExists()) {
            return true;
        } else {
            return false;
        }
    }

}
