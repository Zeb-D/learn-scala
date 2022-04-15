
package com.yd.scala.hello.extension.annotation;

import com.yd.scala.hello.extension.config.DefaultExtensionDefinitionFactory;
import com.yd.scala.hello.extension.config.ExtensionDefinitionFactory;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created By Zeb灬D On 2021-05-25
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ExtensionEnable.class)
public @interface EnableExtension {

    String group();

    String definitionFactory() default "";

    Class<? extends ExtensionDefinitionFactory> definitionFactoryClass() default DefaultExtensionDefinitionFactory.class;
}
