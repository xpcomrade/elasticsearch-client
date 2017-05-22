package com.xpcomrade.study.es.query.response;

import com.xpcomrade.study.es.query.vo.QEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class SearchResponse<T extends QEntity> implements QResponse<T>, Serializable {

    /**
     * 查询耗费时间(毫秒)
     */
    private long took;
    /**
     * 查询是否超时
     */
    private boolean timeout;
    /**
     * 结果总数
     */
    private long total;

    private List<T> hits;

    public long getTook() {
        return took;
    }

    public void setTook(long took) {
        this.took = took;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getHits() {
        return hits;
    }

    public void setHits(List<T> hits) {
        this.hits = hits;
    }
}
