package com.yd;

import java.util.HashMap;

import static java.util.stream.Collectors.joining;

/**
 * @author created by Zeb灬D on 2021-01-16 17:28
 */
public class Test {

    @org.junit.Test
    public void Test() {
        HashMap<String, Object>  h = new HashMap();
        h.put("a",1);
        h.put("b",2);
        String str = h.entrySet().stream()
                .map(t ->  t.getKey() + "=" + t.getValue())
                .collect(joining(","));
        System.out.println(str);
    }

}
