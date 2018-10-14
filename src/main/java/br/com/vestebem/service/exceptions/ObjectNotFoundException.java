package br.com.vestebem.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String arg) {
		super(arg);
	}
	public ObjectNotFoundException(String arg, Throwable cause) {
		super(arg, cause);
	}

}
