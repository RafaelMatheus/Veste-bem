package br.com.vestebem.model.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) throws Exception {
		if (codigo == null)
			return null;

		for (TipoCliente x : TipoCliente.values()) {
			if (codigo.equals(x.getCodigo()))
				return x;
		}
		throw new Exception("Id inválido" + codigo);
	}
}
