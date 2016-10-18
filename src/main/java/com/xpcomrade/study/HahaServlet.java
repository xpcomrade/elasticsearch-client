package com.xpcomrade.study;

import com.xpcomrade.study.es.api.FullDataIndexer;
import com.xpcomrade.study.es.api.TimeIncrementDataIndexer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xpcomrade on 2016/9/28.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class HahaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("full".equals(method)) {
            new FullDataIndexer().index();
        } else {

        }

        try (OutputStream output = response.getOutputStream()) {
            output.write("finish".getBytes("utf-8"));
            output.flush();
        }
    }




    @Override
    public void init(ServletConfig config) throws ServletException {
        new TimeIncrementDataIndexer().index();
        super.init(config);
    }
}
