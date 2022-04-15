package com.yd.scala.hello.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Ser implements Serializable {
    private static final long serialVersionUID = 1L;
    public int num = 911;

    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.obj"));
            Ser ser = new Ser();
            oos.writeObject(ser);//序列化关键函数
            oos.flush();  //缓冲流
            oos.close(); //关闭流
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}