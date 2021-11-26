package com.yd.scala.classmexer.instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;


/**
 * instrument 是 JVM 提供的一个可以修改已加载类的类库，专门为 Java 语言编 写的插桩服务提供支持。
 * 它需要依赖 JVMTI 的 Attach API 机制实现，JVMTI 这一 部分，我们将在下一小节进行介绍。
 * 在 JDK 1.6 以前，instrument 只能在 JVM 刚 启动开始加载类时生效，而在 JDK 1.6 之后，instrument 支持了在运行时对类定 义的修改。
 * 要使用 instrument 的类修改功能，我们需要实现它提供的 ClassFileT- ransformer 接口，定义一个类文件转换器。接口中的 transform() 方法会在类文件 被加载时调用，
 * 而在 transform 方法里，我们可以利用上文中的 ASM 或 Javassist 对传入的字节码进行改写或替换，生成新的字节码数组后返回。
 */
public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming " + className);
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.yd.scala.classmexer.instrument.Base");
            cc.freeze();
            cc.defrost();
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}