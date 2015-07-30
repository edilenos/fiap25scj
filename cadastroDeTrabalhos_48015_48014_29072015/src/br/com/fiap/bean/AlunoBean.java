package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.annotation.Transacional;
import br.com.fiap.model.entity.Aluno;
import br.com.fiap.model.entity.Curso;
import br.com.fiap.model.entity.Disciplina;
import br.com.fiap.model.entity.Nota;

@Named(value = "alunoBean")
@RequestScoped
public class AlunoBean {

	@Inject
	private Dao<Aluno> alunoDao;

	@Inject
	private Aluno aluno;

	@Inject
	private Nota nota;

	private List<Disciplina> disciplinas;

	private List<Aluno> alunos;

	@PostConstruct
	public void init() {
		aluno.setCurso(new Curso());
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Transacional
	public void cadastrar() {
		try {
			alunoDao.adiciona(aluno);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Aluno cadastrado com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Erro no cadastro do aluno!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
		}
	}

	public List<Aluno> getAlunos() {
		alunos = alunoDao.listaTodos();
		return alunos;
	}

	public String getRowClass() {
		StringBuilder rowClasses = new StringBuilder();

		for (Aluno aluno : alunos) {
			if (rowClasses.length() > 0) {
				rowClasses.append(",");
			}
			if (aluno.getSituacao().equals("Aprovado")) {
				rowClasses.append("success");
			} else if (aluno.getSituacao().equals("Reprovado")) {
				rowClasses.append("danger");
			}
		}

		return rowClasses.toString();
	}

	public String getNotaRowClass() {
		StringBuilder rowClasses = new StringBuilder();

		for (Aluno aluno : alunos) {
			for (Nota nota : aluno.getNotas()) {
				if (rowClasses.length() > 0) {
					rowClasses.append(",");
				}
				if (nota.getValor() > 7) {
					rowClasses.append("success");
				} else {
					rowClasses.append("danger");
				}
			}
		}

		return rowClasses.toString();
	}
}
