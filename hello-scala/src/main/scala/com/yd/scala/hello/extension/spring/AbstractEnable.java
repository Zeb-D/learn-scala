
package com.yd.scala.hello.extension.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractEnable {

    protected String[] getApplicationBasePackages(Environment environment, AnnotationMetadata metadata) {
        Set<AnnotationAttributes> componentScans = componentAttributesForRepeatable(metadata);
        Set<String> basePackages = new LinkedHashSet<>();
        for (AnnotationAttributes componentScan : componentScans) {
            String[] basePackagesArray = componentScan.getStringArray("basePackages");
            for (String pkg : basePackagesArray) {
                String[] tokenized = StringUtils.tokenizeToStringArray(environment.resolvePlaceholders(pkg),
                        ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
                Collections.addAll(basePackages, tokenized);
            }

            for (Class<?> clazz : componentScan.getClassArray("basePackageClasses")) {
                basePackages.add(ClassUtils.getPackageName(clazz));
            }
            if (basePackages.isEmpty()) {
                basePackages.add(ClassUtils.getPackageName(metadata.getClassName()));
            }
        }
        return StringUtils.toStringArray(basePackages);
    }

    protected String[] getAnnotationStringArrayAttribute(Environment environment, AnnotationMetadata metadata, String annotationName, String attributeName) {
        Set<AnnotationAttributes> annotationAttributes = new LinkedHashSet<>();
        addAttributesIfNotNull(annotationAttributes,
                metadata.getAnnotationAttributes(annotationName, false));
        Set<String> attributes = new LinkedHashSet<>();
        for (AnnotationAttributes componentScan : annotationAttributes) {
            String[] attributeArray = componentScan.getStringArray(attributeName);
            for (String pkg : attributeArray) {
                String[] tokenized = StringUtils.tokenizeToStringArray(environment.resolvePlaceholders(pkg),
                        ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
                Collections.addAll(attributes, tokenized);
            }
        }
        return StringUtils.toStringArray(attributes);
    }

    protected String getAnnotationStringAttribute(Environment environment, AnnotationMetadata metadata, String annotationName, String attributeName) {
        Set<AnnotationAttributes> annotationAttributes = new LinkedHashSet<>();
        addAttributesIfNotNull(annotationAttributes,
                metadata.getAnnotationAttributes(annotationName, false));
        String attribute = null;
        for (AnnotationAttributes componentScan : annotationAttributes) {
            attribute = componentScan.getString(attributeName);
            if (attribute != null) {
                attribute = environment.resolvePlaceholders(attribute);
                break;
            }
        }
        return attribute;
    }

    protected Class<?> getAnnotationClassAttribute(Environment environment, AnnotationMetadata metadata, String annotationName, String attributeName) {
        Set<AnnotationAttributes> annotationAttributes = new LinkedHashSet<>();
        addAttributesIfNotNull(annotationAttributes,
                metadata.getAnnotationAttributes(annotationName, false));
        Class<?> attribute = null;
        for (AnnotationAttributes componentScan : annotationAttributes) {
            attribute = componentScan.getClass(attributeName);
            if (attribute != null) {
                break;
            }
        }
        return attribute;
    }


    @SuppressWarnings("unchecked")
    protected Set<AnnotationAttributes> componentAttributesForRepeatable(AnnotationMetadata metadata) {
        Set<AnnotationAttributes> result = new LinkedHashSet<>();
        addAttributesIfNotNull(result, metadata.getAnnotationAttributes(ComponentScan.class.getName(), false));
        Map<String, Object> container = metadata.getAnnotationAttributes(ComponentScans.class.getName(), false);
        if (container != null && container.containsKey("value")) {
            for (Map<String, Object> containedAttributes : (Map<String, Object>[]) container.get("value")) {
                addAttributesIfNotNull(result, containedAttributes);
            }
        }
        return Collections.unmodifiableSet(result);
    }

    protected void addAttributesIfNotNull(Set<AnnotationAttributes> result, Map<String, Object> attributes) {
        if (attributes != null) {
            result.add(AnnotationAttributes.fromMap(attributes));
        }
    }
}
