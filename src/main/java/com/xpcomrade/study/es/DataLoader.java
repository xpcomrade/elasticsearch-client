package com.xpcomrade.study.es;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class DataLoader {

    private DataLoader(){

    }

    /**
     * 获取全部数据
     * @return
     */
    public static List<Map<String, Object>> queryAllData() {
        SqlSession sqlSession = DSConnection.openSession();
        List<Map<String, Object>> dataList = null;
        try {
            dataList = sqlSession.selectList("LogMapper.queryAllData");

            handlerData(sqlSession, dataList);
        } finally {
            DSConnection.closeSession(sqlSession);
        }

        return dataList;
    }

    /**
     * 数据处理
     * @param dataList
     */
    private static void handlerData(SqlSession sqlSession, List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.size() <= 0) {
            return;
        }

        for (Map<String, Object> map : dataList) {

        }
    }
}
