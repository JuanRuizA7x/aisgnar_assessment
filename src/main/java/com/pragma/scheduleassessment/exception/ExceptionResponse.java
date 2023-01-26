package com.pragma.scheduleassessment.exception;

public enum ExceptionResponse {
    CHAPTER_AND_SPECIALTY_NOT_FOUND("Chapter and Specialty not found");

    private final String  message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
