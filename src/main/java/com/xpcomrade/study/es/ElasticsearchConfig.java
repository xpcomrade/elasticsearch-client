package com.xpcomrade.study.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;

import java.net.InetAddress;

/**
 * Created by xpcomrade on 2016/9/22.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class ElasticsearchConfig {

    private TransportClient client;

    private String clusterNodes = "192.168.9.96:9300";
    private String clusterName = "my-es-cluster";
    private Boolean clientTransportSniff = true;
    private Boolean clientIgnoreClusterName = Boolean.FALSE;
    private String clientPingTimeout = "5s";
    private String clientNodesSamplerInterval = "5s";

    public static final String COLON = ":";

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

        String hostName = clusterNodes.split(COLON)[0];
        String port = clusterNodes.split(COLON)[1];
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
    }

    private Settings settings() {
        return Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", clientTransportSniff)
                .put("client.transport.ignore_cluster_name", clientIgnoreClusterName)
                .put("client.transport.ping_timeout", clientPingTimeout)
                .put("client.transport.nodes_sampler_interval", clientNodesSamplerInterval)
                .build();
    }

}
