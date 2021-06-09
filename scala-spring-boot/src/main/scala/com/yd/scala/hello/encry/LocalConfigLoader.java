package com.yd.scala.hello.encry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

public class LocalConfigLoader {

    private static Map<String, Map<Integer, KeyConfig>> KEY_CONFIG;

    static {
        String s = "{\n" +
                "    \"smart\": {\n" +
                "        \"1\": {\n" +
                "            \"version\": 1,\n" +
                "            \"key\": \"sdfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"jkps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"test\": {\n" +
                "        \"1\": {\n" +
                "            \"version\": 1,\n" +
                "            \"key\": \"sdfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"jkps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"2\": {\n" +
                "            \"version\": 2,\n" +
                "            \"key\": \"09832904nc@#$xua\",\n" +
                "            \"iv\": \"sdfsdf12dfsdfs#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"3\": {\n" +
                "            \"version\": 3,\n" +
                "            \"key\": \"3dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"3kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"4\": {\n" +
                "            \"version\": 4,\n" +
                "            \"key\": \"4dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"4kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"5\": {\n" +
                "            \"version\": 5,\n" +
                "            \"key\": \"5dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"5kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"6\": {\n" +
                "            \"version\": 6,\n" +
                "            \"key\": \"6dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"6kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"7\": {\n" +
                "            \"version\": 7,\n" +
                "            \"key\": \"7dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"7kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"8\": {\n" +
                "            \"version\": 8,\n" +
                "            \"key\": \"8dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"8kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"9\": {\n" +
                "            \"version\": 9,\n" +
                "            \"key\": \"9dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"9kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"10\": {\n" +
                "            \"version\": 10,\n" +
                "            \"key\": \"10fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"10ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"11\": {\n" +
                "            \"version\": 11,\n" +
                "            \"key\": \"11fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"11ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"12\": {\n" +
                "            \"version\": 12,\n" +
                "            \"key\": \"12fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"12ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"13\": {\n" +
                "            \"version\": 13,\n" +
                "            \"key\": \"13fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"13ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"14\": {\n" +
                "            \"version\": 14,\n" +
                "            \"key\": \"14fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"14ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"15\": {\n" +
                "            \"version\": 15,\n" +
                "            \"key\": \"15fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"15ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"16\": {\n" +
                "            \"version\": 16,\n" +
                "            \"key\": \"16fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"16ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"17\": {\n" +
                "            \"version\": 17,\n" +
                "            \"key\": \"17fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"17ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        }\n" +
                "        },\n" +
                "        \"test1\": {\n" +
                "        \"1\": {\n" +
                "            \"version\": 1,\n" +
                "            \"key\": \"sdfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"jkps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"2\": {\n" +
                "            \"version\": 2,\n" +
                "            \"key\": \"09832904nc@#$xua\",\n" +
                "            \"iv\": \"sdfsdf12dfsdfs#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"3\": {\n" +
                "            \"version\": 3,\n" +
                "            \"key\": \"3dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"3kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"4\": {\n" +
                "            \"version\": 4,\n" +
                "            \"key\": \"4dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"4kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"5\": {\n" +
                "            \"version\": 5,\n" +
                "            \"key\": \"5dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"5kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"6\": {\n" +
                "            \"version\": 6,\n" +
                "            \"key\": \"6dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"6kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"7\": {\n" +
                "            \"version\": 7,\n" +
                "            \"key\": \"7dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"7kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"8\": {\n" +
                "            \"version\": 8,\n" +
                "            \"key\": \"8dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"8kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"9\": {\n" +
                "            \"version\": 9,\n" +
                "            \"key\": \"9dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"9kps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"10\": {\n" +
                "            \"version\": 10,\n" +
                "            \"key\": \"10fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"10ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"11\": {\n" +
                "            \"version\": 11,\n" +
                "            \"key\": \"11fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"11ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"12\": {\n" +
                "            \"version\": 12,\n" +
                "            \"key\": \"12fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"12ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"13\": {\n" +
                "            \"version\": 13,\n" +
                "            \"key\": \"13fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"13ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"14\": {\n" +
                "            \"version\": 14,\n" +
                "            \"key\": \"14fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"14ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"15\": {\n" +
                "            \"version\": 15,\n" +
                "            \"key\": \"15fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"15ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"16\": {\n" +
                "            \"version\": 16,\n" +
                "            \"key\": \"16fsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"16ps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"test2\": {\n" +
                "        \"255\": {\n" +
                "            \"version\": 255,\n" +
                "            \"key\": \"5dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"jkps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"256\": {\n" +
                "            \"version\": 256,\n" +
                "            \"key\": \"6dfsdfsadfsdfs#!\",\n" +
                "            \"iv\": \"jkps+_!~%^^*()#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"CFB\",\n" +
                "            \"padding\":\"NOPADDING\"\n" +
                "        },\n" +
                "        \"257\": {\n" +
                "            \"version\": 257,\n" +
                "            \"key\": \"7dfsdfsadfsdfs#!\",\n" +
                "            \"algorithm\": \"AES\",\n" +
                "            \"mode\":\"ECB\",\n" +
                "            \"padding\":\"PKCS5Padding\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        KEY_CONFIG = JSON.parseObject(s, new TypeReference<Map<String, Map<Integer, KeyConfig>>>() {
        });
    }

    public Map<Integer, KeyConfig> getConfigs(String keyId) {
        return KEY_CONFIG.get(keyId);
    }

    public class KeyConfig {
        private Integer version;
        private String key;
        private String iv;
        private String algorithm;
        private String mode;
        private String padding;

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getIv() {
            return iv;
        }

        public void setIv(String iv) {
            this.iv = iv;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getPadding() {
            return padding;
        }

        public void setPadding(String padding) {
            this.padding = padding;
        }

    }

}
