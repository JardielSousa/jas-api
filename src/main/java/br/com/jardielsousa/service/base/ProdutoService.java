package br.com.jardielsousa.service.base;

import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoAlterarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;

import java.util.List;

public interface ProdutoService {

    Produto criarProduto(ProdutoCriarRequest request);

    Produto criarProduto(ProdutoAlterarRequest request);

    Produto criar(Produto produto);

    List<Produto> buscarTodos();

    Produto alterarProduto(Long id, Produto request);

    Boolean ativarDesativarProduto(Long id);
}
