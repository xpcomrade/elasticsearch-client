package com.xpcomrade.study.es;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * Created by xpcomrade on 2015/12/29.
 * Copyright (c) 2015, xpcomrade@gmail.com All Rights Reserved.
 * Description: (mybatis连接管理). <br/>
 */
public class DSConnection {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    private DSConnection(){

    }

    static {
        try {
            reader = Resources.getResourceAsReader("sqlmap-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlSessionFactory
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            throw new IllegalStateException("SqlSessionFactory not initialized");
        }
        return sqlSessionFactory;
    }

    /**
     * 获取sqlsession
     * @return
     */
    public static SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭sqlsession
     * @param sqlSession
     */
    public static void closeSession(SqlSession sqlSession){
        if (sqlSession == null) {
            return;
        }
        sqlSession.close();
    }

    /**
     * 事务提交
     * @param sqlSession
     */
    public static void commit(SqlSession sqlSession) {
        sqlSession.commit();
    }

    /**
     * 事务回滚
     * @param sqlSession
     */
    public static void rollback(SqlSession sqlSession){
        sqlSession.rollback();
    }
}
