package com.yd.scala.hello.crypto.handler.regions.sync;

import com.yd.scala.hello.crypto.handler.EncryptedBigDecimalTypeHandler;

public class RegionsSyncBigDecimalHandler extends EncryptedBigDecimalTypeHandler {
    protected String getKeyId() {
        return super.encryptionProvider.getConfigLoader().getRegionSyncKey();
    }
}