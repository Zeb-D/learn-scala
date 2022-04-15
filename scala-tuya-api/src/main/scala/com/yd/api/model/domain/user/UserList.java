package com.yd.api.model.domain.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 *
 * @date: 2019-01-30 12:06
 */
public class UserList {

    //是否有更多用户信息
    @JSONField(name = "has_more")
    private Boolean hasMore;
    // 用户列表
    private List<User> list;

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
}
