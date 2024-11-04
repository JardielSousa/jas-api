package br.com.jardielsousa.model.dto.produto;

import br.com.jardielsousa.model.domain.produto.Produto;

import java.util.List;

public record ProdutoBuscarTodosResponse(
    List<Produto> produtos
) { }
