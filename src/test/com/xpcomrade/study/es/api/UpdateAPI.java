package com.xpcomrade.study.es.api;

import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.get.GetResult;
import org.junit.Test;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class UpdateAPI extends AbstractBaseTest {

    @Test
    public void update(){

        try {
            UpdateResponse response = client.prepareUpdate("twitter", "tweet", "AVdQ4uxQzoR-8DGzXal1")
                    .setDoc(
                            jsonBuilder().startObject()
                                    .field("user", "xpcomrade")
                                    .field("postDate", new Date())
                                    .field("message", "trying out Elasticsearch").endObject()
                    )
                    .get();

            GetResult result = response.getGetResult();

            result.getFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
