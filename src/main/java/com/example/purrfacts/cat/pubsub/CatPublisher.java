package com.example.purrfacts.cat.pubsub;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatPublisher {

  private static final Logger log = LoggerFactory.getLogger(CatPublisher.class);

  @Value("${pubsub.topic}")
  private String topicName;

  private final PubSubTemplate pubSubTemplate;

  public CatPublisher(PubSubTemplate pubSubTemplate) {
    this.pubSubTemplate = pubSubTemplate;
  }

  public void publishMessage(String message) {
    CompletableFuture<String> future = pubSubTemplate.publish(topicName, message);
    if (future.isCompletedExceptionally()) {
      log.error("Error while publishing");
    } else {
      log.info("Message published successfully to {}", topicName);
    }
  }
}
