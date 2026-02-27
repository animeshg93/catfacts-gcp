package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatSubscriber {

    private final PubSubTemplate pubSubTemplate;

    public CatSubscriber(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void subscribe(String topic, String message) {
        pubSubTemplate.subscribe("testTopic-sub", mes -> {
            System.out.println("Message received is:  "
                    + mes.getPubsubMessage().getData().toStringUtf8());
            mes.ack();
        });
    }
}
