package com.xpcomrade.study.es.api;

import com.xpcomrade.study.ds.PageUtil;
import com.xpcomrade.study.es.DataLoader;
import com.xpcomrade.study.es.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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
        long startime = System.currentTimeMillis();
        logger.info("索引开始:{}", new Date().toLocaleString());
        Integer totalCount = DataLoader.queryCount();
        if (null == totalCount || 0 == totalCount) {
            logger.error("DataSet empty");
            return;
        }

        int pageSize = 10000;
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        int topage = 1;
        List<Map<String, Object>> resultList = null;
        while (totalPage >0) {
            int offset = PageUtil.getOffset(topage, pageSize);
            resultList = DataLoader.queryList(offset, pageSize);

            IndexWriter.submit(resultList, "hxjblog", "log");
            logger.info("第{}页，索引完毕！共{}页, 条数: {}", topage, totalPage, totalCount);
            topage++;
            totalPage--;
        }

        logger.info("索引完毕，耗时:{}毫秒", (System.currentTimeMillis() - startime));
    }

    public static void main(String[] args) {
        new FullDataIndexer().index();
    }
}
