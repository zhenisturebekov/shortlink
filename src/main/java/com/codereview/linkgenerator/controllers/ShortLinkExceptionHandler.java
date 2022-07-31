package com.codereview.linkgenerator.controllers;

import com.codereview.linkgenerator.entities.ShortLinkErrorResponse;
import com.codereview.linkgenerator.exceptions.ShortLinkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShortLinkExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ShortLinkErrorResponse> handleException(ShortLinkNotFoundException ex) {
        return new ResponseEntity<>(getShortLinkErrorResponse(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ShortLinkErrorResponse> handleException(Exception ex) {
        return new ResponseEntity<>(getShortLinkErrorResponse(new ShortLinkNotFoundException("Bad request. Invalid data.")), HttpStatus.BAD_REQUEST);
    }

    private ShortLinkErrorResponse getShortLinkErrorResponse(Exception ex){
        ShortLinkErrorResponse response = new ShortLinkErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return response;
    }

}
