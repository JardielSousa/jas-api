package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoAlterarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(final ProdutoCriarRequest request) {
        return new Produto(null, request.getNome(), request.getDescricao(), request.getPreco(), null);
    }

    @Override
    public Produto criarProduto(final ProdutoAlterarRequest request) {
        return new Produto(null, request.getNome(), request.getDescricao(), request.getPreco(), null);
    }

    @Override
    public Produto criar(final Produto produto) {
        final var produtoEntitySalvo = this.produtoRepository.save(new ProdutoEntity(null, produto.getNome(), produto.getDescricao(), produto.getPreco()));
        return this.produtoEntityParaProduto(produtoEntitySalvo);
    }

    @Override
    public List<Produto> buscarTodos() {
        return this.produtoRepository.findAll().stream().map(this::produtoEntityParaProduto).toList();
    }

    @Override
    public Produto alterarProduto(final Long id, final Produto produto) {
        final var optionalProdutoEntity = this.produtoRepository.findById(id);
        if (optionalProdutoEntity.isPresent()) {
            final var produtoEntity = optionalProdutoEntity.get();
            produtoEntity.setNome(produto.getNome());
            produtoEntity.setDescricao(produto.getDescricao());
            produtoEntity.setPreco(produto.getPreco());

            return this.produtoEntityParaProduto(this.alterarProduto(produtoEntity));
        }

        throw new IllegalArgumentException("Produto não encontrado");
    }

    @Override
    public Boolean ativarDesativarProduto(final Long id) {
        final var optionalProdutoEntity = this.produtoRepository.findById(id);
        if (optionalProdutoEntity.isPresent()) {
            var produtoEntity = optionalProdutoEntity.get();
            final var status = (produtoEntity.getStatus().equals(ProdutoStatus.ATIVO)) ? ProdutoStatus.INATIVO : ProdutoStatus.ATIVO;
            produtoEntity.setStatus(status);
            produtoEntity = this.alterarProduto(produtoEntity);

            return produtoEntity.getStatus().equals(ProdutoStatus.ATIVO);
        }

        throw new IllegalArgumentException();
    }

    private ProdutoEntity alterarProduto(final ProdutoEntity produto) {
        produto.setDataAlteracao(LocalDateTime.now());
        return this.produtoRepository.save(produto);
    }

    private Produto produtoEntityParaProduto(final ProdutoEntity produtoEntitySalvo) {
        return new Produto(
                produtoEntitySalvo.getId(),
                produtoEntitySalvo.getNome(),
                produtoEntitySalvo.getDescricao(),
                produtoEntitySalvo.getPreco(),
                produtoEntitySalvo.getStatus());
    }

}
