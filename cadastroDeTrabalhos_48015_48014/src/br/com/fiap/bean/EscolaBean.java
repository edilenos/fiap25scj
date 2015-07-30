package br.com.fiap.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fiap.dao.Dao;
import br.com.fiap.dao.annotation.Transacional;
import br.com.fiap.model.entity.Escola;

@Named(value = "escolaBean")
@ConversationScoped
public class EscolaBean implements Serializable {

	private static final long serialVersionUID = 5850992839935445800L;

	@Inject
	private Dao<Escola> daoEscola;

	@Inject
	private Escola escola;

	@Inject
	private Conversation conversation;

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Dao<Escola> getDaoEscola() {
		return daoEscola;
	}

	public void setDaoEscola(Dao<Escola> daoEscola) {
		this.daoEscola = daoEscola;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	@Transacional
	public void cadastrar() {
		try {

			validar(escola);
			daoEscola.adiciona(escola);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Escola cadastrada com sucesso!");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);
		}
	}

	@Transacional
	public void alterar() {
		try {
			validar(escola);
			daoEscola.atualiza(escola);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(
					"Escola alterada com sucesso!");
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

	public List<Escola> getEscolas() {
		return daoEscola.listaTodos();
	}

	@Transacional
	public String remover() {
		try {
			Escola escolaRemover = daoEscola.buscaPorId(escola.getId());
			daoEscola.remove(escolaRemover);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage(e.getMessage());
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, facesMessage);

		}

		terminarConversacao();

		return "cadastroEscola?faces-redirect=true";
	}

	public void validar(Escola escola) throws Exception {

		if (escola.getNome() == null || escola.getNome() == "")
			throw new Exception("Escola n�o possui nome.");

		for (Escola escolas : getEscolas()) {
			if (escolas.getNome().equals(escola.getNome())) {
				if (!escolas.getId().equals(escola.getId())) {
					throw new Exception(
							"J� existe uma escola cadastrada com esse nome.");
				}
			}
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
		return "cadastroEscola?faces-redirect=true";
	}

	public String selecionar(ActionEvent evento) {
		Integer escolaId = (Integer) evento.getComponent().getAttributes()
				.get("escolaId");
		escola = daoEscola.buscaPorId(escolaId);

		return "cadastroEscola?cid=" + conversation.getId();
	}

}
