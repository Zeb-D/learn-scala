package com.yd.test.graphql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author created by Zeb灬D on 2021-09-15 14:20
 */
@ToString(callSuper = true)
public class YamlCommonVO {
    @Data
    @ToString(callSuper = true)
    @AllArgsConstructor
    public static class SdkComService {
        private Composition compositionService;
    }

    @Data
    @ToString(callSuper = true)
    @AllArgsConstructor
    public static class Composition {
        private String id;
        private String service;
        private String method;
        private Variables variables;
        private List<Resolver> resolvers;
    }

    @Data
    @ToString(callSuper = true)
    @AllArgsConstructor
    public static class Variables {
        private String clientId;
        private String appVersion;
    }

    @Data
    @ToString(callSuper = true)
    @AllArgsConstructor
    public static class Resolver {
        private String refid;
        private List<Field> fields;
    }

    @Data
    @ToString(callSuper = true)
    @AllArgsConstructor
    public static class Field {
        private String field;
    }

}
