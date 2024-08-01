package com.backend.backend_firman_ismail_hariri.model.responseHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<ResponseAPI> generateResponse(String message, HttpStatus status, Object responseObj) {
        ResponseAPI response = ResponseAPI.builder()
                .code(status.value())
                .status(status)
                .message(message)
                .data(responseObj)
                .build();

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ResponseAPI> successResponse(Object responseObj, HttpHeaders... headers) {
        HttpStatus status = HttpStatus.OK;
        ResponseAPI response = ResponseAPI.builder()
                .code(status.value())
                .status(status)
                .message("Success")
                .data(responseObj)
                .build();

        if (headers.length > 0) return new ResponseEntity<>(response, headers[0], status);

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ResponseAPI> badRequestResponse(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseAPI response = ResponseAPI.builder()
                .code(status.value())
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<ResponseAPI> notFoundResponse(String... message) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String msg = "Data not found";
        if (message.length > 0) msg = message[0];
        ResponseAPI response = ResponseAPI.builder()
                .code(status.value())
                .status(status)
                .message(msg)
                .build();

        return new ResponseEntity<>(response, status);
    }
}
