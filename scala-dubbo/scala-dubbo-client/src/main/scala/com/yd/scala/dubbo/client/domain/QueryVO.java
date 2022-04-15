package com.yd.scala.dubbo.client.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author created by Zeb灬D on 2022-03-23 15:59
 */
@Setter
@Getter
public class QueryVO implements Serializable {
    private static final long serialVersionUID=1L;

    private List<Long> ids ;

    private List<Long> batchIds ;
}
