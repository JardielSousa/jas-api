package br.com.jardielsousa.model.enumeration;

import jakarta.persistence.AttributeConverter;

import static java.util.Objects.nonNull;

public class ProdutoStatusConverter implements AttributeConverter<ProdutoStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final ProdutoStatus status) {
        if (nonNull(status)) {
            return status.getCodigo();
        }

        throw new IllegalArgumentException("Status inválido");
    }

    @Override
    public ProdutoStatus convertToEntityAttribute(final Integer dbStatus) {
        return ProdutoStatus.porCodigo(dbStatus);
    }
}
