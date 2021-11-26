package com.yd.scala.hello.crypto.handler;

import com.yd.scala.hello.crypto.config.IConfigLoader;
import com.yd.scala.hello.crypto.config.LocalConfigLoader;
import com.yd.scala.hello.crypto.encryption.EncryptionProvider;
import com.yd.scala.hello.crypto.encryption.IEncryptionProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.nio.charset.Charset;
import java.sql.*;

public abstract class EncryptedTypeHandler<T> extends BaseTypeHandler<T> {
    private static final Charset ENCODING_CHARSET = Charset.forName("UTF-8");
    protected IEncryptionProvider encryptionProvider = new EncryptionProvider(this.getConfigLoader(), this.useKeyRolling());
    private final static IConfigLoader defaultConfigLoader = new LocalConfigLoader();

    protected IConfigLoader getConfigLoader() {
        String encryptionLoaderClassName = System.getProperty("encryption.loader.className", LocalConfigLoader.class.getName());
        //class.forName
        return getConfigLoader(encryptionLoaderClassName);
    }

    private IConfigLoader getConfigLoader(String encryptionLoaderClassName) {
        try {
            Class loaderClass = Class.forName(encryptionLoaderClassName);
            if (IConfigLoader.class.isAssignableFrom(loaderClass)) {
                return (IConfigLoader) loaderClass.newInstance();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return defaultConfigLoader;
    }

    protected String getKeyId() {
        return encryptionProvider.getConfigLoader().getDefaultKey();
    }

    protected boolean useKeyRolling() {
        return true;
    }

    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        String param = String.valueOf(parameter);
        String encryptedParam = this.encrypt(param);
        ps.setString(i, encryptedParam);
    }

    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String encryptedValue = rs.getString(columnName);
        return this.decrypt(encryptedValue, columnName);
    }

    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String encryptedValue = rs.getString(columnIndex);
        ResultSetMetaData metaData = rs.getMetaData();
        String columnName;
        if (metaData == null) {
            columnName = "(null)";
        } else {
            columnName = metaData.getColumnName(columnIndex);
        }

        return this.decrypt(encryptedValue, columnName);
    }

    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String encryptedValue = cs.getString(columnIndex);
        ResultSetMetaData metaData = cs.getMetaData();
        String columnName;
        if (metaData == null) {
            columnName = "(null)";
        } else {
            columnName = metaData.getColumnName(columnIndex);
        }

        return this.decrypt(encryptedValue, columnName);
    }

    private String encrypt(String plainText) {
        if (StringUtils.isEmpty(plainText)) {
            return plainText == null ? null : "";
        } else {
            byte[] cipherBytes = this.encryptionProvider.encrypt(plainText.getBytes(), this.getKeyId());
            return Base64.encodeBase64String(cipherBytes);
        }
    }

    private T decrypt(String cipherText, String columnName) {
        if (StringUtils.isEmpty(cipherText)) {
            return this.getOriginalValue(cipherText);
        } else {
            byte[] cipherBytes = Base64.decodeBase64(cipherText);

            byte[] plainBytes;
            try {
                plainBytes = this.encryptionProvider.decrypt(cipherBytes, this.getKeyId());
            } catch (RuntimeException var6) {
                throw new RuntimeException("Decrypt error at column " + columnName + ",cipher text:" + cipherText, var6);
            }

            String decryptedValue = new String(plainBytes, ENCODING_CHARSET);
            return this.getOriginalValue(decryptedValue);
        }
    }

    protected abstract T getOriginalValue(String var1);
}