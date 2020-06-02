package com.yd.akka.test.db;

import com.yd.scala.akka.db.JClient;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author created by Zeb-D on 2019-08-04 20:58
 */
public class JClientTest {
    JClient client = new JClient("127.0.0.1:2552");

    @Test
    public void itShouldSetRecord() throws Exception {
        client.set("123", 123);
        Integer result = (Integer) ((CompletableFuture) client.get("123")).get();
        assert(result == 123);
    }
}
