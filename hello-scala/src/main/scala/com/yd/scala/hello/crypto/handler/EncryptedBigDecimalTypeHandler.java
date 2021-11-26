package com.yd.scala.hello.crypto.handler;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class EncryptedBigDecimalTypeHandler extends EncryptedTypeHandler<BigDecimal> {
    protected BigDecimal getOriginalValue(String decryptedValue) {
        return StringUtils.isEmpty(decryptedValue) ? null : new BigDecimal(decryptedValue);
    }
}