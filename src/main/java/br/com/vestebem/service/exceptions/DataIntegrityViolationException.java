package br.com.vestebem.service.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String arg) {
		super(arg);
	}
	public DataIntegrityViolationException(String arg, Throwable cause) {
		super(arg, cause);
	}

}
