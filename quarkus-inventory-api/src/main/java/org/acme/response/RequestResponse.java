package org.acme.response;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RequestResponse {
    private final LocalDateTime requestDate;

    private Object content;

    public RequestResponse() {
        this.requestDate = LocalDateTime.now();
    }

    public Object getContent() {
        return content;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
