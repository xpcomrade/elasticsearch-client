package com.xpcomrade.study.es.index;

import com.xpcomrade.study.es.ESClientUtil;
import org.junit.Test;

/**
 * Created by xpcomrade on 2016/9/26.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class CreateIndex extends AbstractBaseTest {

    @Test
    public void createIndex () {
        ESClientUtil.createIndex(client, "hmuserlog_20160928");
    }
}
