package br.com.vestebem.model.dto;

import br.com.vestebem.model.Estado;

public class EstadoDto {
	
	private Integer id;
	private String nome;
	
	public EstadoDto() {
		
	}

	public EstadoDto(Estado estado) {
		super();
		this.id = estado.getId();
		this.nome = estado.getNome();
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
