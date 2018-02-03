package com.yd.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author Yd on  2018-01-27
 * @Description：
 **/
@Data
@ToString
public class GoodsInfo {
    private Integer id;
    private String name;
    private String type;
    private String goodStatus;
    private Integer age;
}
