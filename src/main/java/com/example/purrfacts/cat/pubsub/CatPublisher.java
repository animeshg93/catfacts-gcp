package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatPublisher {

    @Value("${pubsub.topic}")
    private String topicName;
    private final PubSubTemplate pubSubTemplate;

    public CatPublisher(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publishMessage(String message) {
        pubSubTemplate.publish(topicName, message);
    }
}
