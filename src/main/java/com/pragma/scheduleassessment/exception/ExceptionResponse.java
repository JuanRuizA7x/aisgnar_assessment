package com.pragma.scheduleassessment.exception;

public enum ExceptionResponse {
    CHAPTER_AND_SPECIALTY_NOT_FOUND("Chapter and Specialty not found"),
    EVENTS_NOT_FOUND("No fueroin encontrados eventos disponibles"),
    CONSULT_EVENT_CLIENT_RESPONSE_NULL("La respuesta del cliente de consulta de eventos es nula"),
    UPDATE_EVENT_CLIENT_RESPONSE_NULL("La respuesta del cliente de actualización de eventos es nula");

    private final String  message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
