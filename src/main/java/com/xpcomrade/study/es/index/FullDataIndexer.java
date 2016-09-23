package com.xpcomrade.study.es.index;

import com.xpcomrade.study.es.DataLoader;
import com.xpcomrade.study.es.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class FullDataIndexer implements Indexer {

    static Logger logger = LoggerFactory.getLogger(FullDataIndexer.class);

    @Override
    public void index() {

        List<Map<String, Object>> resultList = DataLoader.queryAllData();

        if (null == resultList || 0 == resultList.size()) {
            logger.error("No data can be indexed.");
            return;
        }

        IndexWriter.submit(resultList);
    }

    public static void main(String[] args) {
        new FullDataIndexer().index();
    }
}
