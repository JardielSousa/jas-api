package br.com.jardielsousa.model.enumeration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ProdutoStatusTest {

    @Test
    void porCodigo_nulo() {
        assertThatCode(() -> ProdutoStatus.porCodigo(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void porCodigo_naoNulo_naoContainKey() {
        assertThatCode(() -> ProdutoStatus.porCodigo(999)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void porCodigo_naoNulo_containKey() {
        assertThat(ProdutoStatus.porCodigo(1)).isEqualTo(ProdutoStatus.ATIVO);
    }

}