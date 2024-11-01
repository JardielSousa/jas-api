package br.com.jardielsousa.model.dto.produto;

import java.math.BigDecimal;

public record ProdutoCriarResponse(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco) {
}
