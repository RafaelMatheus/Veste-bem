package br.com.vestebem.service.exceptions;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String arg) {
		super(arg);
	}
	public AuthorizationException(String arg, Throwable cause) {
		super(arg, cause);
	}

}
