package com.yd.api.config;

public class ClientConfig {


    /** 客户端读写超时 */
    private int socketTimeout = 50000;

    /** 客户端连接超时 */
    private int connectionTimeout = 50000;

    /** 客户端最大重试次数 */
    private int maxErrorRetry = 3;

    /** 最大连接数 */
    private int maxConnections = 50;
    
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout=socketTimeout;
    }
    public int getSocketTimeout() {
        return this.socketTimeout;
    }


    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout=connectionTimeout;
    }
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }


    public void setMaxErrorRetry(int maxErrorRetry) {
        this.maxErrorRetry=maxErrorRetry;
    }
    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections=maxConnections;
    }
    public int getMaxConnections() {
        return this.maxConnections;
    }

}
