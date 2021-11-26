package com.yd.http.testing.test;

import com.github.houbb.markdown.toc.util.FileUtil;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author created by Zeb灬D on 2020-11-19 14:30
 */
public class JSONTest {
    public static void main(String[] args) throws IOException {
        String str = fileToString("/Users/zouyongdong/IdeaProjects/my/learn-scala/scala-http-test/src/main/scala/com/yd/http/testing/test.json");
        System.out.println(str);

    }

    public static String fileToString(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        System.out.println( inputStream.available());
        String charset = "UTF-8";
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        List<String> res = FileUtil.getFileContentEachLine(inputStream, 0);

        return res.stream().collect(Collectors.joining());
    }
}
