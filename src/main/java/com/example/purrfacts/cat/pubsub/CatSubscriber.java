package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
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

  public void subscribe() {
    pubSubTemplate.subscribe(subscription, BasicAcknowledgeablePubsubMessage::ack);
  }

  public String pull() {
    List<PubsubMessage> messages = pubSubTemplate.pullAndAck(subscription, 2, true);
    return messages.getFirst().getData().toStringUtf8();
  }
}
