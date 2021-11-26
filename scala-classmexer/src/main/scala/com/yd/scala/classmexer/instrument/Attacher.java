package com.yd.scala.classmexer.instrument;

import com.sun.tools.attach.*;

import java.io.File;
import java.io.IOException;

/**
 * 执行这段代码前，请先打个包括TestAgent.class的jar，使用类似arthas机制：
 * Attach API 的作用是提供 JVM 进程间通信的能力，比如说我们为了让另外一 个 JVM 进程把线上服务的线程 Dump 出来，
 * 会运行 jstack 或 jmap 的进程，并传 递 pid 的参数，告诉它要对哪个进程进行线程 Dump，这就是 Attach API 做的事情。
 * 在下面，我们将通过 Attach API 的 loadAgent() 方法，将打包好的 Agent jar 包动 态 Attach 到目标 JVM 上。具体实现起来的步骤如下:
 * ● 定义 Agent，并在其中实现 AgentMain 方法，如上一小节中定义的代码块 7 中的 TestAgent 类;
 * ● 然后将 TestAgent 类打成一个包含 MANIFEST.MF 的 jar 包，其中 MANI- FEST.MF 文件中将 Agent-Class 属性指定为 TestAgent 的全限定名
 * ● 最后利用 Attach API，将我们打包好的 jar 包 Attach 到指定的 JVM pid 上
 *
 * @author created by Zeb灬D on 2021-11-16 17:14
 */
public class Attacher {
    //先执行mvn 命令打个scala-classmexer模块包；
    //再执行Base一直跑着
    //再填入这里的值
    public static void main(String[] args) throws
            AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("83753");
//        vm.detach();
        String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        File file = new File(path);
        path = file.getParent();
        vm.loadAgent(path + "/scala-classmexer.jar");
    }


    //参考来源：https://github.com/alibaba/arthas/blob/master/core/src/main/java/com/taobao/arthas/core/Arthas.java#attachAgent
    private void attachAgent(String javaPid, String arthasAgentPath) throws Exception {
        VirtualMachineDescriptor virtualMachineDescriptor = null;
        // 拿到所有JVM进程，找出目标进程
        for (VirtualMachineDescriptor descriptor : VirtualMachine.list()) {
            String pid = descriptor.id();
            if (pid.equals(javaPid)) {
                virtualMachineDescriptor = descriptor;
            }
        }
        VirtualMachine virtualMachine = null;
        try {
            // 针对某个JVM进程调用VirtualMachine.attach()方法，拿到 VirtualMachine 实例
            if (null == virtualMachineDescriptor) {
                virtualMachine = VirtualMachine.attach("" + javaPid);
            } else {
                virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
            }
            // ...
            // 调用VirtualMachine#loadAgent()，将arthasAgentPath指定的jar attach 到目标 JVM进程中
            // 第二个参数为attach参数，即agentmain的首个String参数args
            //virtualMachine.loadAgent(arthasAgentPath, configure.getArthasCore() + ";" + configure.toString());
            virtualMachine.loadAgent(arthasAgentPath, "{params}");
        } finally {
            if (null != virtualMachine) {
                // 调用VirtualMachine#detach()释放
                virtualMachine.detach();
            }
        }
    }
}
