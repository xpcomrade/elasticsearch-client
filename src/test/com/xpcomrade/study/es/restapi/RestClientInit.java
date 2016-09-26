package com.xpcomrade.study.es.restapi;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by xpcomrade on 2016/9/26.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class RestClientInit {

    protected RestClient restClient;

    @Before
    public void init () {
        try {
            restClient = RestClient.builder(new HttpHost(InetAddress.getByName("192.168.9.96"), 9200)).build();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close () {
        if(restClient != null) {
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
