package com.ardecs.carconfiguration.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
 @RestControllerAdvice
public class ControllerHandler {
 @ExceptionHandler
 private ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundIdException e) {
  ResourceErrorResponse response = new ResourceErrorResponse(
          e.getMessage() + " with this ID wasn't found!",
          System.currentTimeMillis()
  );
  return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler
 private ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundNameException e) {
  ResourceErrorResponse response = new ResourceErrorResponse(
          e.getMessage() + " with this NAME wasn't found!",
          System.currentTimeMillis()
  );
  return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler
 private ResponseEntity<ResourceErrorResponse> handleException(DuplicateNameException e) {
  ResourceErrorResponse response = new ResourceErrorResponse(
          e.getMessage() + " with this NAME has already exists!",
          System.currentTimeMillis()
  );
  return new ResponseEntity<>(response, HttpStatus.FOUND);
 }

 @ExceptionHandler
 private ResponseEntity<ResourceErrorResponse> handleException(ResourceNotCreatedException e) {
  ResourceErrorResponse response = new ResourceErrorResponse(
          e.getMessage(),
          System.currentTimeMillis()
  );
  return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
 }
}
