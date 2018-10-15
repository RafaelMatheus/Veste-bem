package br.com.vestebem.model.enums;

public enum EstadoPagamento {
	PENDENTE(1, "PENDENTE"), 
	QUITADO(2, "QUITADO"), 
	CANCELADO(3, "CANCELADO");

	private int codigo;
	private String descricao;

	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) throws Exception {
		if (codigo == null)
			return null;

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo()))
				return x;
		}
		throw new Exception("Id inválido" + codigo);
	}
}
