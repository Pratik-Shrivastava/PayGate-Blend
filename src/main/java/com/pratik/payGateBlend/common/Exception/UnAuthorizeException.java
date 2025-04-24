package com.pratik.payGateBlend.common.Exception;

public class UnAuthorizeException extends RuntimeException {
	
	public UnAuthorizeException(String message) {
		super(message);
	}
	
	public UnAuthorizeException(String message, Throwable cause) {
		super(message, cause);
	}
}
