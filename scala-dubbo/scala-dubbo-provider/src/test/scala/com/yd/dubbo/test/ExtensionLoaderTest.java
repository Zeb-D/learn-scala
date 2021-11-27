package com.yd.dubbo.test;

import com.yd.scala.dubbo.filter.VarPassFilter;
import com.yd.scala.dubbo.filter.YdFilter;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.AdaptiveClassCodeGenerator;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;


/**
 * 自己实现了一些filter，用来自测filter-impl
 *
 * @author created by Zeb灬D on 2021-11-25 14:43
 */
public class ExtensionLoaderTest {
    @Test
    public void ExtensionLoaderTest() {
        ExtensionLoader<YdFilter> filterExtensionLoader = ExtensionLoader.getExtensionLoader(YdFilter.class);

        System.out.println(filterExtensionLoader.hasExtension("permission"));
        String varPaas = filterExtensionLoader.getExtensionName(VarPassFilter.class);
        System.out.println(varPaas);
        //下面是因为InternPermissionFilter加了@Adaptive才不会报错
        System.out.println(filterExtensionLoader.getAdaptiveExtension());
        //如果将@Adaptive放到实现类中，就不会出现cacheClasses中，这个注解一般是放在@SPI注解 接口中的
        System.out.println(filterExtensionLoader.getSupportedExtensions());
        System.out.println(filterExtensionLoader.getDefaultExtension());
        System.out.println(filterExtensionLoader.getExtension("varpass"));
        System.out.println(createAdaptiveExtensionClass(YdFilter.class, "varpass"));

    }

    @Test
    public void TestActivateExtension() {
        URL url = URL.valueOf("dubbo://172.16.46.62:21913/com.xxx.application.user.hospitality.IHospitalityCodeService");
        //@Activate()自动会带上，只要group没限制
        url = url.addParameter("value1", "anything");
        //添加varpass实例，去掉c实例
        url = url.addParameter("yd.filter", "varpass,-c");
        System.out.println(ExtensionLoader.getExtensionLoader(YdFilter.class).getActivateExtension(url, "yd.filter", "group1"));
        System.out.println(ExtensionLoader.getExtensionLoader(YdFilter.class).getActivateExtension(url, "yd.filter", "group"));
        System.out.println(ExtensionLoader.getExtensionLoader(YdFilter.class).getActivateExtension(url, ""));
        System.out.println(ExtensionLoader.getExtensionLoader(YdFilter.class).getActivateExtension(url, "value", "group1"));
        System.out.println(ExtensionLoader.getExtensionLoader(YdFilter.class).getActivateExtension(url, "value", "group"));
    }

    private Class<?> createAdaptiveExtensionClass(Class<?> type, String cachedDefaultName) {
        String code = (new AdaptiveClassCodeGenerator(type, cachedDefaultName)).generate();
        System.out.println(code);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        return compiler.compile(code, classLoader);
    }

}
