package br.com.fiap.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.annotation.Transacional;
import br.com.fiap.model.entity.Aluno;
import br.com.fiap.model.entity.Curso;
import br.com.fiap.model.entity.Disciplina;
import br.com.fiap.model.entity.Escola;

@Named(value = "cursoBean")
@ConversationScoped
public class CursoBean implements Serializable {

	private static final long serialVersionUID = -3395149841106452382L;

	@Inject
	private Dao<Curso> daoCurso;
	
	@Inject
	private Curso curso;
	
	private List<Aluno> alunos;
	
	@Inject
	private Conversation conversation;
	
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	@Transacional
	public void cadastrar() {
		try {
			daoCurso.adiciona(curso);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Curso cadastrado com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
		}
	}

	public List<Curso> getCursos() {
		return daoCurso.listaTodos();
	}

	public void alunosMatriculados() {
		Curso cursoSelecionado = daoCurso.buscaPorId(curso.getId());
		alunos = cursoSelecionado.getAlunos();
	}

	public void validar(Curso curso) throws Exception {
		List<Curso> cursos = getCursos();

		if (cursos != null && cursos.contains(curso)) {
			throw new Exception("Já existe um curso cadastrado com esse nome.");
		}
	}
	
	public void iniciarConversacao() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	private void terminarConversacao() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public String cancelar() {
		terminarConversacao();
		return "cadastroCurso?faces-redirect=true";
	}

	public String selecionar(ActionEvent evento) {
		Integer cursoId = (Integer) evento.getComponent().getAttributes()
				.get("cursoId");
		curso = daoCurso.buscaPorId(cursoId);

		return "cadastroCurso?cid=" + conversation.getId();
	}

}
