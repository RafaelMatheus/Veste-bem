package br.com.vestebem.service.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private List<FieldMessage> fieldMessages = new ArrayList<FieldMessage>();

	public ValidationError(Integer statusHttp, String mensagem, long timeStamp) {
		super(statusHttp, mensagem, timeStamp);

	}

	public List<FieldMessage> getErros() {
		return fieldMessages;
	}

	public void setError(String fieldMessages, String message) {
		this.fieldMessages.add(new FieldMessage(fieldMessages, message));
	}

}
