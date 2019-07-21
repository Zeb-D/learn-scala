package com.yd.test.pulsar

import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

import org.apache.pulsar.client.api.PulsarClient
import org.junit.Test

/**
  * @author created by zouyd on 2019-07-21 17:09
  */
class PulsalTest {

  @Test
  def consumer() {
    val client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
    val consumer = client.newConsumer().topic("my-topic").subscriptionName("pulsar-my-sub")
      .ackTimeout(1, TimeUnit.SECONDS).subscribe();

    val msg = consumer.receive(2, TimeUnit.SECONDS);
    consumer.acknowledgeAsync(msg)
    println(new String(msg.getData))
  };

  @Test
  def producer() {
    val client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();

    val producer = client.newProducer.topic("my-topic").create

    // You can then send messages to the broker and topic you specified:
    val msgId = producer.send("My message -3".getBytes(Charset.forName("UTF-8")))
    println(new String(msgId.toByteArray))
  }
}
