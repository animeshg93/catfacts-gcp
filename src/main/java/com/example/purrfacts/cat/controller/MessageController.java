package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.model.PubSubPushMessage;
import com.example.purrfacts.cat.pubsub.CatPublisher;
import com.example.purrfacts.cat.pubsub.CatSubscriber;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);
  private final CatPublisher catPublisher;
  private final CatSubscriber catSubscriber;

  @Autowired
  public MessageController(CatPublisher catPublisher, CatSubscriber catSubscriber) {
    this.catPublisher = catPublisher;
    this.catSubscriber = catSubscriber;
  }

  @PostMapping
  public ResponseEntity<String> publishMessage(@RequestBody String message) {
    catPublisher.publishMessage(message);
    return ResponseEntity.status(HttpStatus.CREATED).body("Message published");
  }

  @GetMapping("/pull")
  public ResponseEntity<String> getMessages() {
    String message = catSubscriber.pull();
    return ResponseEntity.status(HttpStatus.CREATED).body(message);
  }

  @GetMapping("/push")
  public ResponseEntity<String> receiveMessages(@RequestBody PubSubPushMessage message) {
    String rawData = message.getMessage().getData();

    // Decode the Base64 payload
    String decoded = new String(Base64.getDecoder().decode(rawData), StandardCharsets.UTF_8);

    log.info("Received message: {}", decoded);

    // Return 200-204 to acknowledge. Anything else causes Pub/Sub to retry.
    return ResponseEntity.noContent().build();
  }
}
