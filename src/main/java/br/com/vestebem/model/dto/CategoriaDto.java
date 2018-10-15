package br.com.vestebem.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.vestebem.model.Categoria;

public class CategoriaDto {

	private Integer id;
	@NotEmpty(message="Preenchimento do campo nome é obrigatório")
	@Length(min=5,max=80,message="Tamanho deve ter entre 5 e 80 caractes")
	private String nome;

	public CategoriaDto() {

	}

	public CategoriaDto(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
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