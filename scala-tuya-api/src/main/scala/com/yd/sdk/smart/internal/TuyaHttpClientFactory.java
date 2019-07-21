package com.yd.sdk.smart.internal;

import com.yd.sdk.smart.config.ClientConfig;
import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class TuyaHttpClientFactory {

    protected static volatile TuyaHttpClientFactory instance;

	protected static RequestConfig requestConfig;

	protected PoolingHttpClientConnectionManager connManager;

	protected static Charset defaultEncoding = Consts.UTF_8;

	protected Registry<ConnectionSocketFactory> registry;

    private TuyaHttpClientFactory() {
		super();
	}

	public static TuyaHttpClientFactory getInstance() {
		synchronized (TuyaHttpClientFactory.class) {
			if (TuyaHttpClientFactory.instance == null) {
				instance = new TuyaHttpClientFactory();
			}
			return instance;
		}
	}

    public CloseableHttpClient getDefaultClient(final ClientConfig clientConfig) {

        // 设置连接参数
		ConnectionConfig connConfig = ConnectionConfig
            .custom()
            .setCharset(defaultEncoding)
            .build();

		SocketConfig socketConfig = SocketConfig
            .custom()
            .setSoTimeout(clientConfig.getSocketTimeout())
            .build();

		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder
				.<ConnectionSocketFactory> create();

		ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSF);
		// 指定信任密钥存储对象和连接套接字工厂
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			SSLContext sslContext = SSLContexts.custom().useTLS()
					.loadTrustMaterial(trustStore, new TrustStrategy() {
						public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
							return true;
						}
					})
					.build();
			LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(
					sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			registryBuilder.register("https", sslSF);

		} catch (KeyStoreException e) {
			throw new RuntimeException(e);
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		registry = registryBuilder.build();
		// 设置连接管理器
		connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setDefaultConnectionConfig(connConfig);
		connManager.setDefaultSocketConfig(socketConfig);
		connManager.setMaxTotal(clientConfig.getMaxConnections());
		connManager.setDefaultMaxPerRoute(25);

		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				if (executionCount >= clientConfig.getMaxErrorRetry() ) {
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					return false;
				}
				if (exception instanceof UnknownHostException) {
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {
					return false;
				}
				if (exception instanceof SSLException) {
					return false;
				}
				HttpClientContext clientContext = HttpClientContext
						.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				if (idempotent) {
					// 如果请求是幂等的，就再次尝试
					return true;
				}
				return false;
			}
		};

        requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(clientConfig.getConnectionTimeout())
            .setConnectTimeout(clientConfig.getConnectionTimeout())
            .setSocketTimeout(clientConfig.getSocketTimeout())
            .build();

		// 指定cookie存储对象
		BasicCookieStore cookieStore = new BasicCookieStore();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setConnectionManager(connManager)
            .setRetryHandler(myRetryHandler)
            .setDefaultCookieStore(cookieStore).build();

        return httpClient;
    }

}
