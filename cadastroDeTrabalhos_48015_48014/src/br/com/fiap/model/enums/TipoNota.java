package br.com.fiap.model.enums;

public enum TipoNota {
	PROJETO_1("P1", "Projeto 01", 0.3), PROJETO_2("P2", "Projeto 02", 0.4), ATIVIDADE_PRATICA(
			"AP", "Atividade Pratica", 0.3);

	private String valor;
	private String descricao;
	private double peso;

	private TipoNota(String valor, String descricao, double peso) {
		this.valor = valor;
		this.descricao = descricao;
		this.peso = peso;
	}

	public String getValue() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return descricao;
	}

	public static TipoNota getByValor(String valor) {
		for (TipoNota tipoNota : values()) {
			if (valor.equals(tipoNota.getValue())) {
				return tipoNota;
			}
		}
		return null;
	}
}
