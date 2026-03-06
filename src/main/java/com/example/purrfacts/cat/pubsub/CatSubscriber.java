package com.example.purrfacts.cat.pubsub;

import com.example.purrfacts.cat.exception.NoMessagesException;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatSubscriber {

  private static final Logger log = LoggerFactory.getLogger(CatSubscriber.class);

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
    if (messages.isEmpty()) {
      log.error("There are no messages to be pulled");
      throw new NoMessagesException();
    }
    return messages.getFirst().getData().toStringUtf8();
  }
}
