package com.xpcomrade.study.es.api;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.junit.Test;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class BulkAPI extends AbstractBaseTest {

    @Test
    public void bulk() throws Exception{
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
                .setSource(
                        jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )

        );


        bulkRequest.add(client.prepareIndex("twitter", "tweet", "2")
                        .setSource(
                                jsonBuilder()
                                        .startObject()
                                        .field("user", "kimchy")
                                        .field("postDate", new Date())
                                        .field("message", "another post")
                                        .endObject()
                        )

        );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }

}
