package com.yd.scala.dubbo.client.domain;


import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author created by Zeb-D on 2019-05-09 14:53
 */
public class User implements Serializable {//extends BaseDo
    private Integer age;
    private String name;
//    private Byte sex;
//    private Integer[] list;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Byte getSex() {
//        return sex;
//    }
//
//    public void setSex(Byte sex) {
//        this.sex = sex;
//    }

//    public Integer[] getList() {
//        return list;
//    }
//
//    public void setList(Integer[] list) {
//        this.list = list;
//    }

    public static void main(String[] args) {
        List list = new ArrayList<>();
        ReflectionUtils.doWithFields(User.class, list::add);
        System.out.println(list);
    }
}
