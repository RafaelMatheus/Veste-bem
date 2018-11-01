package br.com.vestebem.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDto {
	@NotEmpty(message="O email é de preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;

	public EmailDto() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
