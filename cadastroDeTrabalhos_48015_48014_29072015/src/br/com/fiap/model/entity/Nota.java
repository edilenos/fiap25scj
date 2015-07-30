package br.com.fiap.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fiap.model.enums.TipoNota;

@Entity
@Table(name = "notas")
public class Nota implements Serializable {

	private static final long serialVersionUID = -1883254431093523536L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;

	private TipoNota tipo;

	@Column(name = "val_nota")
	private Double valor;

	public Integer getId() {
		return id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public TipoNota getTipo() {
		return tipo;
	}

	public void setTipo(TipoNota tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStyle() {
		if (this.valor > 7) {
			return "success";
		} else {
			return "danger";
		}
	}
}
