package com.example.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author zhang zhengdong
 * @DATE 2024/12/27 20:08
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
		return ResponseEntity.badRequest().body("Validation error: " + ex.getMessage());
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleBindException(BindException ex) {
		return ResponseEntity.badRequest().body("Validation error: " + ex.getBindingResult().getAllErrors());
	}
}