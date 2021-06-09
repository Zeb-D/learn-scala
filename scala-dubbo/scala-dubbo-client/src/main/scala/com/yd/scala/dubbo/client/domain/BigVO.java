package com.yd.scala.dubbo.client.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author created by Zeb灬D on 2020-12-07 14:58
 */
public class BigVO implements Serializable {
    private String bigName;
    private int age;
    private BigDecimal a = BigDecimal.TEN;
    private BigInteger b = BigInteger.ONE;


    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }
}
