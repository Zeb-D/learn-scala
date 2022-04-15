package com.yd.scala.hello.handler.protocol;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Protocols {
    private static final Map<String, Protocol> PROTOCOL_MAP = new ConcurrentHashMap<>();

    private static Protocol DEFAULT_PROTOCOL = new DefaultProtocol();

    public static void put(String id, Protocol protocol) {
        PROTOCOL_MAP.put(id, protocol);
    }

    public static Protocol get(String id) {
        return PROTOCOL_MAP.get(id);
    }

    public static Protocol getDefaultProtocol() {
        return DEFAULT_PROTOCOL;
    }

    public static void setDefaultProtocol(Protocol protocol) {
        DEFAULT_PROTOCOL = protocol;
    }

    public static Protocol getProtocol(String pathProtocol, String clusterProtocol) {
        if (StringUtils.isNotBlank(pathProtocol)) {
            return getProtocolOrThowException(pathProtocol);
        }

        if (StringUtils.isNotBlank(clusterProtocol)) {
            return getProtocolOrThowException(clusterProtocol);
        }

        return DEFAULT_PROTOCOL;
    }

    public static Protocol getProtocol(String pathProtocol, Protocol clusterProtocol) {
        if (StringUtils.isNotBlank(pathProtocol)) {
            return getProtocolOrThowException(pathProtocol);
        }

        if (clusterProtocol != null) {
            return clusterProtocol;
        }

        return DEFAULT_PROTOCOL;
    }

    private static Protocol getProtocolOrThowException(String protocolId) {
        Protocol protocol = PROTOCOL_MAP.get(protocolId);
        if (protocol == null) {
            throw new IllegalArgumentException(protocolId);
        }
        return protocol;
    }
}
