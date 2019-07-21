package com.yd.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.yd.akka.ArgsActor;
import com.yd.akka.factory.ActorFactory;
import org.junit.Test;

/**
 * @author Yd on  2018-02-07
 * @description
 **/
public class ArgsActorTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void Test() {
        ActorRef actor = system.actorOf(ActorFactory.props(ArgsActor.class, "Pong"));
        actor.tell("ping", ActorRef.noSender());
    }

    @Test
    public void testRemote() {
        ActorSelection selection = system.actorSelection("akka.tcp://actorSystem@host.jason-goodwin.com:5678/user/KeanuReeves");
        selection.pathString();
    }

}
