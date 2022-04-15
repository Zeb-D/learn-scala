package com.yd.scala.hello.bytecode.Javassist;

import com.yd.scala.hello.bytecode.Base;
import javassist.*;

import java.io.IOException;

/**
 * 利用 Javassist 实现字节码增强时，可以无须关注字节码刻板的结构，其优点就 在于编程简单。
 * 直接使用 java 编码的形式，而不需要了解虚拟机指令，就能动态改 变类的结构或者动态生成类。其中最重要的是 ClassPool、CtClass、CtMethod、
 * <p>
 * CtField 这四个类:
 * ● CtClass(compile-time class):编译时类信息，它是一个 class 文件在代 码中的抽象表现形式，可以通过一个类的全限定名来获取一个 CtClass 对象， 用来表示这个类文件。
 * ● ClassPool:从开发视角来看，ClassPool 是一张保存 CtClass 信息的 HashTable，key 为类名，value 为类名对应的 CtClass 对象。
 * 当我们需要 对某个类进行修改时，就是通过 pool.getCtClass(“className”) 方法从 pool 中获取到相应的 CtClass。
 * ● CtMethod、CtField:这两个比较好理解，对应的是类中的方法和属性。
 *
 * @author created by Zeb灬D on 2021-11-16 16:01
 */
public class JavassistTest {
    public static void main(String[] args) throws NotFoundException,
            CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.yd.scala.hello.bytecode.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("./");
        Base h = (Base) c.newInstance();
        h.process();
    }
}
