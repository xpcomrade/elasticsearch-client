package com.xpcomrade.study.es.api;

import com.xpcomrade.study.ds.PageUtil;
import com.xpcomrade.study.ds.RedisClientWrapper;
import com.xpcomrade.study.es.DataLoader;
import com.xpcomrade.study.es.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/30.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class TimeIncrementDataIndexer implements Indexer {

    static Logger logger = LoggerFactory.getLogger(TimeIncrementDataIndexer.class);

    @Override
    public void index() {
        while(true) {
            try {
                Date currentDate = new Date();
                Date previousDate = new Date(readLastIndexTime());
                Integer totalCount = DataLoader.queryTimingIndexerLogsCount(previousDate);
                if (null == totalCount || 0 == totalCount) {
                    logger.error("DataSet empty");
                    continue;
                }

                int pageSize = 10000;
                int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
                int topage = 1;
                List<Map<String, Object>> resultList = null;
                while (totalPage > 0) {
                    int offset = PageUtil.getOffset(topage, pageSize);
                    resultList = DataLoader.queryTimingIndexerData(previousDate, offset, pageSize);

                    IndexWriter.submit(resultList, null, null);
                    logger.info("第{}页，索引完毕！共{}页, 条数: {}", topage, totalPage, totalCount);
                    topage++;
                    totalPage--;
                }

                writeLastIndexTime(currentDate.getTime());

                Thread.sleep(30000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 获取最后一次索引时间
     * @return
     */
    private static long readLastIndexTime() {
        try (Jedis jedis = RedisClientWrapper.getInstance().getResource()){
            String latest = jedis.get("timingIndexer.metadata.latest");
            if (null == latest || "".equals(latest)) {
                return System.currentTimeMillis();
            }
            return Long.parseLong(latest);
        }
    }

    /**
     * 将最后一次索引时间写入文件
     * @param timestamp
     */
    private static void writeLastIndexTime(long timestamp){
        try (Jedis jedis = RedisClientWrapper.getInstance().getResource()){
            jedis.set("timingIndexer.metadata.latest", timestamp+"");
        }
    }

    public static void main(String[] args) {
        new  TimeIncrementDataIndexer().index();
    }
}
