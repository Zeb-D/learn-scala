package com.yd.scala.dubbo.client.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author created by Zeb灬D on 2020-12-04 15:28
 */
public class BooleanVO implements Serializable {
    private String name;
    @JSONField(name = "isDefault")
    private Boolean isDefault;
    private Boolean success;
    private int age;
    private Integer a;
    private BigInteger aa = BigInteger.ONE;
    private BigDecimal ab = BigDecimal.ONE;
    private List<BigVO> bigList = Arrays.asList(new BigVO());

    public List<BigVO> getBigList() {
        return bigList;
    }

    public void setBigList(List<BigVO> bigList) {
        this.bigList = bigList;
    }

    public BigDecimal getAb() {
        return ab;
    }

    public void setAb(BigDecimal ab) {
        this.ab = ab;
    }

    public BigInteger getAa() {
        return aa;
    }

    public void setAa(BigInteger aa) {
        this.aa = aa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }
}
