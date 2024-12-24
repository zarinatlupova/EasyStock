package com.springeasystock.easystock.advice;

import com.springeasystock.easystock.exception.CustomNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class EasyStockControllerAdvice {

    private String extractFieldFromException(DataIntegrityViolationException ex) {
        String rootCause = ex.getRootCause().getMessage();
        if (rootCause != null) {
            int lastDotIndex = rootCause.lastIndexOf(".");
            if (lastDotIndex != -1) {
                String fieldName = rootCause.substring(lastDotIndex + 1);
                return "Field must not be null: " + fieldName;
            }
        }
        return "Unknown field must not be null"+ex.getRootCause();
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(CustomNotFoundException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("timestamp", LocalDateTime.now());
    errorResponse.put("message", "Element not found");
    errorResponse.put("details", ex.getMessage());
    errorResponse.put("status", HttpStatus.NOT_FOUND.value()); //404

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "Constraint violation");
        errorResponse.put("details", ex.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value()); //400
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "Data integrity violation");
        errorResponse.put("details", extractFieldFromException(ex));
        errorResponse.put("status", HttpStatus.CONFLICT.value()); // 409
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "Validation failed");
        errorResponse.put("details", fieldErrors);
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value()); // 400
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
