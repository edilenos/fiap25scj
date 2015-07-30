package br.com.fiap.interceptors;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.fiap.dao.annotation.Transacional;

@Interceptor @Transacional
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 9154457859069165994L;
	
	@Inject
	private EntityManager em;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		try {
			em.getTransaction().begin();
			Object resultado = ctx.proceed();
			em.getTransaction().commit();
			
			return resultado;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}
}
