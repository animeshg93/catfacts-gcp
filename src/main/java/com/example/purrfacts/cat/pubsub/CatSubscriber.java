package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.AcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CatSubscriber {

    private final PubSubTemplate pubSubTemplate;

    public CatSubscriber(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void subscribe(String topic, String message) {
        pubSubTemplate.subscribe("testTopic-sub",   mes -> {
            System.out.println("Message received is:  "
                    + mes.getPubsubMessage().getData().toStringUtf8());
            mes.ack();
        });
    }

    public String pull(String subscriptionName) {

        List<PubsubMessage> messages =
                pubSubTemplate.pullAndAck(subscriptionName, 10, true);
        return messages.getFirst().getData().toStringUtf8();
    }
}
