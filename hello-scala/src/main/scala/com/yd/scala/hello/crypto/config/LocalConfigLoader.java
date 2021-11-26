package com.yd.scala.hello.crypto.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

public class LocalConfigLoader implements IConfigLoader {
    private static Map<String, Map<Integer, KeyConfig>> KEY_CONFIG;

    public Map<Integer, KeyConfig> getConfigs(String keyId) {
        return KEY_CONFIG.get(keyId);
    }

    @Override
    public String getDefaultKey() {
        return "test";
    }

    @Override
    public String getRegionSyncKey() {
        return "regionSync";
    }

    static {
        ClassPathResource resource = new ClassPathResource("/encryption/encryption.json");

        try {
            InputStream is = resource.getInputStream();
            Throwable var2 = null;

            try {
                StringBuilder sb = new StringBuilder();
                Iterator var4 = IOUtils.readLines(is, Charset.forName("UTF-8")).iterator();

                while (true) {
                    if (!var4.hasNext()) {
                        KEY_CONFIG = JSON.parseObject(sb.toString(),
                                new TypeReference<Map<String, Map<Integer, KeyConfig>>>() {
                                });
                        break;
                    }

                    String line = (String) var4.next();
                    sb.append(line).append("\n");
                }
            } catch (Throwable var14) {
                var2 = var14;
                throw var14;
            } finally {
                if (is != null) {
                    if (var2 != null) {
                        try {
                            is.close();
                        } catch (Throwable var13) {
                            var2.addSuppressed(var13);
                        }
                    } else {
                        is.close();
                    }
                }

            }

        } catch (Exception var16) {
            throw new RuntimeException("Init encryption config error!", var16);
        }
    }
}