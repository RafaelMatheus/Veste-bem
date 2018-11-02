package br.com.vestebem.service.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private List<FieldMessage> fieldMessages = new ArrayList<FieldMessage>();

	public ValidationError(long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	

	public List<FieldMessage> getErros() {
		return fieldMessages;
	}


	public void setError(String fieldMessages, String message) {
		this.fieldMessages.add(new FieldMessage(fieldMessages, message));
	}

}
