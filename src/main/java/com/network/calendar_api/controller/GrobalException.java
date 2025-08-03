package com.network.calendar_api.controller;

import com.network.calendar_api.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GrobalException {
    @ExceptionHandler(CalendarNotFoundException.class)
    public ResponseEntity<BaseResponse> CalenderNotFoundException(CalendarNotFoundException e) {
        return ResponseEntity.status(400).body(new BaseResponse(400, e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> Exception(Exception e) {
        return ResponseEntity.status(500).body(new BaseResponse(500, e.getMessage(), null));
    }
}
