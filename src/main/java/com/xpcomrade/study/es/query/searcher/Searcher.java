package com.xpcomrade.study.es.query.searcher;

/**
 * Created by xpcomrade on 2016/11/7.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (搜索接口). <br/>
 */
public interface Searcher<I, O> {

    O execute(I i);
}
