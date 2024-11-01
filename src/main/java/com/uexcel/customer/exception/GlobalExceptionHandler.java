package com.uexcel.customer.exception;

import com.uexcel.customer.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException
            (final  ResourceNotFoundException e, final WebRequest webRequest) {
      ErrorResponseDto err =  new ErrorResponseDto(
              404,e.getMessage(),
                webRequest.getDescription(false), getTimeStamp());
      return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            final BadRequestException e, final WebRequest webRequest){
        ErrorResponseDto err =  new ErrorResponseDto(
                400,e.getMessage(),
                webRequest.getDescription(false), getTimeStamp());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(final Exception e, final WebRequest webRequest) {
        ErrorResponseDto err =  new ErrorResponseDto(
                500,e.getMessage(),
                webRequest.getDescription(false), getTimeStamp());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        return sdf.format(new Date());
    }

}
