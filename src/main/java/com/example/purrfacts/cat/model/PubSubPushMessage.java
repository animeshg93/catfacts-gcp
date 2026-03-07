package com.example.purrfacts.cat.model;

public class PubSubPushMessage {
  private PubSubMessage message;
  private String subscription;

  public PubSubMessage getMessage() {
    return message;
  }

  public void setMessage(PubSubMessage message) {
    this.message = message;
  }

  public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }
}
