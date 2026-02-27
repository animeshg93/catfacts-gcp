package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.pubsub.CatPublisher;
import com.example.purrfacts.cat.pubsub.CatSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

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
        String message = catSubscriber.pull("testTopic-sub");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
