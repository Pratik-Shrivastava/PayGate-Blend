package com.pratik.payGateBlend.common.Exception;

import com.pratik.payGateBlend.common.model.GeneralResponse;
import com.pratik.payGateBlend.constants.AppConstants;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(
		@NotNull BindException ex,
		WebRequest request
	) {
		System.out.println(ex.getStackTrace());
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		GeneralResponse errorResponse = new GeneralResponse(
			false,
			AppConstants.PAYLOAD_NOT_ACCEPTABLE_STATUS_CODE,
			"Validation Failed",
			errors
		);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(UnAuthorizeException.class)
	public ResponseEntity<?> handleUnAuthorizeException(
		@NotNull Exception ex,
		WebRequest request
	) {
		
		System.out.println(ex.getStackTrace());
		GeneralResponse errorResponse = new GeneralResponse(
			false,
			401,
			ex.getMessage(),
			null
		);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidAccessException.class)
	public ResponseEntity<?> handleInvalidAccessException(
		@NotNull Exception ex,
		WebRequest request
	) {
		System.out.println(ex.getStackTrace());
		GeneralResponse errorResponse = new GeneralResponse(
			false,
			403,
			ex.getMessage(),
			null
		);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(
		@NotNull Exception ex,
		WebRequest request
	) {
		System.out.println(ex.getStackTrace());
		GeneralResponse errorResponse = new GeneralResponse(
			false,
			AppConstants.EXCEPTION_OCCURRED_STATUS_CODE,
			ex.getMessage(),
			null
		);
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
