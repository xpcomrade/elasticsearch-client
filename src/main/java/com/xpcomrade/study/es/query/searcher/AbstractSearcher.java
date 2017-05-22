package com.xpcomrade.study.es.query.searcher;

import com.xpcomrade.study.es.query.QueryBuilder;
import com.xpcomrade.study.es.query.request.QRequest;
import com.xpcomrade.study.es.query.response.QResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.search.MultiMatchQuery;

import java.util.concurrent.TimeUnit;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (搜索抽象类). <br/>
 */
public abstract class AbstractSearcher<I extends QRequest, O extends QResponse> implements Searcher<I, O> {

    private TransportClient client;
    private String esIndex;
    private String esType;

    protected SearchResponse search(I i) {
        SearchRequestBuilder builder = client.prepareSearch(esIndex).setTypes(esType)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        QueryBuilder queryBuilder = i.buildQuery(builder);
        SearchResponse resp = builder.execute().actionGet(10, TimeUnit.SECONDS);

        return resp;
    }

    protected abstract O buildResponse(I i, SearchResponse response);

    public final O execute(I i) {
        SearchResponse resp = search(i);
        O o = buildResponse(i, resp);
        return o;
    }




}
