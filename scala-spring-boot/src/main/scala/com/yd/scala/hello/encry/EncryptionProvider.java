package com.yd.scala.hello.encry;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.CollectionUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 密钥轮转的逻辑:<br />
 * 加密: <br />
 * 1.计算padding字符:(byte)(v & 0xFF) ,其中v为最新版本号或指定的版本号 <br />
 * 2.在明文字节数组的最前面pad上这个字符 <br />
 * 3.加密 <br />
 * 4.在加密结果前再pad上这个字符 <br />
 *
 * @author qianh
 */
public class EncryptionProvider {

    private static final int PADDIN_VERSION_SIZE = 1;

    private static final int MAX_ROLLING_COUNT = 0xFF;

    private LocalConfigLoader configLoader = new LocalConfigLoader();

    private boolean useKeyRolling;


    public static EncryptionProvider provider = new EncryptionProvider(true);
    private static final Charset ENCODING_CHARSET = Charset.forName("UTF-8");

    private static String encrypt(String plainText) {
        byte[] cipherBytes = provider.encrypt(plainText.getBytes(), "smart");
        return Base64.encodeBase64String(cipherBytes);
    }

    private static String decrypt(String cipherText) {
        byte[] cipherBytes = Base64.decodeBase64(cipherText);
        byte[] plainBytes = provider.decrypt(cipherBytes, "smart");
        return new String(plainBytes, ENCODING_CHARSET);
    }

    public static void main(String[] args) {
        String test = "sdfskdjflsdk";
        String encrypted = encrypt(test);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
        System.out.println(decrypt("Aa6b7aveWmABYMt8z2w="));
    }

    public EncryptionProvider(boolean useKeyRolling) {
        this.useKeyRolling = useKeyRolling;
    }

    private byte getVersionByteForPadding(int version) {
        //结果为0x00,0x01,0x02...0x0FF
        return (byte) (version & MAX_ROLLING_COUNT);
    }

    private int getExactVersion(byte versionByte, String keyId) {
        Map<Integer, LocalConfigLoader.KeyConfig> configs = configLoader.getConfigs(keyId);
        for (Entry<Integer, LocalConfigLoader.KeyConfig> entry : configs.entrySet()) {
            if ((byte) (entry.getKey() & MAX_ROLLING_COUNT) == versionByte) {
                return entry.getKey();
            }
        }
        return 0;
    }

    private LocalConfigLoader.KeyConfig getKeyConfig(String keyId, int version) {
        Map<Integer, LocalConfigLoader.KeyConfig> configs = configLoader.getConfigs(keyId);
        if (CollectionUtils.isEmpty(configs)) {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{0} no config!", keyId));
        }
        LocalConfigLoader.KeyConfig config = configs.get(version);
        if (config == null) {
            throw new RuntimeException(
                    MessageFormat.format("Version not supported!keyId:{0},version:{1}", keyId, version));
        }
        return config;
    }

    //-----------------开始加密部分-----------------
    public byte[] encrypt(byte[] plainBytes, String keyId) {
        Integer latestVersion = getLatestVersion(keyId);
        if (latestVersion == null || latestVersion <= 0) {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{} no config!", keyId));
        }
        return encrypt(plainBytes, keyId, latestVersion);
    }


    public byte[] decrypt(byte[] cipherBytes, String keyId) {
        Integer latestVersion = getLatestVersion(keyId);
        if (latestVersion == null || latestVersion <= 0) {
            throw new RuntimeException(MessageFormat.format("Config error!Key:{} no config!", keyId));
        }
        if (useKeyRolling) {
            return decryptWithKeyRolling(cipherBytes, keyId);
        }
        LocalConfigLoader.KeyConfig config = getKeyConfig(keyId, latestVersion);
        return simpleDecrypt(cipherBytes, config);
    }

    public byte[] encrypt(byte[] plainBytes, String keyId, Integer version) {
        LocalConfigLoader.KeyConfig config = getKeyConfig(keyId, version);
        byte[] forEncrypt = plainBytes;
        if (useKeyRolling) {
            forEncrypt = new byte[plainBytes.length + PADDIN_VERSION_SIZE];
            forEncrypt[0] = getVersionByteForPadding(config.getVersion());
            System.arraycopy(plainBytes, 0, forEncrypt, PADDIN_VERSION_SIZE, plainBytes.length);
        }
        byte[] encryptedBytes = simpleEncrypt(forEncrypt, config);
        byte[] resultBytes = encryptedBytes;
        if (useKeyRolling) {
            resultBytes = new byte[encryptedBytes.length + PADDIN_VERSION_SIZE];
            resultBytes[0] = getVersionByteForPadding(config.getVersion());
            System.arraycopy(encryptedBytes, 0, resultBytes, PADDIN_VERSION_SIZE, encryptedBytes.length);
        }
        return resultBytes;
    }

    public Integer getLatestVersion(String keyId) {
        Map<Integer, LocalConfigLoader.KeyConfig> versions = configLoader.getConfigs(keyId);
        if (CollectionUtils.isEmpty(versions)) {
            return null;
        }
        List<Integer> versionList = versions.keySet().stream().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(versions)) {
            return null;
        }
        Collections.sort(versionList);
        return versionList.get(versionList.size() - 1);
    }

    private byte[] simpleEncrypt(byte[] plainBytes, LocalConfigLoader.KeyConfig config) {
        try {
            String transformation = config.getAlgorithm() + "/" + config.getMode() + "/" + config.getPadding();
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keySpec = new SecretKeySpec(config.getKey().getBytes(), config.getAlgorithm());
            if (StringUtils.isNotBlank(config.getIv())) {
                IvParameterSpec ivSpec = new IvParameterSpec(config.getIv().getBytes());
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            }
            return cipher.doFinal(plainBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Config error！", e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("encrypt error!", e);
        }
    }

    private byte[] decryptWithKeyRolling(byte[] cipherBytes, String keyId) {
        boolean success = true;
        byte[] plainBytes = null;
        do {

            if (cipherBytes.length < PADDIN_VERSION_SIZE * 2) {
                success = false;
                break;
            }

            byte versionByte = cipherBytes[0];
            int exactVersion = getExactVersion(versionByte, keyId);
            if (exactVersion <= 0) {
                success = false;
                break;
            }
            LocalConfigLoader.KeyConfig config = getKeyConfig(keyId, exactVersion);

            plainBytes = simpleDecrypt(Arrays.copyOfRange(cipherBytes, 1, cipherBytes.length), config);
            if (plainBytes == null || plainBytes.length < PADDIN_VERSION_SIZE) {
                success = false;
                break;
            }
            byte versionInPlain = plainBytes[0];
            if (versionInPlain != versionByte) {
                success = false;
                break;
            }

        } while (false);
        return Arrays.copyOfRange(plainBytes, PADDIN_VERSION_SIZE, plainBytes.length);
    }

    private byte[] simpleDecrypt(byte[] cipherBytes, LocalConfigLoader.KeyConfig config) {
        try {
            String transformation = config.getAlgorithm() + "/" + config.getMode() + "/" + config.getPadding();
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keySpec = new SecretKeySpec(config.getKey().getBytes(), config.getAlgorithm());
            if (StringUtils.isNotBlank(config.getIv())) {
                IvParameterSpec ivSpec = new IvParameterSpec(config.getIv().getBytes());
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
            }
            return cipher.doFinal(cipherBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Config error！", e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("decrypt error!", e);
        }
    }

}
