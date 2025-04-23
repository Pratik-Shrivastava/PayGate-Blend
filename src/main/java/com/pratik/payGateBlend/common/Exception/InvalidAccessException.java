package com.pratik.payGateBlend.common.Exception;

public class InvalidAccessException extends RuntimeException {
	
	public InvalidAccessException(String message) {
		super(message);
	}
	
	public InvalidAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}