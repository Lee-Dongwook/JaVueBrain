package com.example.backend.common.response;

import java.time.OffsetDateTime;

public class ErrorResponse {
    private final String message;
    private final String code;
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    
    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
