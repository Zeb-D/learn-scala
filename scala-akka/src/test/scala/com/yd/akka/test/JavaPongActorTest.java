package com.yd.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.yd.scala.akka.ScalaPongActor;
import org.junit.Test;

/**
 * @author Yd on  2018-02-06
 * @description
 **/
public class JavaPongActorTest {
    ActorSystem actorSystem = ActorSystem.create();

    @Test
    public void test() {
        ActorRef actor = actorSystem.actorOf(Props.create(ScalaPongActor.class));
        actor.tell("Ping", ActorRef.noSender());
        TestActorRef<ScalaPongActor> actorTestActor = TestActorRef.create(actorSystem, Props.create(ScalaPongActor.class));
        actorTestActor.receive("Ping");
        actorTestActor.tell("Ping", ActorRef.noSender());
        ScalaPongActor javaPongActor = actorTestActor.underlyingActor();
        javaPongActor.receive();
    }

}
