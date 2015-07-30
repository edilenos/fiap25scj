package br.com.fiap.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.fiap.dao.Dao;
import br.com.fiap.dao.NotaDao;
import br.com.fiap.dao.annotation.Transacional;
import br.com.fiap.model.entity.Aluno;
import br.com.fiap.model.entity.Curso;
import br.com.fiap.model.entity.Disciplina;
import br.com.fiap.model.entity.Nota;
import br.com.fiap.model.enums.TipoNota;

@Named(value = "notaBean")
@ConversationScoped
public class NotaBean implements Serializable {

	private static final long serialVersionUID = 5134990385063835570L;

	@Inject
	private NotaDao notaDao;

	@Inject
	private Dao<Curso> daoCurso;

	@Inject
	private Dao<Aluno> alunoDao;

	@Inject
	private Dao<Disciplina> disciplinaDao;

	@Inject
	private Nota nota;

	private Aluno aluno;

	private List<Disciplina> disciplinas;

	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		if (FacesContext.getCurrentInstance().isPostback()) {
			try {
				Integer idAluno = Integer.valueOf(FacesContext
						.getCurrentInstance().getExternalContext()
						.getRequestParameterMap().get("id"));
				aluno = alunoDao.buscaPorId(idAluno);
			} catch (Exception e) {

			}
		}

		if (nota.getId() == null) {
			nota.setDisciplina(new Disciplina());
		}
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<Disciplina> getDisciplinas() {
		if (aluno == null || aluno.getCurso().getId() == null) {
			disciplinas = null;
		} else {
			Curso cursoSelecionado = daoCurso.buscaPorId(aluno.getCurso()
					.getId());
			disciplinas = cursoSelecionado.getDisciplinas();
		}

		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Transacional
	public void cadastrar() {
		try {
			nota.setAluno(aluno);
			nota.setDisciplina(disciplinaDao.buscaPorId(nota.getDisciplina()
					.getId()));
			validar(nota);
			notaDao.adiciona(nota);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Nota cadastrada com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
		}
	}

	private void validar(Nota nota) throws Exception {
		validarAlteracao(nota);

		for (Nota notaAluno : aluno.getNotas()) {
			if (notaAluno.getDisciplina().equals(nota.getDisciplina())) {
				if (notaAluno.getTipo().equals(nota.getTipo())) {
					throw new Exception("Nota de " + nota.getTipo()
							+ " para a disciplina " + notaAluno.getDisciplina()
							+ " já cadastrada");
				}
			}
		}
	}

	public List<TipoNota> getTiposNota() {
		return Arrays.asList(TipoNota.values());
	}

	public List<Nota> getNotas() {
		return notaDao.buscarNotasAluno(aluno.getId());
	}

	@Transacional
	public void alterar() {
		try {
			validarAlteracao(nota);
			notaDao.atualiza(nota);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Nota alterada com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
			
			
		}

	}

	public void validarAlteracao(Nota nota) throws Exception {
		if (nota.getValor() == null) {
			throw new Exception("Valor da nota ï¿½ obrigatï¿½rio");
		}

		if (nota.getValor() < 0 || nota.getValor() > 10) {
			throw new Exception("Valor da nota deve ser entre 0 e 10");
		}
	}

	public String selecionar(ActionEvent evento) {
		Integer notaId = (Integer) evento.getComponent().getAttributes()
				.get("notaId");
		nota = notaDao.buscaPorId(notaId);
		
		return "cadastroNotas?cid=" + conversation.getId();
	}

	@Transacional
	public String remover() {
		try {
			Nota notaRemover = notaDao.buscaPorId(nota.getId());
			notaDao.remove(notaRemover);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
			
		}
		
		terminarConversacao();

		return "cadastroNotas?id=" + aluno.getId() + "&faces-redirect=true";
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
		return "cadastroNotas?id=" + aluno.getId() + "&faces-redirect=true";
	}
}
