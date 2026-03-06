package com.example.purrfacts.cat.exception;

/** Exception thrown when there are no messages to pull from a Pub/Sub subscription. */
public class NoMessagesException extends RuntimeException {

  /** Constructs a new NoMessagesException with the default message. */
  public NoMessagesException() {
    super("No messages available to pull from the subscription");
  }

  /**
   * Constructs a new NoMessagesException with a custom message.
   *
   * @param message the detail message
   */
  public NoMessagesException(String message) {
    super(message);
  }

  /**
   * Constructs a new NoMessagesException with a custom message and cause.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   */
  public NoMessagesException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new NoMessagesException with a cause.
   *
   * @param cause the cause of the exception
   */
  public NoMessagesException(Throwable cause) {
    super("No messages available to pull from the subscription", cause);
  }
}
