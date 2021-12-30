package com.yd;

import com.yd.scala.grpc.sigmax.SigmaxConsumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author created by Zeb-D on 2019-08-19 11:43
 */
public class GrpcTest extends BaseTest {

    @Autowired
    private SigmaxConsumer sigmaxConsumer;

    @Test
    public void test() {
        sigmaxConsumer.addJob("112344", 12344L);
    }

}
