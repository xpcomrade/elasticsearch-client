package com.xpcomrade.study.ds;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class PageUtil {

    /**
     * 获取偏移位置
     * @param toPage
     * @param pageSize
     * @return
     */
    public static int getOffset(int toPage, int pageSize){
        if(toPage < 1){
            toPage = 1;
        }

        if (pageSize < 0) {
            pageSize = 10;
        }

        return (toPage - 1) * pageSize;
    }
}
