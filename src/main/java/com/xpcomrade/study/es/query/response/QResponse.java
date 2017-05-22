package com.xpcomrade.study.es.query.response;

import com.xpcomrade.study.es.query.vo.QEntity;

import java.util.List;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public interface QResponse<T extends QEntity> {

    public void setHits(List<T> tList);

    /**
     * 获得结果集
     * @return 泛型实体对象。继承自QEntity
     */
    public List<T> getHits();
}
