package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    @Override
    public Produto criarProduto(final ProdutoCriarRequest request) {
        return this.modelMapper.map(request, Produto.class);
    }

    @Override
    public Produto criar(final Produto produto) {
        final var save = this.produtoRepository.save(this.modelMapper.map(produto, ProdutoEntity.class));
        return this.modelMapper.map(save, Produto.class);
    }

    @Override
    public List<Produto> buscarTodos() {
        final List<ProdutoEntity> produtosEntity = this.produtoRepository.findAll();
        return produtosEntity.stream()
                .map(produtoEntity -> this.modelMapper.map(produtoEntity, Produto.class))
                .toList();
    }

    @Override
    public Produto alterarProduto(final Long id) {
        return new Produto();
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
}
