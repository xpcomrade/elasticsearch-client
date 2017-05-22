package com.xpcomrade.study.es.query.request;

import com.xpcomrade.study.es.query.QueryBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public abstract class QRequest {

    abstract public QueryBuilder buildQuery(SearchRequestBuilder builder);

}
