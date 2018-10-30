package br.com.vestebem.model.dto;

public class CredenciaisDto {
	private String userName;
	private String senha;
	
	public CredenciaisDto() {
		
	}
	
	public CredenciaisDto(String userName, String senha) {
		super();
		this.userName = userName;
		this.senha = senha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
