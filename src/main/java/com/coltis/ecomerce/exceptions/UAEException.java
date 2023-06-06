package com.coltis.ecomerce.exceptions;

public class UAEException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UAEException() {
		super("User already has an account");
	}

}
