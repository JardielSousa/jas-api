package br.com.jardielsousa.factories;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarResponse;
import br.com.jardielsousa.model.enumeration.ProdutoStatus;

import java.math.BigDecimal;
import java.util.List;

public final class ProdutoFactory {

    private ProdutoFactory() { }

    public static ProdutoCriarRequest fabricarProdutoCriarRequest() {
        return ProdutoCriarRequest.builder()
                .nome("Produto 01")
                .descricao("Descrição 01")
                .preco(BigDecimal.TEN)
                .build();
    }

    public static ProdutoEntity fabricarProdutoEntity(final String nome) {
        return ProdutoEntity.builder()
                .nome(nome)
                .descricao(String.format("Descrição do %s", nome))
                .preco(BigDecimal.TEN)
                .status(ProdutoStatus.ATIVO)
                .build();
    }

    public static List<ProdutoEntity> fabricarProdutosEntity() {
        final var produto1 = fabricarProdutoEntity("Produto 01");
        final var produto2 = fabricarProdutoEntity("Produto 02");
        final var produto3 = fabricarProdutoEntity("Produto 03");

        return List.of(produto1, produto2, produto3);
    }

    public static ProdutoCriarResponse fabricarProdutoCriarResponse(final Long id, final String nome) {
        return new ProdutoCriarResponse(id, nome, String.format("Descrição do %s", nome), BigDecimal.TEN);
    }
}
