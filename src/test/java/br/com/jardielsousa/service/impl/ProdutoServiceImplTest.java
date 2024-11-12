package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.factories.ProdutoFactory;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProdutoServiceImplTest {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    void criarProduto() {
        final ProdutoCriarRequest request = ProdutoFactory.fabricarProdutoCriarRequest();
        final Produto produto = this.produtoService.criarProduto(request);
        assertThat(produto).isNotNull();
        assertThat(produto.getNome()).isEqualTo(request.getNome());
        assertThat(produto.getDescricao()).isEqualTo(request.getDescricao());
        assertThat(produto.getPreco()).isEqualTo(request.getPreco());
    }

    @Test
    void criar() {
        when(this.produtoRepository.save(any(ProdutoEntity.class))).thenReturn(new ProdutoEntity());
        assertThatCode(() -> this.produtoService.criar(new Produto())).doesNotThrowAnyException();
    }

    @Test
    void buscarTodos() {
        when(this.produtoRepository.findAll()).thenReturn(ProdutoFactory.fabricarProdutosEntity());
        assertThatCode(() -> this.produtoService.buscarTodos()).doesNotThrowAnyException();
    }

}
