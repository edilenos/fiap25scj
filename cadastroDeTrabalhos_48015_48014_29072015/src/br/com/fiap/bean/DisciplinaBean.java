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
import br.com.fiap.model.entity.Curso;
import br.com.fiap.model.entity.Disciplina;

@Named(value = "disciplinaBean")
@RequestScoped
public class DisciplinaBean {

	@Inject
	private Dao<Disciplina> daoDisciplina;
	
	@Inject
	private Disciplina disciplina;

	@PostConstruct
	public void init() {
		disciplina.setCurso(new Curso());
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	@Transacional
	public void cadastrar() {
		try {
			daoDisciplina.adiciona(disciplina);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Disciplina cadastrada com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
		}
	}
	
	public void validar(Disciplina disciplina) throws Exception {
		List<Disciplina> disciplinas = daoDisciplina.listaTodos();
		
		if (disciplinas != null && disciplinas.contains(disciplina)) {
			throw new Exception("Já existe uma disciplina cadastrada com esse nome.");
		}
	}
}
