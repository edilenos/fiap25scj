package br.com.fiap.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.fiap.model.enums.TipoUsuario;

/**
 * Conversor para persistir o enum {@link TipoUsuario} no banco de dados
 */
@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, String> {

	public String convertToDatabaseColumn(TipoUsuario tipoUsuario) {
		return tipoUsuario.getValue();
	}

	public TipoUsuario convertToEntityAttribute(String valorBanco) {
		return TipoUsuario.getByValor(valorBanco);
	}

}
