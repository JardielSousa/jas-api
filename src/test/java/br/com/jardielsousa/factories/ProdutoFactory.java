package br.com.jardielsousa.factories;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.enumeration.ProdutoStatus;

import java.math.BigDecimal;
import java.util.List;

public final class ProdutoFactory {

    private ProdutoFactory() { }

    public static ProdutoCriarRequest criarProdutoRequest() {
        return ProdutoCriarRequest.builder()
                .nome("Produto 01")
                .descricao("Descrição 01")
                .preco(BigDecimal.TEN)
                .status(ProdutoStatus.ATIVO)
                .build();
    }

    public static ProdutoEntity criarProdutoEntity(final String nome) {
        return ProdutoEntity.builder()
                .nome(nome)
                .descricao(String.format("Descrição do %s", nome))
                .preco(BigDecimal.TEN)
                .status(ProdutoStatus.ATIVO)
                .build();
    }

    public static List<ProdutoEntity> criarProdutosEntity() {
        final var produto1 = criarProdutoEntity("Produto 01");
        final var produto2 = criarProdutoEntity("Produto 02");
        final var produto3 = criarProdutoEntity("Produto 03");

        return List.of(produto1, produto2, produto3);
    }

}
