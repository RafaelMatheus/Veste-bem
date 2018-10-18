package br.com.vestebem.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.vestebem.model.Cliente;
import br.com.vestebem.service.validation.ClienteInsert;

@ClienteInsert
public class ClienteDto {
	private Integer id;
	@NotEmpty(message="Preenchimento do campo nome é obrigatório")
	@Length(min=5, message="O tamanho deve ter pelo menos 5 caracteres")
	private String nome;
	@NotEmpty(message="É obrigatório o preenchimento do email")
	@Email(message="Email inválido")
	private String email;

	public ClienteDto() {

	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
