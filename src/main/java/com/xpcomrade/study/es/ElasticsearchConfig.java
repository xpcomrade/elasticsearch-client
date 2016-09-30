package com.xpcomrade.study.es;

import org.apache.ibatis.io.Resources;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class ElasticsearchConfig {

    private TransportClient client;

    public static final String COLON = ":";

    public static final String COMMA = ",";

    static Properties prop;

    static {
        try {
            prop = Resources.getResourceAsProperties("config/es.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TransportClient client() throws Exception {
        if (null == client) {
            buildlClient();
        }

        return client;
    }

    private synchronized void buildlClient() throws Exception {

        client = TransportClient
                .builder()
                .settings(settings())
                .addPlugin(DeleteByQueryPlugin.class)
                .build();

        String[] clusterNodes = prop.getProperty("es.cluster.nodes").split(COMMA);
        if (null == clusterNodes || 0 == clusterNodes.length) {
            throw new NullPointerException("es nodes is null ");
        }

        for (String node : clusterNodes) {
            String hostName = node.split(COLON)[0];
            String port = node.split(COLON)[1];
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
        }
    }

    private Settings settings() {
        return Settings.settingsBuilder()
                .put("cluster.name", prop.getProperty("es.cluster.name"))
                .put("client.transport.sniff", Boolean.valueOf(prop.getProperty("es.client.transport.sniff")))
                .put("client.transport.ignore_cluster_name", Boolean.valueOf(prop.getProperty("es.client.transport.ignore_cluster_name")))
                .put("client.transport.ping_timeout", prop.getProperty("es.client.transport.ping_timeout"))
                .put("client.transport.nodes_sampler_interval", prop.getProperty("es.client.transport.nodes_sampler_interval"))
                .build();
    }

}
