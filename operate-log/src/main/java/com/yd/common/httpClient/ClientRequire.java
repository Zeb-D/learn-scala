package com.yd.common.httpClient;

public class ClientRequire {
    public ClientRequire() {
    }

    public boolean relogin(ClientResult result) {
        return result.getStatus().getCode() == 401;
    }
}