package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.pubsub.CatPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final CatPublisher catPublisher;

    @Autowired
    public MessageController(CatPublisher catPublisher) {
        this.catPublisher = catPublisher;
    }

    @PostMapping
    public ResponseEntity<String> publishMessage(@RequestBody String message) {
        catPublisher.publishMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message published");
    }

}
