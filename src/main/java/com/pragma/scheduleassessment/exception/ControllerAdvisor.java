package com.pragma.scheduleassessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice()
public class ControllerAdvisor {
        private static final String ERROR_MESSAGE = "message";
        @ExceptionHandler(ChapterAndSpecialtyNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleChapterAndSpecialtyNotFound() {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(ERROR_MESSAGE, ExceptionResponse.CHAPTER_AND_SPECIALTY_NOT_FOUND.getMessage()));
        }

        @ExceptionHandler(EventsNotFoundException.class)
            public ResponseEntity<Map<String, String>> handleEventsNotFound() {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(ERROR_MESSAGE, ExceptionResponse.EVENTS_NOT_FOUND.getMessage()));
        }

        @ExceptionHandler(ConsultEventClientResponseNullException.class)
            public ResponseEntity<Map<String, String>> handleConsultEventClientResponseNull() {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(ERROR_MESSAGE, ExceptionResponse.CONSULT_EVENT_CLIENT_RESPONSE_NULL.getMessage()));
        }

        @ExceptionHandler(UpdateEventClientResponseNullException.class)
            public ResponseEntity<Map<String, String>> handleUpdateEventClientResponseNull() {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(ERROR_MESSAGE, ExceptionResponse.UPDATE_EVENT_CLIENT_RESPONSE_NULL.getMessage()));
        }
}
