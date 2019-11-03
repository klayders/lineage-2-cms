package studio.lineage2.cms.other;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
  //reCAPTCHA verification errors
  MISSING_SECRET_KEY("missing-input-secret"),
  INVALID_SECRET_KEY("invalid-input-secret"),
  MISSING_USER_CAPTCHA_RESPONSE("missing-input-response"),
  INVALID_USER_CAPTCHA_RESPONSE("invalid-input-response"),

  //Custom errors
  MISSING_USERNAME_REQUEST_PARAMETER("missing-username-request-parameter"),
  MISSING_CAPTCHA_RESPONSE_PARAMETER("missing-captcha-response-parameter"),
  VALIDATION_HTTP_ERROR("validation-http-error");

  private final String text;

  ErrorCode(String text) {
    this.text = text;
  }

  @JsonValue
  public String getText() {
    return text;
  }
}
