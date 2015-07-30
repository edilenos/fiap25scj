package br.com.fiap.model.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fiap.dao.Dao;
import br.com.fiap.model.entity.Aluno;

/**
 * Utilizado na lista de alunos para converter um id de aluno em um objeto aluno
 * salvo no banco
 * 
 * @author Felipe
 *
 */
@Named
@RequestScoped
public class AlunoConverter implements Converter {

	@Inject
	private Dao<Aluno> alunoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			return alunoDao.buscaPorId(Integer.valueOf(submittedValue));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(submittedValue
					+ " Não é um Id de um aluno cadastrado"), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Aluno) {
			return String.valueOf(((Aluno) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue
					+ "is not a valid User entity"));
		}
	}

}
