package com.pragma.scheduleassessment.exception;

public enum ExceptionResponse {
    CHAPTER_AND_SPECIALTY_NOT_FOUND("The chapterId and/or specialty do not correspond to values stored in the database"),
    EVENTS_NOT_FOUND("No available events found"),
    CONSULT_EVENT_CLIENT_RESPONSE_NULL("Event query client response is null"),
    UPDATE_EVENT_CLIENT_RESPONSE_NULL("Event update client response is null");

    private final String  message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
