package com.xpcomrade.study.es.api;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.junit.Test;

/**
 * Created by xpcomrade on 2016/9/26.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class IndexSettingsAPI extends AbstractBaseTest {

    @Test
    public void indexMapping () throws Exception {
        PutMappingResponse response = client.admin().indices().preparePutMapping("{\n" +
                "  \"hxjbapilog\": {\n" +
                "    \"mappings\": {\n" +
                "      \"apilog\": {\n" +
                "        \"properties\": {\n" +
                "          \"access_time\": {\n" +
                "            \"type\": \"date\",\n" +
                "            \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                "          },\n" +
                "          \"action\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"app_name\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"app_version\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"appid\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"channel\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"create_time\": {\n" +
                "            \"type\": \"date\",\n" +
                "            \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                "          },\n" +
                "          \"device_type\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"gps_mapid\": {\n" +
                "            \"type\": \"long\"\n" +
                "          },\n" +
                "          \"id\": {\n" +
                "            \"type\": \"long\"\n" +
                "          },\n" +
                "          \"latitude\": {\n" +
                "            \"type\": \"double\"\n" +
                "          },\n" +
                "          \"longitude\": {\n" +
                "            \"type\": \"double\"\n" +
                "          },\n" +
                "          \"mac_address\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"mid\": {\n" +
                "            \"type\": \"long\"\n" +
                "          },\n" +
                "          \"net_type\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"parameter_infos\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"analyzed\"\n" +
                "          },\n" +
                "          \"primary_key\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"request_headers\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"analyzed\"\n" +
                "          },\n" +
                "          \"server_ip\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"sys\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"sys_version\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"user_ip\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"userid\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          },\n" +
                "          \"version\": {\n" +
                "            \"type\": \"string\",\n" +
                "            \"api\": \"not_analyzed\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}").execute().get();


        boolean isAcknowledged = response.isAcknowledged();
    }

}
