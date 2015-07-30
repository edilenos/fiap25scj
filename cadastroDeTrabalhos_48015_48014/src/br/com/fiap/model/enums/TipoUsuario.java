package br.com.fiap.model.enums;

public enum TipoUsuario {
	PROFESSOR("P"), ALUNO("A"), ADMINISTRADOR("D");

	private String value;

	private TipoUsuario(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.name();
	}

	public static TipoUsuario getByValor(String valor) {
		for (TipoUsuario tipoUsuario : values()) {
			if (valor.equals(tipoUsuario.getValue())) {
				return tipoUsuario;
			}
		}
		return null;
	}
}
