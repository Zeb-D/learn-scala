
package com.yd.test.extension;

import com.yd.scala.hello.extension.config.BaseConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created By Zeb灬D On 2020-03-29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestExtensionApplication.class})
public class ExtensionTest {

    @Resource
    private DemoService demoService;

    @Before
    public void init() {
//        BaseConfig.setExtensionDefinitionFactory(new TestExtensionDefinitionFactory());
    }

    @Test
    public void test() {
        String result = demoService.test("1", "188888");
//
//        result = demoService.test("2", "188888");
//
//        result = demoService.test("1", "182888");

        System.out.println(result);
    }
}
