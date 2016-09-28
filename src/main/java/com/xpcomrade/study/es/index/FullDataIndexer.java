package com.xpcomrade.study.es.index;

import com.xpcomrade.study.ds.PageList;
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

    /*@Override
    public void index() {
        long startime = System.currentTimeMillis();
        logger.info("索引开始:{}", new Date().toLocaleString());
        PageList<Map<String, Object>> pageList = DataLoader.queryPage(1, 20000);
        if (null == pageList || 0 == pageList.getTotalCount()) {
            logger.error("DataSet empty");
            return;
        }
        int totalPage = pageList.getTotalPage();
        System.out.println("总共:" + totalPage + "页，条数:" + pageList.getTotalCount());
        int topage = 1;
        while (totalPage >0) {
            if (topage != 1) {
                pageList = DataLoader.queryPage(topage, 20000);
            }
            IndexWriter.submit(pageList.getRecords());
            logger.info("第:{}页，索引完毕！", topage);
            topage++;
            totalPage--;

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logger.info("索引完毕，耗时:{}毫秒", (System.currentTimeMillis() - startime));
    }*/


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
        logger.info("总共:{}页，条数:{}", totalPage, totalCount);

        int topage = 1;
        List<Map<String, Object>> resultList = null;
        while (totalPage >0) {
            int offset = PageUtil.getOffset(topage, pageSize);
            resultList = DataLoader.queryList(offset, pageSize);

            IndexWriter.submit(resultList);
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
