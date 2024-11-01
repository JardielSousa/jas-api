package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(final ProdutoCriarRequest request) {
        return Produto.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .preco(request.getPreco())
                .build();
    }

    @Override
    public Produto criar(final Produto produto) {
        final var save = this.produtoRepository.save(this.toEntity(produto));
        return Produto.builder()
                .id(save.getId())
                .nome(save.getNome())
                .descricao(save.getDescricao())
                .preco(save.getPreco())
                .build();
    }

    private ProdutoEntity toEntity(Produto produto) {
        return ProdutoEntity.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .build();
    }
}
