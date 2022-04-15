package com.yd.scala.hello.editor.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.MethodCallback;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ApolloProcessor implements BeanPostProcessor, PriorityOrdered {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Iterator var4 = this.findAllField(clazz).iterator();

        while (var4.hasNext()) {
            Field field = (Field) var4.next();
            this.processField(bean, beanName, field);
        }

        var4 = this.findAllMethod(clazz).iterator();

        while (var4.hasNext()) {
            Method method = (Method) var4.next();
            this.processMethod(bean, beanName, method);
        }

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    protected abstract void processField(Object var1, String var2, Field var3);

    protected abstract void processMethod(Object var1, String var2, Method var3);

    public int getOrder() {
        return 2147483647;
    }

    private List<Field> findAllField(Class clazz) {
        final List<Field> res = new LinkedList();
        ReflectionUtils.doWithFields(clazz, new FieldCallback() {
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                res.add(field);
            }
        });
        return res;
    }

    private List<Method> findAllMethod(Class clazz) {
        final List<Method> res = new LinkedList();
        ReflectionUtils.doWithMethods(clazz, new MethodCallback() {
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                res.add(method);
            }
        });
        return res;
    }
}