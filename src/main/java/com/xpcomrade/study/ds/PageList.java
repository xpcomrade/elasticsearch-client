package com.xpcomrade.study.ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xpcomrade on 2016/9/23.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
public class PageList<T> implements Serializable {

    private static final long serialVersionUID = -2949497622293336316L;

    /**
     * 当前页第一条数据在List中的位置,从0开始
     */
    private int start = 0;
    /**
     * 当前页 默认1
     */
    private int currentPage = 1;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 页大小
     */
    private int pageSize = 20;
    /**
     * 当前页中存放的数据
     */
    private List<T> records = new ArrayList<T>();

    /**
     * 构造方法，构造空数
     */
    public PageList() {
        this(0, 1, 0, 20, new ArrayList<T>());
    }

    /**
     * 带参数构造方法
     *
     * @param start     当前页中起始数据中的位置
     * @param currentPage 当前页
     * @param totalCount 总记录行数
     * @param pageSize  当前页大中
     * @param records   当前页记录数据
     */
    public PageList(int start, int currentPage, int totalCount, int pageSize, List<T> records) {
        this.start = start;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.records = records;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    /**
     * 设置当前页
     *
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1)
            currentPage = 1;
        this.currentPage = currentPage;
    }

    /**
     * 取当前页码 ，从1开始
     *
     * @return
     */
    public int getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean hashNextPage() {
        return this.getCurrentPageNo() < this.getTotalPage();
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public int getTotalPage() {
        if (this.totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return totalCount / pageSize + 1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取任一页第一条数据的位置,startIndex从0开始
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 取前一页
     *
     * @return
     */
    public int getPreviousPage() {
        return currentPage > 1 ? currentPage - 1 : currentPage;
    }

    /**
     * 取下一页  如果当前页大于总页数返回-1
     *
     * @return
     */
    public int getNextPage() {
        return currentPage < this.getTotalPage() ? currentPage + 1 : -1;
    }

    @Override
    public String toString() {
        return "PageList [start=" + start + ", currentPage=" + currentPage
                + ", totalCount=" + totalCount + ", pageSize=" + pageSize
                + ", records=" + records + "]";
    }
}
