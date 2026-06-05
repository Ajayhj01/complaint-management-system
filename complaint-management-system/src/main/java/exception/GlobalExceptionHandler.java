package com.cms.complaint_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.cms.complaint_management_system.exception.ResourceNotFoundException.class)
    public ResponseEntity<com.cms.complaint_management_system.exception.ErrorResponse> handleNotFound(com.cms.complaint_management_system.exception.ResourceNotFoundException ex) {
        com.cms.complaint_management_system.exception.ErrorResponse error = new com.cms.complaint_management_system.exception.ErrorResponse(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.cms.complaint_management_system.exception.ErrorResponse> handleGeneral(Exception ex) {
        com.cms.complaint_management_system.exception.ErrorResponse error = new com.cms.complaint_management_system.exception.ErrorResponse(500, "Something went wrong");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}