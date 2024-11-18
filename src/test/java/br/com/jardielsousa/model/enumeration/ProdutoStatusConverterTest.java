package br.com.jardielsousa.model.enumeration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ProdutoStatusConverterTest {

    @Test
    void convertToDatabaseColumn() {
        assertThat(new ProdutoStatusConverter().convertToDatabaseColumn(ProdutoStatus.ATIVO)).isEqualTo(1);
    }

    @Test
    void convertToDatabaseColumn_nulo() {
        assertThatCode(() -> new ProdutoStatusConverter().convertToDatabaseColumn(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void convertToEntityAttribute() {
        assertThat(new ProdutoStatusConverter().convertToEntityAttribute(1)).isEqualTo(ProdutoStatus.ATIVO);
    }

    @Test
    void convertToEntityAttribute_nulo() {
        assertThatCode(() -> new ProdutoStatusConverter().convertToEntityAttribute(null)).isInstanceOf(IllegalArgumentException.class);
    }
}