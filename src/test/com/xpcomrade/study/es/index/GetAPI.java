package com.xpcomrade.study.es.index;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class GetAPI extends AbstractBaseTest {

    @Test
    public void get() {
        GetResponse response = client.prepareGet("twitter", "tweet", "1").setOperationThreaded(false).get();

        Map<String, Object> source = response.getSourceAsMap();
        System.out.println(response.getIndex());
    }
}
