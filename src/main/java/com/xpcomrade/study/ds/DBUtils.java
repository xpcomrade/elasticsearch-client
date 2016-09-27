package com.xpcomrade.study.ds;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/9/27.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public final class DBUtils {

    private DBUtils() {

    }

    public static <T> T selectOne(String statement) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            return sqlSession.selectOne(statement);
        }
    }

    public static <T> T selectOne(String statement, Object parameter) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            return sqlSession.selectOne(statement, parameter);
        }
    }

    public static <E> List<E> selectList(String statement) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            return sqlSession.selectList(statement);
        }
    }

    public static <E> List<E> selectList(String statement, Object parameter) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            return sqlSession.selectList(statement, parameter);
        }
    }

    public static <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            return sqlSession.selectMap(statement, parameter, mapKey);
        }
    }

    public static <T> PageList<T> queryForPageList(String sqlstatement, Map<String, Object> param, int toPage, int pageSize) {
        try (SqlSession sqlSession = DSConnection.openSession()) {
            if (toPage < 1) {
                toPage = 1;
            }
            if (pageSize < 1) {
                pageSize = 10;
            }

            Integer totalSize = (Integer) sqlSession.selectOne(sqlstatement + "Count", param);
            int offset = PageUtil.getOffset(toPage, pageSize);
            if (param == null) {
                param = new HashMap<String, Object>();
            }

            param.put("offset", offset);
            param.put("pagesize", pageSize);

            PageList pageList = new PageList(offset, toPage, totalSize, pageSize, sqlSession.selectList(sqlstatement, param));

            return pageList;
        }
    }
}
