package com.ardecs.carconfiguration.controllers;

import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
import com.ardecs.carconfiguration.exceptions.DuplicateNameException;
import com.ardecs.carconfiguration.exceptions.ResourceErrorResponse;
import com.ardecs.carconfiguration.exceptions.ResourceNotCreatedException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundModelComplectException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
 @RestControllerAdvice
public class ControllerHandler {
 @ResponseStatus(HttpStatus.NOT_FOUND)
 @ExceptionHandler
 private ResourceErrorResponse handleException(ResourceNotFoundIdException e) {
  return new ResourceErrorResponse(e.getMessage() + " with this ID wasn't found!",
          System.currentTimeMillis());
 }

 @ResponseStatus(HttpStatus.NOT_FOUND)
 @ExceptionHandler
 private ResourceErrorResponse handleException(ResourceNotFoundNameException e) {
  return new ResourceErrorResponse(e.getMessage() + " with this NAME wasn't found!",
          System.currentTimeMillis());
 }

 @ResponseStatus(HttpStatus.NOT_FOUND)
 @ExceptionHandler
 private ResourceErrorResponse handleException(ResourceNotFoundModelComplectException e) {
  return new ResourceErrorResponse(e.getMessage() + " with this complectation ID wasn't found for this model!",
          System.currentTimeMillis());
 }

 @ResponseStatus(HttpStatus.CONFLICT)
 @ExceptionHandler
 private ResourceErrorResponse handleException(DuplicateNameException e) {
  return new ResourceErrorResponse(e.getMessage() + " with this NAME has already exists!",
          System.currentTimeMillis());
 }

 @ResponseStatus(HttpStatus.CONFLICT)
 @ExceptionHandler
 private ResourceErrorResponse handleException(DuplicateModelComplectException e) {
  return new ResourceErrorResponse(e.getMessage() + " with this NAME has already exists for this model!",
          System.currentTimeMillis());
 }

 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler
 private ResourceErrorResponse handleException(ResourceNotCreatedException e) {
  return new ResourceErrorResponse(e.getMessage(), System.currentTimeMillis());
 }
}
