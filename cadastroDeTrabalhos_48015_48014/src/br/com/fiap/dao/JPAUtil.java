package br.com.fiap.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	// Usa CDI para criar Factory, tempo de vida "Aplicacao"
	@Produces @ApplicationScoped
	public EntityManagerFactory criaFactory() {
		return Persistence.createEntityManagerFactory("cadastroJPA");
	}
	
	// Usa CDI para criar EntityManager, tempo de vida "Requisicao"
	@Produces @RequestScoped
	public EntityManager criaEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	// Fecha o entityManager
	public void fechaEntityManager(@Disposes EntityManager em) {
		em.close();
	}
}
