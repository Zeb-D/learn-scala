package com.yd.scala.hello.crypto.config;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApolloConfigLoader implements IConfigLoader {
    private static final Map<String, Map<Integer, KeyConfig>> CONFIG_MAP = Maps.newHashMap();
    private String namespace;

    public ApolloConfigLoader() {
        this.namespace = SecretKey.DEFAULT.value; // 请关联下 nameSpace
    }

    public Map<Integer, KeyConfig> getConfigs(String keyId) {
        Map<Integer, KeyConfig> result = CONFIG_MAP.get(keyId);
        if (result == null) {
            this.getConfigsFromApollo(keyId);
            return CONFIG_MAP.get(keyId);
        } else {
            return result;
        }
    }

    @Override
    public String getDefaultKey() {
        return SecretKey.DEFAULT.value;
    }

    @Override
    public String getRegionSyncKey() {
        return SecretKey.REGION_SYNC.value;
    }

    public enum SecretKey {
        DEFAULT("encryption.secret"),
        REGION_SYNC("encryption.secret.regions.sync");

        String value;

        SecretKey(String value) {
            this.value = value;
        }
    }

    private void getConfigsFromApollo(String keyId) {
        Config config = ConfigService.getConfig(this.namespace);
        String configJson = config.getProperty(keyId, null);
        this.parseConfig(keyId, configJson);
        config.addChangeListener(changeEvent -> {
            Iterator var2 = changeEvent.changedKeys().iterator();

            while (var2.hasNext()) {
                String key = (String) var2.next();
                if (ApolloConfigLoader.CONFIG_MAP.containsKey(key)) {
                    ConfigChange change = changeEvent.getChange(key);
                    ApolloConfigLoader.this.parseConfig(key, change.getNewValue());
                }
            }

        });
    }

    private synchronized void parseConfig(String keyId, String content) {
        Map<Integer, KeyConfig> result = Maps.newHashMap();
        List<KeyConfig> configList = JSON.parseArray(content, KeyConfig.class);
        Iterator var5 = configList.iterator();

        while (var5.hasNext()) {
            KeyConfig c = (KeyConfig) var5.next();
            check(c);
            result.put(c.getVersion(), c);
        }

        CONFIG_MAP.put(keyId, result);
    }

    private static final Set<String> MODE_CANNOT_USE_UNCHANGING_IV = Sets.newHashSet(new String[]{"OFB", "CFB", "CTR", "GCM", "CCM"});

    public static void check(KeyConfig config) {
        if (StringUtils.isNotBlank(config.getIv()) && MODE_CANNOT_USE_UNCHANGING_IV.contains(config.getMode())) {
            String msg = "Mode cannot use unchanging IV!";
            throw new RuntimeException(msg);
        }
    }
}