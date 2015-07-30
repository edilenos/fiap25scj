package br.com.fiap.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.entity.Usuario;
import br.com.fiap.model.enums.TipoUsuario;

@Named(value = "loginUsuario")
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 4668078470806650058L;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private Usuario usuario;

	private boolean isLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String validarUsuario() {

		try {
			usuario = usuarioDao.buscarPorNomeUsuarioESenha(
					usuario.getNome(), usuario.getSenha());

			isLogado = true;
			if (TipoUsuario.PROFESSOR.equals(usuario.getTipo())) {
				return "/admin/cursos";
			} else {
				return "/aluno/notas";
			}

		} catch (NoResultException e) {
			isLogado = false;
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage();
			msg.setDetail("Usuário e/ou senha inválido(s)");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);

			context.addMessage(null, msg);

			return "/index";
		}
	}

	public boolean isLogado() {
		return isLogado;
	}
}
