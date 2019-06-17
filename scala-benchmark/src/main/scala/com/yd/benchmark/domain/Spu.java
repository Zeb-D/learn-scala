package com.yd.benchmark.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Spu {
    private Long id;
    private String name;
}