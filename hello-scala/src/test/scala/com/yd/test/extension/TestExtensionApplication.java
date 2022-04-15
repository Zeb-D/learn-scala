package com.yd.test.extension;

import com.yd.scala.hello.extension.annotation.EnableExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;

/**
 * Created By Zeb灬D On 2020-03-29
 **/

@SpringBootApplication(
        scanBasePackages = {"com.yd.test.extension", "com.yd.scala.hello.extension"},
        exclude = {
                RedisAutoConfiguration.class,
                RedisRepositoriesAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                TaskExecutionAutoConfiguration.class,
                TaskSchedulingAutoConfiguration.class})
@EnableExtension(group = "pisces", definitionFactoryClass = TestExtensionDefinitionFactory.class)
public class TestExtensionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestExtensionApplication.class, args);
    }
}
