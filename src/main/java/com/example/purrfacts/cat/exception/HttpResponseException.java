package com.example.purrfacts.cat.exception;

/** Exception thrown when an HTTP request returns a non-200 status code. */
public class HttpResponseException extends RuntimeException {

  private final int statusCode;
  private final String statusText;
  private final String responseBody;

  /**
   * Constructs a new HttpResponseException with status code and response body.
   *
   * @param statusCode the HTTP status code
   * @param statusText the HTTP status text
   * @param responseBody the response body from the HTTP request
   */
  public HttpResponseException(int statusCode, String statusText, String responseBody) {
    super(String.format("HTTP Error %d %s: %s", statusCode, statusText, responseBody));
    this.statusCode = statusCode;
    this.statusText = statusText;
    this.responseBody = responseBody;
  }

  /**
   * Constructs a new HttpResponseException with status code only.
   *
   * @param statusCode the HTTP status code
   * @param statusText the HTTP status text
   */
  public HttpResponseException(int statusCode, String statusText) {
    super(String.format("HTTP Error %d %s", statusCode, statusText));
    this.statusCode = statusCode;
    this.statusText = statusText;
    this.responseBody = null;
  }

  /**
   * Constructs a new HttpResponseException with a custom message and status code.
   *
   * @param message the detail message
   * @param statusCode the HTTP status code
   */
  public HttpResponseException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
    this.statusText = null;
    this.responseBody = null;
  }

  /**
   * Constructs a new HttpResponseException with a custom message, status code, and cause.
   *
   * @param message the detail message
   * @param statusCode the HTTP status code
   * @param cause the cause of the exception
   */
  public HttpResponseException(String message, int statusCode, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
    this.statusText = null;
    this.responseBody = null;
  }

  /**
   * Gets the HTTP status code.
   *
   * @return the status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Gets the HTTP status text.
   *
   * @return the status text, or null if not provided
   */
  public String getStatusText() {
    return statusText;
  }

  /**
   * Gets the response body.
   *
   * @return the response body, or null if not provided
   */
  public String getResponseBody() {
    return responseBody;
  }
}
