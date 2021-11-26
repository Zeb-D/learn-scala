package com.yd.scala.hello.crypto.encryption;

import com.yd.scala.hello.crypto.config.IConfigLoader;
import com.yd.scala.hello.crypto.config.KeyConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class EncryptionProvider implements IEncryptionProvider {
    private static final int PADDIN_VERSION_SIZE = 1;
    private static final int MAX_ROLLING_COUNT = 255;
    private static String DEFAULT_APP_KEY;
    private static String REGION_SYNC_APP_KEY;
    private static final String UTF8 = "utf-8";
    private static IConfigLoader configLoader;
    private boolean useKeyRolling;

    public EncryptionProvider(IConfigLoader configLoader, boolean useKeyRolling) {
        this.configLoader = configLoader;
        this.useKeyRolling = useKeyRolling;
        this.DEFAULT_APP_KEY = configLoader.getDefaultKey();
        this.REGION_SYNC_APP_KEY = configLoader.getRegionSyncKey();
    }

    private byte getVersionByteForPadding(int version) {
        return (byte) (version & MAX_ROLLING_COUNT);
    }

    private int getExactVersion(byte versionByte, String keyId) {
        Map<Integer, KeyConfig> configs = this.configLoader.getConfigs(keyId);
        Iterator var4 = configs.entrySet().iterator();

        Entry entry;
        do {
            if (!var4.hasNext()) {
                return 0;
            }

            entry = (Entry) var4.next();
        } while ((byte) ((Integer) entry.getKey() & 255) != versionByte);

        return (Integer) entry.getKey();
    }

    private KeyConfig getKeyConfig(String keyId, int version) {
        Map<Integer, KeyConfig> configs = this.configLoader.getConfigs(keyId);
        if (CollectionUtils.isEmpty(configs)) {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{0} no config!", keyId));
        } else {
            KeyConfig config = configs.get(version);
            if (config == null) {
                throw new RuntimeException(MessageFormat.format("Version not supported!keyId:{0},version:{1}", keyId, version));
            } else {
                return config;
            }
        }
    }

    public Integer getLatestVersion(String keyId) {
        Map<Integer, KeyConfig> versions = this.configLoader.getConfigs(keyId);
        if (CollectionUtils.isEmpty(versions)) {
            return null;
        } else {
            List<Integer> versionList = versions.keySet().stream().collect(Collectors.toList());
            if (CollectionUtils.isEmpty(versions)) {
                return null;
            } else {
                Collections.sort(versionList);
                return versionList.get(versionList.size() - 1);
            }
        }
    }

    @Override
    public IConfigLoader getConfigLoader() {
        return this.getConfigLoader();
    }

    public byte[] encrypt(byte[] plainBytes, String keyId) {
        Integer latestVersion = this.getLatestVersion(keyId);
        if (latestVersion != null && latestVersion > 0) {
            return this.encrypt(plainBytes, keyId, latestVersion);
        } else {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{0} no config!", keyId));
        }
    }

    public byte[] encrypt(byte[] plainBytes) {
        return this.encrypt(plainBytes, DEFAULT_APP_KEY);
    }

    public String encrypt(String plainText) {
        return this.encrypt(plainText, false);
    }

    public String encrypt(String plainText, boolean regionsSync) {
        if (plainText != null) {
            byte[] cipherBytes = this.encrypt(plainText.getBytes(), regionsSync ? REGION_SYNC_APP_KEY : DEFAULT_APP_KEY);
            return Base64.encodeBase64String(cipherBytes);
        } else {
            return null;
        }
    }

    public byte[] encrypt(byte[] plainBytes, String keyId, Integer version) {
        KeyConfig config = this.getKeyConfig(keyId, version);
        byte[] forEncrypt = plainBytes;
        if (this.useKeyRolling) {
            forEncrypt = new byte[plainBytes.length + 1];
            forEncrypt[0] = this.getVersionByteForPadding(config.getVersion());
            System.arraycopy(plainBytes, 0, forEncrypt, 1, plainBytes.length);
        }

        byte[] encryptedBytes = this.simpleEncrypt(forEncrypt, config);
        byte[] resultBytes = encryptedBytes;
        if (this.useKeyRolling) {
            resultBytes = new byte[encryptedBytes.length + 1];
            resultBytes[0] = this.getVersionByteForPadding(config.getVersion());
            System.arraycopy(encryptedBytes, 0, resultBytes, 1, encryptedBytes.length);
        }

        return resultBytes;
    }

    private byte[] simpleEncrypt(byte[] plainBytes, KeyConfig config) {
        try {
            String transformation = config.getAlgorithm() + "/" + config.getMode() + "/" + config.getPadding();
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keySpec = new SecretKeySpec(config.getKey().getBytes(), config.getAlgorithm());
            if (StringUtils.isNotBlank(config.getIv())) {
                IvParameterSpec ivSpec = new IvParameterSpec(config.getIv().getBytes());
                cipher.init(1, keySpec, ivSpec);
            } else {
                cipher.init(1, keySpec);
            }

            return cipher.doFinal(plainBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException var7) {
            throw new RuntimeException("Config error！", var7);
        } catch (BadPaddingException | IllegalBlockSizeException var8) {
            throw new RuntimeException("encrypt error!", var8);
        }
    }

    public byte[] decrypt(byte[] cipherBytes, String keyId) throws RuntimeException {
        Integer latestVersion = this.getLatestVersion(keyId);
        if (latestVersion != null && latestVersion > 0) {
            if (this.useKeyRolling) {
                return this.decryptWithKeyRolling(cipherBytes, keyId);
            } else {
                KeyConfig config = this.getKeyConfig(keyId, latestVersion);
                return this.simpleDecrypt(cipherBytes, config);
            }
        } else {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{0} no config!", keyId));
        }
    }

    public byte[] decrypt(byte[] cipherBytes) throws RuntimeException {
        return this.decrypt(cipherBytes, DEFAULT_APP_KEY);
    }

    public String decrypt(String cipherText) {
        return this.decrypt(cipherText, false);
    }

    public String decrypt(String cipherText, boolean regionsSync) {
        if (cipherText == null) {
            return null;
        } else if (cipherText.equals("")) {
            return "";
        } else {
            try {
                byte[] cipherBytes = Base64.decodeBase64(cipherText);
                byte[] plainBytes = this.decrypt(cipherBytes, regionsSync ? REGION_SYNC_APP_KEY : DEFAULT_APP_KEY);
                return new String(plainBytes, UTF8);
            } catch (RuntimeException var5) {
                throw new RuntimeException("解密失败", var5);
            } catch (UnsupportedEncodingException var6) {
                throw new RuntimeException("字符类型转换失败", var6);
            }
        }
    }

    public byte[] decrypt(byte[] cipherBytes, String keyId, Integer version) {
        if (this.useKeyRolling) {
            throw new NotImplementedException("Not mplemented when using key rolling!");
        } else {
            KeyConfig config = this.getKeyConfig(keyId, version);
            return this.simpleDecrypt(cipherBytes, config);
        }
    }

    private byte[] decryptWithKeyRolling(byte[] cipherBytes, String keyId) throws RuntimeException {
        boolean success = true;
        byte[] plainBytes = null;
        if (cipherBytes.length < 2) {
            success = false;
        } else {
            byte versionByte = cipherBytes[0];
            int exactVersion = this.getExactVersion(versionByte, keyId);
            if (exactVersion <= 0) {
                success = false;
            } else {
                KeyConfig config = this.getKeyConfig(keyId, exactVersion);

                try {
                    plainBytes = this.simpleDecrypt(Arrays.copyOfRange(cipherBytes, 1, cipherBytes.length), config);
                } catch (Exception var9) {
                    throw new RuntimeException(var9);
                }

                if (plainBytes != null && plainBytes.length >= 1) {
                    byte versionInPlain = plainBytes[0];
                    if (versionInPlain != versionByte) {
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        }

        if (!success) {
            throw new RuntimeException(MessageFormat.format("KeyId:{0},head bytes:{1}", keyId, this.headBytesToString(cipherBytes, 2)));
        } else {
            return Arrays.copyOfRange(plainBytes, 1, plainBytes.length);
        }
    }

    private String headBytesToString(byte[] bytes, int len) {
        if (bytes.length <= len) {
            return Arrays.toString(bytes);
        } else {
            StringBuilder sb = new StringBuilder(len + 5);
            sb.append('[');

            for (int i = 0; i < len; ++i) {
                sb.append(bytes[i]);
            }

            sb.append("...]");
            return sb.toString();
        }
    }

    private byte[] simpleDecrypt(byte[] cipherBytes, KeyConfig config) {
        try {
            String transformation = config.getAlgorithm() + "/" + config.getMode() + "/" + config.getPadding();
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keySpec = new SecretKeySpec(config.getKey().getBytes(), config.getAlgorithm());
            if (StringUtils.isNotBlank(config.getIv())) {
                IvParameterSpec ivSpec = new IvParameterSpec(config.getIv().getBytes());
                cipher.init(2, keySpec, ivSpec);
            } else {
                cipher.init(2, keySpec);
            }

            return cipher.doFinal(cipherBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException var7) {
            throw new RuntimeException("Config error！", var7);
        } catch (BadPaddingException | IllegalBlockSizeException var8) {
            throw new RuntimeException("decrypt error!", var8);
        }
    }
}