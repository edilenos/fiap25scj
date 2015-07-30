package br.com.fiap.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.fiap.model.enums.TipoNota;

/**
 * Conversor para persistir o enum {@link TipoNota} no banco de dados
 */
@Converter(autoApply = true)
public class TipoNotaConverter implements AttributeConverter<TipoNota, String> {

	public String convertToDatabaseColumn(TipoNota tipoNota) {
		return tipoNota.getValue();
	}

	public TipoNota convertToEntityAttribute(String valorBanco) {
		return TipoNota.getByValor(valorBanco);
	}

}
