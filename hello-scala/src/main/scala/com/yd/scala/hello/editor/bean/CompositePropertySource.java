package com.yd.scala.hello.editor.bean;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.util.*;

public class CompositePropertySource extends EnumerablePropertySource<Object> {
    private final Set<PropertySource<?>> propertySources = new LinkedHashSet();

    public CompositePropertySource(String name) {
        super(name);
    }

    public Object getProperty(String name) {
        for (PropertySource propertySource : propertySources) {
            if (propertySource.getProperty(name) != null) {
                return propertySource.getProperty(name);
            }
        }
        return null;
    }

    public boolean containsProperty(String name) {

        for (PropertySource propertySource : propertySources) {
            if (propertySource.containsProperty(name)) {
                return true;
            }
        }
        return false;
    }

    public String[] getPropertyNames() {
        Set<String> names = new LinkedHashSet();
        Iterator var2 = this.propertySources.iterator();

        while (var2.hasNext()) {
            PropertySource<?> propertySource = (PropertySource) var2.next();
            if (!(propertySource instanceof EnumerablePropertySource)) {
                throw new IllegalStateException("Failed to enumerate property names due to non-enumerable property source: " + propertySource);
            }

            names.addAll(Arrays.asList(((EnumerablePropertySource) propertySource).getPropertyNames()));
        }

        return StringUtils.toStringArray(names);
    }

    public void addPropertySource(PropertySource<?> propertySource) {
        this.propertySources.add(propertySource);
    }

    public void addFirstPropertySource(PropertySource<?> propertySource) {
        List<PropertySource<?>> existing = new ArrayList(this.propertySources);
        this.propertySources.clear();
        this.propertySources.add(propertySource);
        this.propertySources.addAll(existing);
    }

    public Collection<PropertySource<?>> getPropertySources() {
        return this.propertySources;
    }

    public String toString() {
        return String.format("%s [name='%s', propertySources=%s]", this.getClass().getSimpleName(), this.name, this.propertySources);
    }
}