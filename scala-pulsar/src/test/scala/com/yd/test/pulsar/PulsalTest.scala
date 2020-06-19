package com.yd.test.pulsar

import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

import org.apache.pulsar.client.api.PulsarClient
import org.junit.{Before, Test}

/**
  * @author created by Zeb-D on 2019-07-21 17:09
  */
class PulsalTest {
  var client: PulsarClient = null

  @Before
  def before() {
    client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build()
  }

  @Test
  def consumer() {
    val consumer = client.newConsumer().topic("my-topic").subscriptionName("pulsar-my-sub")
      .ackTimeout(1, TimeUnit.SECONDS).subscribe()

    var msg = consumer.receive(2, TimeUnit.SECONDS)
    consumer.acknowledge(msg)
    println(new String(msg.getData))
  };

  @Test
  def producer() {
    val producer = client.newProducer.topic("my-topic").create

    // You can then send messages to the broker and topic you specified:
    val msgId = producer.send("My message -1".getBytes(Charset.forName("UTF-8")))
    println(new String(msgId.toString))
  }
}
