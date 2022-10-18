package com.example.bid_submission_rabbitmq.exception;

import com.example.bid_submission_rabbitmq.dto.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseData<Void>> handle500(RuntimeException e) {
        return new ResponseEntity<>(new ResponseData<>(null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
