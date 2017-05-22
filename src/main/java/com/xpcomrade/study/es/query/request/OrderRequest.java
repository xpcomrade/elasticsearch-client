package com.xpcomrade.study.es.query.request;

import com.xpcomrade.study.es.query.QueryBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;

import java.io.Serializable;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class OrderRequest extends QRequest implements Serializable {


    /**
     * 查询关键字
     */
    private String q;
    /**
     * 查询结果返回指定字段
     */
    private String fields;

    /**
     * 分页
     */
    private int page;
    /**
     * 分页大小
     */
    private int pageSize = 20;

    private int from;

    @Override
    public QueryBuilder buildQuery(SearchRequestBuilder builder) {

        return null;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public int getFrom() {
        int p = Math.max(0, page);
        from = p == 0 ? 0 : (p - 1) * pageSize;
        return from;
    }


    public void setFrom(int from) {
        this.from = from;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
