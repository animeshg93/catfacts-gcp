package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.pubsub.v1.PubsubMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatSubscriber {

  @Value("${pubsub.subscription}")
  private String subscription;

  private final PubSubTemplate pubSubTemplate;

  public CatSubscriber(PubSubTemplate pubSubTemplate) {
    this.pubSubTemplate = pubSubTemplate;
  }

  public void subscribe(String topic, String message) {
    pubSubTemplate.subscribe(
        subscription,
        mes -> {
          System.out.println(
              "Message received is:  " + mes.getPubsubMessage().getData().toStringUtf8());
          mes.ack();
        });
  }

  public String pull(String subscriptionName) {

    List<PubsubMessage> messages = pubSubTemplate.pullAndAck(subscriptionName, 10, true);
    return messages.getFirst().getData().toStringUtf8();
  }
}
