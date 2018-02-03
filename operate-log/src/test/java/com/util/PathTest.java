package com.util;

import org.junit.Test;

import java.io.File;

/**
 * @author Yd on  2018-01-18
 * @Description：
 **/
public class PathTest {
    @Test
    public void testPath(){
        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径

        File directory = new File("");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        }catch(Exception e){}
    }
}
