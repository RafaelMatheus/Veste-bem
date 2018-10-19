package br.com.vestebem.model.dto;

import br.com.vestebem.model.Produto;

public class ProdutoDto {
	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDto() {

	}
	public ProdutoDto(Produto p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.preco = p.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
