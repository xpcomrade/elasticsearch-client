package com.xpcomrade.study.es.index;

import com.xpcomrade.study.ds.PageList;
import com.xpcomrade.study.es.DataLoader;
import com.xpcomrade.study.es.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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
        PageList<Map<String, Object>> pageList = DataLoader.queryPage(1, 10000);
        if (null == pageList || 0 == pageList.getTotalCount()) {
            logger.error("DataSet empty");
            return;
        }
        int totalPage = pageList.getTotalPage();
        System.out.println("总共:" + totalPage + "页，条数:" + pageList.getTotalCount());
        int topage = 1;
        while (totalPage >0) {
            if (topage != 1) {
                pageList = DataLoader.queryPage(topage, 10000);
            }
            IndexWriter.submit(pageList.getRecords());
            logger.info("第:{}页，索引完毕！", topage);
            topage++;
            totalPage--;
        }

        logger.info("索引完毕，耗时:{}毫秒", (System.currentTimeMillis() - startime));
    }

    public static void main(String[] args) {
        new FullDataIndexer().index();
    }
}
