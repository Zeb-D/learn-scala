package com.yd.scala.hello.serializable;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeSer {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("object.obj");
        byte[] b = new byte[(int) fis.getChannel().size()];
        fis.read(b);
        System.out.println(b.length);
        for (int i = 0; i < b.length; i++) {
            System.out.println(String.format("%02x", b[i]));
        }
        fis.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(b));
        Ser s = (Ser) ois.readObject();
        System.out.println(s.num);
        ois.close();
    }
}