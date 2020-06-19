package com.yd.scala.dubbo.client.domain;

import java.io.Serializable;

/**
 * @author created by Zeb-D on 2019-07-26 20:01
 */
public class BaseDo implements Serializable {
    private Integer pageNo;
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
