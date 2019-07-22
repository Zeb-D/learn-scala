package com.yd.akka.test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.yd.akka.AkkademyDb;
import com.yd.entity.SetRequest;
import junit.framework.Assert;
import org.junit.Test;
import scala.Some;

public class AkkademyDbTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("test-key", "test-value"), ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        akkademyDb.receive();
        Assert.assertTrue(akkademyDb.map().get("test-key").equals(new Some<String>("test-value")));
    }
}