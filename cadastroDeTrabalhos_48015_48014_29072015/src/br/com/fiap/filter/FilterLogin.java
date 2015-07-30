package br.com.fiap.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.bean.UsuarioBean;
import br.com.fiap.model.enums.TipoUsuario;

@WebFilter("/admin/*")
public class FilterLogin implements Filter {

	@Inject
	private UsuarioBean usuarioBean;

	public FilterLogin() {
		
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!usuarioBean.isLogado()) {
			resp.sendRedirect("../index.xhtml");
		} else if (TipoUsuario.ALUNO.equals(usuarioBean.getUsuario().getTipo())) {
			resp.sendRedirect("../aluno/notas.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
