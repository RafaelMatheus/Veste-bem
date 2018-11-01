package br.com.vestebem.service.exceptions;

public class FileException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FileException(String arg) {
		super(arg);
	}
	public FileException(String arg, Throwable cause) {
		super(arg, cause);
	}

}
