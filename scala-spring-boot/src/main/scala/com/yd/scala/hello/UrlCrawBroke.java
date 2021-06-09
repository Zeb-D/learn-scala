package com.yd.scala.hello;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 如果设置了自定义域名，将博客域名前缀填写入19行的变量userId中，点击运行
 */
public class UrlCrawBroke {
    static int maxPages = 20; // 填写你的博客查找页数
    static String userId = "hanquan";// 这里填入主页名称：例如主业为 https://hanquan.blog.csdn.net/ 则填入 hanquan 即可
    static final String homeUrl = "https://zeb-d.blog.csdn.net/article/list/";

    static List<String> urlSet = new ArrayList<>();

    public static void getUrls() throws IOException, InterruptedException {
        InputStream is;
        String pageStr;
        StringBuilder curUrl = null;
        for (int i = 1; i < maxPages; i++) {
            Thread.sleep(500);
            System.out.println("正在查找第 " + i + " 页中的博客地址");
            curUrl = new StringBuilder(homeUrl);
            curUrl.append(i);
            System.out.println(curUrl);
            is = doGet(curUrl.toString());
            pageStr = inputStreamToString(is, "UTF-8");// 一整页的html源码

            List<String> list = getMatherSubstrs(pageStr, "(?<=href=\")https://zeb-d.blog.csdn.net/article/details/[0-9]{8,9}(?=\")");
            urlSet.addAll(list);
            //System.out.println("加入 " + list.size() + " 个url");
        }
    }

    public static List<String> getSubUrl(String url) throws IOException {
        InputStream is = doGet(url);
        String pageStr = inputStreamToString(is, "UTF-8");// 一整页的html源码
        return getMatherSubstrs(pageStr, "(?<=href=\")https://zeb-d.blog.csdn.net/article/details/[0-9]{8,9}(?=\")");
    }

    public static void main(String urlstr[]) throws IOException, InterruptedException {
        // ----------------------------------------------遍历每一页 获取文章链接----------------------------------------------
//        for (String s : getSubUrl("https://blog.csdn.net/u014229282/category_10853648.html")) {
//            System.out.println(s);
//        }


//        getUrls();
//        System.out.println(radomIp());
//
//        // ---------------------------------------------------打印每个链接---------------------------------------------------
//        System.out.println("打印每个链接" + "数量：" + urlSet.size());
        urlSet = getSubUrl("https://blog.csdn.net/u014229282/category_10853648.html");
//        for (String s : urlSet) {
//            System.out.println(s);
//        }
//        System.out.println("打印每个链接完毕");
//
//        // ---------------------------------------------------多线程访问每个链接---------------------------------------------------
        ExecutorService executor = Executors.newCachedThreadPool();
        int threadCount = 5; // 并发线程数量
        for (int i = 0; i < threadCount; i++) {
            executor.execute(new MyThread(new HashSet<>(urlSet)));
        }
        executor.shutdown();
    }

    public static void gStrs(Collection<String> urlSet) {
        for (String s : urlSet) {
            System.out.println(gStr(s));
        }
    }

    public static String gStr(String url) {
        String s = "curl -IL  -H \"user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome\"  -H \"X-Forwarded-For:%s\" -H \"X-Real-IP:%s\" " + url;
        s = String.format(s, radomIp(), radomIp());
        return s;
    }

    public static String gIp(String key) {
        return key + ":" + radomIp();
    }

    private static final Random r = new Random();

    public static String radomIp() {
        int a = r.nextInt(255);
        int b = r.nextInt(255);
        int c = r.nextInt(255);
        int d = r.nextInt(255);
        return a + "." + b + "." + c + "." + d;
    }

    public static InputStream doGet(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }

    public static String inputStreamToString(InputStream is, String charset) throws IOException {
        byte[] bytes = new byte[1024];
        int byteLength = 0;
        StringBuffer sb = new StringBuffer();
        while ((byteLength = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, byteLength, charset));
        }
        return sb.toString();
    }

    // 正则匹配
    public static List<String> getMatherSubstrs(String str, String regex) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
}

class MyThread implements Runnable {
    public List<String> urlList;

    public MyThread(Set<String> urls) {
        List list = new ArrayList(urls);
        Collections.shuffle(list);
        this.urlList = list;
    }

    @Override
    public void run() {
        int i = 0;
        for (String s : urlList) {
            try {
                doGet(s);
                System.out.println(Thread.currentThread().getName() + "成功访问第" + (++i) + "个链接,共" + urlList.size() + "个:" + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static InputStream doGet(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }
}