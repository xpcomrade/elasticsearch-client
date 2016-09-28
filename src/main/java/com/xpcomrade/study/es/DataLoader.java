package com.xpcomrade.study.es;

import com.xpcomrade.study.ds.DBUtils;
import com.xpcomrade.study.ds.PageList;

import java.util.HashMap;
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
        List<Map<String, Object>> dataList = DBUtils.selectList("LogMapper.queryAllData");
        handlerData(dataList);

        return dataList;
    }

    public static PageList<Map<String, Object>> queryPage (int topage, int pageSize) {
        Map<String, Object> param = new HashMap<String, Object>();
        PageList<Map<String, Object>> pageList = DBUtils.queryForPageList("LogMapper.queryByPage", param, topage, pageSize);
        handlerData(pageList.getRecords());

        return pageList;
    }

    public static Integer queryCount() {
        return DBUtils.selectOne("LogMapper.queryByPageCount",null);
    }

    public static List<Map<String, Object>> queryList(int offset, int pageSize) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("offset", offset);
        param.put("pagesize", pageSize);
        List<Map<String, Object>> resultList = DBUtils.selectList("LogMapper.queryByPage", param);
        handlerData(resultList);

        return resultList;
    }

    /**
     * 数据处理
     * @param dataList
     */
    private static void handlerData(List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.size() <= 0) {
            return;
        }

        for (Map<String, Object> map : dataList) {
            map.put("domain", "http://api.51jiabo.com");
        }
    }
}
