package com.yd.scala.hello;

import com.yd.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 研究spring 我们写了个路径，为什么能找到对应的class？
 * 原理比较简单，先把 匹配路径转换成系统路径，然后遍历list找到所有的class
 *
 * @author created by Zeb灬D on 2021-08-14 15:31
 */
public class ResourceTest {
    public static void main(String[] args) throws IOException {
        //通过路径来看看到底有多少class
        Enumeration<URL> resourceUrls = Thread.currentThread().getContextClassLoader().getResources("com/yd/scala/hello/");
        int i = 0;
        while (resourceUrls.hasMoreElements()) {
            URL url = resourceUrls.nextElement();
            System.out.println(url.getClass());
            System.out.println(url);
            File f = new File(url.getPath());
            System.out.println(JSONObject.toJSONString(f.list()));
            i++;
        }
        System.out.println("多少个" + i);
    }
}
