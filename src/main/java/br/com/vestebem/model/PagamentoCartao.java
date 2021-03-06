package br.com.vestebem.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.vestebem.model.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoCartao extends Pagamento {
	private Integer qntParcelas;

	public PagamentoCartao() {

	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer qntParcelas) {
		super(id, estado, pedido);
		this.qntParcelas = qntParcelas;
	}

	public Integer getQntParcelas() {
		return qntParcelas;
	}

	public void setQntParcelas(Integer qntParcelas) {
		this.qntParcelas = qntParcelas;
	}

}
