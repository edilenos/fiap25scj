package br.com.fiap.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Classe DAO Generica para os m�todos de CRUD basicos. Para criar m�todos
 * especificos paras algum entity, extenda essa classe e crie um DAO especifico
 * 
 * @author Felipe
 *
 * @param <T>
 */
public class Dao<T> implements Serializable {

	private static final long serialVersionUID = 8318409175169480756L;

	private final Class<T> classe;

	// Esta sendo injetado aqui para quando as classes filhas utilizarem os métodos herdados
	@Inject
	private EntityManager em;

	public Dao(Class<T> classe) {
		this.classe = classe;
	}

	public Dao(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}

	public void adiciona(T entidade) {
		em.persist(entidade);
	}

	public void remove(T entidade) {
		em.remove(entidade);
	}

	public void atualiza(T entidade) {
		em.merge(entidade);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.from(classe);
		return em.createQuery(query).getResultList();
	}

	public T buscaPorId(Integer id) {
		return em.find(classe, id);
	}
	
	protected EntityManager getEntityManager() {
		return this.em;
	}
}
