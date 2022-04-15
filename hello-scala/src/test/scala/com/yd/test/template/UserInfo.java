package com.yd.test.template;

import com.yd.scala.hello.template.parse.jsonpath.annotation.JsonPath;
import lombok.Data;

/**
 * getdevice为refid,query字符串传给graphql后返回的是一个大的map结构,
 * Map<refid,单个原子SDK返回的数据>
 */
@Data
@JsonPath("$")
public class UserInfo {
    @JsonPath("$.u.id")
    private String id;
    @JsonPath("$.u.name")
    private String name;
}
