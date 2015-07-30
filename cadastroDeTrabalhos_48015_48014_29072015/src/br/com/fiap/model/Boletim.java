package br.com.fiap.model;

import br.com.fiap.model.enums.TipoNota;

public class Boletim {

	private String nomeAluno;
	private String curso;
	private String disciplina;
	private TipoNota tipoNota;
	private double nota;

	public Boletim(String nomeAluno, String curso, String disciplina,
			TipoNota tipoNota, double nota) {
		super();
		this.nomeAluno = nomeAluno;
		this.curso = curso;
		this.disciplina = disciplina;
		this.tipoNota = tipoNota;
		this.nota = nota;
	}

}
