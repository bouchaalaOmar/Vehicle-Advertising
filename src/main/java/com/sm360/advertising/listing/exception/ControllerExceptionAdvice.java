package com.sm360.advertising.listing.exception;

import com.sm360.advertising.listing.exception.custumexception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<ErrorMessage> duplicateEntityException(DuplicateEntityException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PublishingLimitReachedException.class)
    public ResponseEntity<ErrorMessage> publishingLimitReachedException(PublishingLimitReachedException ex,
                                                                        WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ErrorCode.PUBLISH_LIMIT_REACHED.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> publishingLimitReachedException(NoSuchElementException ex,
                                                                        WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ErrorCode.EMPTY_DATA.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
