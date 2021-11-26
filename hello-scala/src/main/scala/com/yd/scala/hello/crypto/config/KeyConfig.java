package com.yd.scala.hello.crypto.config;

import lombok.Data;

@Data
public class KeyConfig {
    private Integer version;
    private String key;
    private String iv;
    private String algorithm;
    private String mode;
    private String padding;
}