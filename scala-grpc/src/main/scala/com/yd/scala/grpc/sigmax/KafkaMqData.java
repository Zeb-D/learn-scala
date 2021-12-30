
package com.yd.scala.grpc.sigmax;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class KafkaMqData<T> implements Serializable {

    private static final long serialVersionUID = -1;

    private T data;

    private int count = 0;

    private long ct = 0L;

    private long rt = 0L;

    private String groupId;

    public KafkaMqData() {
    }

    public KafkaMqData(T data) {
        this.data = data;
    }

    private List<Long> offsets = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Long> getOffsets() {
        return offsets;
    }

    public void setOffsets(List<Long> offsets) {
        this.offsets = offsets;
    }

    public long getCt() {
        return ct;
    }

    public void setCt(long ct) {
        this.ct = ct;
    }

    public long getRt() {
        return rt;
    }

    public void setRt(long rt) {
        this.rt = rt;
    }

    public void addOffsets(long offset) {
        if (this.offsets == null) {
            this.offsets = new ArrayList<>();
        }
        this.offsets.add(offset);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
