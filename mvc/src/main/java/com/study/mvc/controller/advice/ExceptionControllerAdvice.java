package com.study.mvc.controller.advice;

import com.study.mvc.exception.DuplicatedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<?> duplicatedExceptionHandel(DuplicatedException e) {
        return ResponseEntity.badRequest().body(e.getErrorMap());
    }
}
