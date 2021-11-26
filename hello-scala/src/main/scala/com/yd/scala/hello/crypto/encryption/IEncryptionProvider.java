package com.yd.scala.hello.crypto.encryption;

import com.yd.scala.hello.crypto.config.IConfigLoader;

public interface IEncryptionProvider {
    byte[] encrypt(byte[] var1, String keyId);

    byte[] encrypt(byte[] var1);

    String encrypt(String var1);

    String encrypt(String var1, boolean regionsSync);

    byte[] decrypt(byte[] var1, String keyId) throws RuntimeException;

    byte[] decrypt(byte[] var1) throws RuntimeException;

    String decrypt(String var1);

    String decrypt(String var1, boolean regionsSync);

    byte[] encrypt(byte[] var1, String keyId, Integer version);

    byte[] decrypt(byte[] var1, String keyId, Integer version);

    Integer getLatestVersion(String keyId);

    IConfigLoader getConfigLoader();
}