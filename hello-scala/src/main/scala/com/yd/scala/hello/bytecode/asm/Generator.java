package com.yd.scala.hello.bytecode.asm;

import com.yd.scala.hello.bytecode.Base;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 为 了 利 用 ASM 实 现 AOP， 需 要 定 义 两 个 类:
 * 一 个 是 MyClassVisitor 类，用于对字节码的 visit 以及修改;
 * 另一个是 Generator 类，在这个类中定义 ClassReader 和 ClassWriter，其中的逻辑是，classReader 读取字节码，
 * 然后交 给 MyClassVisitor 类处理，处理完成后由 ClassWriter 写字节码并将旧的字节码 替换掉。
 * Generator 类较简单，我们先看一下它的实现，如下所示，然后重点解释 MyClassVisitor 类。
 *
 * @author created by Zeb灬D on 2021-11-16 15:05
 */
public class Generator {
    public static void main(String[] args) throws IOException {
        // 读取
        ClassReader classReader = new ClassReader("com/yd/scala/hello/bytecode/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        String currentPath = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        // 输出
        File f = new File(currentPath + "/com/yd/scala/hello/bytecode/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");

    }
    //详细解读其中的代码，对字节码做修改 的步骤是:
    //● 首先通过 MyClassVisitor 类中的 visitMethod 方法，判断当前字节码读到 哪一个方法了。
    // 跳过构造方法 <init> 后，将需要被增强的方法交给内部类 MyMethodVisitor 来进行处理。
    //● 接下来，进入内部类 MyMethodVisitor 中的 visitCode 方法，它会在 ASM 开始访问某一个方法的 Code 区时被调用，
    // 重写 visitCode 方法，将 AOP 中 的前置逻辑就放在这里。
    //● MyMethodVisitor 继续读取字节码指令，每当 ASM 访问到无参数指令时，都 会调用 MyMethodVisitor 中的 visitInsn 方法。
    // 我们判断了当前指令是否为无 参数的“return”指令，如果是就在它的前面添加一些指令，也就是将 AOP
    // 的后置逻辑放在该方法中。
    //● 综上，重写 MyMethodVisitor 中的两个方法，就可以实现 AOP 了，而
    //重写方法时就需要用 ASM 的写法，手动写入或者修改字节码。通过调用 methodVisitor 的 visitXXXXInsn() 方法就可以实现字节码的插入，
    // XXXX 对 应相应的操作码助记符类型，比如 mv.visitLdcInsn(“end”) 对应的操作码就 是 ldc“end”，即将字符串“end”压入栈。
    //完成这两个 visitor 类后，运行 Generator 中的 main 方法完成对 Base 类的字 节码增强，
    // 增强后的结果可以在编译后的 target 文件夹中找到 Base.class 文件进行 查看，可以看到反编译后的代码已经改变了(如图 18 左侧所示)。
}
