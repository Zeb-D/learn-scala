package com.yd;

/**
 * @author created by Zeb-D on 2019-08-19 11:39
 */

import com.yd.scala.grpc.GrpcApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcApp.class)
public class BaseTest {
}
