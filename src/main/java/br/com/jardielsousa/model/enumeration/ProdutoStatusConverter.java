package br.com.jardielsousa.model.enumeration;

import jakarta.persistence.AttributeConverter;

public class ProdutoStatusConverter implements AttributeConverter<ProdutoStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final ProdutoStatus status) {
        return status.getCodigo();
    }

    @Override
    public ProdutoStatus convertToEntityAttribute(final Integer dbStatus) {
        return ProdutoStatus.porCodigo(dbStatus);
    }
}
