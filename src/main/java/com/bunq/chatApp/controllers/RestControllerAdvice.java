package com.bunq.chatApp.controllers;

import com.bunq.chatApp.dto.HttpErrorResponse;
import com.bunq.chatApp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
@ControllerAdvice
public class RestControllerAdvice {

    private static final String COLON = " : ";
    private static final String ERROR_MESSAGE = "Fatal error: ";
    private static final int SB_INITIAL_CAPACITY = 128;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpErrorResponse> handleException(Exception exception) {
        String uuid = UUID.randomUUID().toString();
        log.error(uuid + COLON + getMessage(exception), exception);
        return createHttpErrorResponse(INTERNAL_SERVER_ERROR, ERROR_MESSAGE + uuid);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpErrorResponse> handleIOException(IOException exception) {
        String uuid = UUID.randomUUID().toString();
        log.error(uuid + COLON + getMessage(exception), exception);
        return createHttpErrorResponse(INTERNAL_SERVER_ERROR, ERROR_MESSAGE + uuid);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<HttpErrorResponse> handleEntityNotFoundException(NotFoundException exception) {
        String uuid = UUID.randomUUID().toString();
        log.error(uuid + COLON + getMessage(exception), exception);
        return createHttpErrorResponse(NOT_FOUND, getMessage(exception));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<HttpErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        String uuid = UUID.randomUUID().toString();
        log.error(uuid + COLON + getMessage(exception), exception);
        return createHttpErrorResponse(BAD_REQUEST, getMessage(exception));
    }

    private String getMessage(Exception exception) {
        return StringUtils.defaultString(exception.getMessage());
    }

    private ResponseEntity<HttpErrorResponse> createHttpErrorResponse(HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .contentType(APPLICATION_JSON)
                .body(HttpErrorResponse.builder()
                        .statusCode(httpStatus.value())
                        .status(httpStatus)
                        .reason(httpStatus.getReasonPhrase())
                        .message(message)
                        .build());
    }
}
