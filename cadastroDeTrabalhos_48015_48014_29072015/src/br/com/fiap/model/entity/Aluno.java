package br.com.fiap.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	private static final long serialVersionUID = -4760460860692933765L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@OneToMany(mappedBy = "aluno")
	private List<Nota> notas;

	@OneToOne(mappedBy = "aluno")
	private Usuario usuario;

	@Transient
	private String situacao;

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSituacao() {
		double resultado = 0;
		for (Nota nota : notas) {
			resultado += (nota.getValor() * nota.getTipo().getPeso());
		}

		if (resultado < 7) {
			situacao = "Reprovado";
		} else {
			situacao = "Aprovado";
		}
		return situacao;
	}
	
}
