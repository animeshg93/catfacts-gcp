package com.example.purrfacts.cat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatsExceptionHandler {

  @ExceptionHandler(NoMessagesException.class)
  public ResponseEntity<String> handleMessageNotFound(NoMessagesException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found");
  }

  @ExceptionHandler(HttpResponseException.class)
  public ResponseEntity<String> handleAuthErrors(HttpResponseException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unable to get token");
  }
}
