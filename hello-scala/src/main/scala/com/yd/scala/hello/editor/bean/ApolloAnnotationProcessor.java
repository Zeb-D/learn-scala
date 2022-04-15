package com.yd.scala.hello.editor.bean;

import com.ctrip.framework.apollo.Config;
import com.google.common.base.Preconditions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Apollo Annotation Processor for Spring Application
 *
 * @author Jason Song(song_s@ctrip.com)
 */
public class ApolloAnnotationProcessor extends ApolloProcessor {

    @Override
    protected void processField(Object bean, String beanName, Field field) {
        ApolloConfig annotation = AnnotationUtils.getAnnotation(field, ApolloConfig.class);
        if (annotation == null) {
            return;
        }

        Preconditions.checkArgument(Config.class.isAssignableFrom(field.getType()),
                "Invalid type: %s for field: %s, should be Config", field.getType(), field);

        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, bean, "123");
    }

    @Override
    protected void processMethod(final Object bean, String beanName, final Method method) {

    }
}