package br.com.compasso.productapi.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic = true)
public class ResponseError {

    @JsonProperty("code_status")
    private int codeStatus;
    private String message;

    public ResponseError() {}

    public ResponseError(int statusCode, String message) {
        this.codeStatus = statusCode;
        this.message = message;
    }

    public int getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(int statusCode) {
        this.codeStatus = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
