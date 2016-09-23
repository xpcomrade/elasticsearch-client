package com.xpcomrade.study.es.index;

import org.elasticsearch.action.index.IndexResponse;
import org.junit.Test;

import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class IndexAPI extends AbstractBaseTest {

    @Test
    public void index() throws Exception {
        IndexResponse response = client.prepareIndex("hxjbApiLog", "apiLog", "1")
                .setSource(
                        jsonBuilder().startObject()
                                .field("user", "kimchy")
                                .field("postDate", new Date())
                                .field("message", "trying out Elasticsearch").endObject()
                )
                .get();

        // Index name
        String _index = response.getIndex();
        // Type name
        String _type = response.getType();
        // Document ID (generated or not)
        String _id = response.getId();
        // Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        // isCreated() is true if the document is a new one, false if it has been updated
        boolean created = response.isCreated();
    }
}
