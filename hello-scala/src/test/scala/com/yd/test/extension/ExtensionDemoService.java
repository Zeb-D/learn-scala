package com.yd.test.extension;

import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;
import org.springframework.stereotype.Component;

/**
 * @author Zeb灬D
 * @date 2021/5/7 4:31 下午
 */

@Component
public class ExtensionDemoService {
    public ExtensionResult<String> test(ExtensionParameter param) {
        System.out.println("1111");
        return ExtensionResult.success(System.currentTimeMillis() + "-OK");
    }
}
