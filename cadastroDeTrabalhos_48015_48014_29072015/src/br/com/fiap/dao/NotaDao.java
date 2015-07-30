package br.com.fiap.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.fiap.model.entity.Nota;

public class NotaDao extends Dao<Nota> {

	private static final long serialVersionUID = -7328234571895269612L;
	
	public NotaDao() {
		super(Nota.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Nota> buscarNotasAluno(Integer idAluno) {
		Query query = getEntityManager().createQuery("FROM Nota WHERE aluno.id = :idAluno");
		query.setParameter("idAluno", idAluno);
		
		return query.getResultList();
	}
}
