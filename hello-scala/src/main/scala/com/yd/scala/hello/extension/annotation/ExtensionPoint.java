package com.yd.scala.hello.extension.annotation;


import java.lang.annotation.*;

/**
 *
 * 安防扩展点规约：
 *
 * 1、所有扩展点必须采用dealer进行严格隔离，换句话说扩展原函数必须包含dealerId参数
 *
 * 2、重写扩展返回值必须与源函数返回值类型保持一致，
 *    返回值类型参考ExtensionResult#returnType，
 *    前置/后置扩展返回值无效
 *
 * 3、在扩展实现方法中调用安防接口，必须检查数据库事务状态
 *
 * 4、严禁在扩展实现方法中进行耗时操作
 *
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtensionPoint {

    /**
     * 扩展点名称(必须全局唯一)
     *
     */
    String name();
}
