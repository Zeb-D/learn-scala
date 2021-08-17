package com.yd.scala.hello.sdk;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author created by Zeb灬D
 */
@Slf4j
public class SdkManager<T> {
    private static Set<Class<? extends Annotation>> supportAnnotation = new HashSet() {{
        add(SdkHttp.class); //多次二次扩展
    }};

    private static ThreadLocal<SdkContext> CONTEXT_LOCAL = new InheritableThreadLocal<SdkContext>() {
        @Override
        protected SdkContext initialValue() {
            return new SdkContext();
        }
    };

    public static void setContext(SdkContext context) {
        CONTEXT_LOCAL.set(context);
    }

    public static <T> Object newProxyInstance(Class<T> clazz) {
        System.out.println(clazz.isInterface());
        //判断下，方法是否有支持的注解；
        Arrays.stream(clazz.getMethods())
                .filter(method ->
                        supportAnnotation.stream()
                                .filter(a -> method.isAnnotationPresent(a))
                                .findAny().isPresent())
                .findAny().orElseThrow(() -> new IllegalArgumentException("supportAnnotation not support"));

        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                (proxy, method, args) -> doMethodInvoke(proxy, method, args));
    }

    public static Object doMethodInvoke(Object proxy, Method method, Object[] args) throws IOException {
        //对method反射下
        if (!supportAnnotation.stream()
                .filter(a -> method.isAnnotationPresent(a))
                .findAny().isPresent()) {
            return proxy.getClass().getName() + "#" + method.getName() + " Object not Annotation";
        }

        SdkHttp sdkHttp = method.getAnnotation(SdkHttp.class);
        CONTEXT_LOCAL.get().setPath(sdkHttp.path());
        CONTEXT_LOCAL.get().setMethod(sdkHttp.method());

        Request rawRequest = encode(CONTEXT_LOCAL.get(), args);
        OkHttpClient client = buildHttpClient(new HttpConfig());
        Response resp = client.newCall(rawRequest).execute();

        //该死的泛型
        return JSONObject.parseObject(new String(resp.body().bytes()), method.getGenericReturnType());
    }

    public static OkHttpClient buildHttpClient(HttpConfig config) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(config.connectionTimeout, TimeUnit.SECONDS);
        client.setReadTimeout(config.readTimeout, TimeUnit.SECONDS);
        client.setWriteTimeout(config.writeTimeout, TimeUnit.SECONDS);
        client.setRetryOnConnectionFailure(true);
        client.getDispatcher().setMaxRequests(config.threads);
        return client;
    }

    public static Request encode(SdkContext context, Object[] args) {
        // 构建Request对象
        Request.Builder rb = new Request.Builder();
        //添加请求头
        rb.header("User-Agent", "yd/1.0");
//        rb.header("X-Request-Id", context.getRequestId());
        context.getHeaders().forEach((key, value) -> {
            rb.header(key, value);
        });
        //解析请求路径
        String path = context.getPath();

        return rb.url(buildUrl(context.getMethod().name(), context.getHost(), path, null))
                .method(context.getMethod().name(), null)
                .build();
    }

    public static String buildUrl(String method, String host, String path, String query) {
        StringBuilder sb = new StringBuilder(host);
        sb.append(path);
        if (StringUtils.isNotEmpty(query)) {
            if (path.contains("?")) {
                sb.append("&").append(query);
            } else {
                sb.append("?").append(query);
            }
        }
        return sb.toString();
    }

}
