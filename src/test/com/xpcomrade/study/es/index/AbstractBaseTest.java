package com.xpcomrade.study.es.index;

import com.xpcomrade.study.es.ElasticsearchConfig;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.After;
import org.junit.Before;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public abstract class AbstractBaseTest {

    protected TransportClient client;

    @Before
    public void initClient() {
        ElasticsearchConfig config = new ElasticsearchConfig();
        try {
            client = config.client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close () {
        if (null != client) {
            client.close();
        }
    }






}
