package com.jfsnpm.jfsnpm.core.vo;


import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * 分页工具类
 * Created by yangchuanhuan on 16/6/9.
 */
public class PaginationVO {
    private int pageCurrent = 1;
    private long total;
    private List<Record> list;

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Record> getList() {
        return list;
    }

    public void setList(List<Record> list) {
        this.list = list;
    }
}
