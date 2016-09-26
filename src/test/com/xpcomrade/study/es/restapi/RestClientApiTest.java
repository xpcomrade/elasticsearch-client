package com.xpcomrade.study.es.restapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;

/**
 * Created by xpcomrade on 2016/9/26.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class RestClientApiTest extends RestClientInit {


    @Test
    public void deleteIndex() {

        restClient.performRequestAsync("delete", "/hxjbapilog", Collections.<String, String>emptyMap(), new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                System.out.println(response.getStatusLine().getStatusCode());

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    @Test
    public void put() {


        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"access_time\": {\n" +
                        "      \"type\": \"date\",\n" +
                        "      \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                        "    },\n" +
                        "    \"action\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"app_name\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"app_version\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"appid\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"channel\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"create_time\": {\n" +
                        "      \"type\": \"date\",\n" +
                        "      \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                        "    },\n" +
                        "    \"device_type\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"gps_mapid\": {\n" +
                        "      \"type\": \"long\"\n" +
                        "    },\n" +
                        "    \"id\": {\n" +
                        "      \"type\": \"long\"\n" +
                        "    },\n" +
                        "    \"latitude\": {\n" +
                        "      \"type\": \"double\"\n" +
                        "    },\n" +
                        "    \"longitude\": {\n" +
                        "      \"type\": \"double\"\n" +
                        "    },\n" +
                        "    \"mac_address\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"mid\": {\n" +
                        "      \"type\": \"long\"\n" +
                        "    },\n" +
                        "    \"net_type\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"parameter_infos\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"analyzed\"\n" +
                        "    },\n" +
                        "    \"primary_key\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"request_headers\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"analyzed\"\n" +
                        "    },\n" +
                        "    \"server_ip\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"sys\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"sys_version\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"user_ip\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"userid\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    },\n" +
                        "    \"version\": {\n" +
                        "      \"type\": \"string\",\n" +
                        "      \"index\": \"not_analyzed\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}", ContentType.APPLICATION_JSON);
        Response response = null;
        try {
            response = restClient.performRequest("PUT", "/hxjbapilog/apilog/_mapping",
                    Collections.<String, String>emptyMap(), entity);
        } catch (IOException e) {
            e.printStackTrace();
        }


        StatusLine statusLine = response.getStatusLine();

        HttpEntity result = response.getEntity();

        System.out.println(result.toString());

    }
}
