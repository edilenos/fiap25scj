package br.com.fiap.dao;

import javax.persistence.Query;

import br.com.fiap.model.entity.Usuario;

public class UsuarioDao extends Dao<Usuario>{

	private static final long serialVersionUID = 8880992062368370009L;

	
	public UsuarioDao() {
		super(Usuario.class);
	}

	public Usuario buscarPorNomeUsuarioESenha(String nomeUsuario, String senha) {
		Query query = getEntityManager().createQuery("FROM Usuario WHERE nome = :nome AND senha = :senha");
		query.setParameter("nome", nomeUsuario);
		query.setParameter("senha", senha);
		
		return (Usuario) query.getSingleResult();
	}
}
