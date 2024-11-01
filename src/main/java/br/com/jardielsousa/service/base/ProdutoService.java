package br.com.jardielsousa.service.base;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;

public interface ProdutoService {

    Produto criarProduto(ProdutoCriarRequest request);

    Produto criar(Produto produto);
}
