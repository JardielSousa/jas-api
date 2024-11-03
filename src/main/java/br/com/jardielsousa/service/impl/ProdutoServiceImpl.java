package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

}
