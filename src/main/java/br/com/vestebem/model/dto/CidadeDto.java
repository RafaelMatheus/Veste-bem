package br.com.vestebem.model.dto;

import br.com.vestebem.model.Cidade;

public class CidadeDto {

	private Integer id;
	private String nome;
	
	public CidadeDto() {
		
	}

	public CidadeDto(Cidade cidade) {
		super();
		this.id = cidade.getId();
		this.nome = cidade.getNome();
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
}
