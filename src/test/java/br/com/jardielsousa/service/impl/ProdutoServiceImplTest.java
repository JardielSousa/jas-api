package br.com.jardielsousa.service.impl;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.factories.ProdutoFactory;
import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoAlterarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import br.com.jardielsousa.repositoty.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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
    void criarProduto_ProdutoCriarRequest() {
        final ProdutoCriarRequest request = ProdutoFactory.fabricarProdutoCriarRequest();
        final Produto produto = this.produtoService.criarProduto(request);
        assertThat(produto).isNotNull();
        assertThat(produto.getNome()).isEqualTo(request.getNome());
        assertThat(produto.getDescricao()).isEqualTo(request.getDescricao());
        assertThat(produto.getPreco()).isEqualTo(request.getPreco());
    }

    @Test
    void criarProduto_ProdutoAlterarRequest() {
        final ProdutoAlterarRequest request = ProdutoFactory.fabricarProdutoAlterarRequest("Produto 01");
        final Produto produto = this.produtoService.criarProduto(request);
        assertThat(produto).isNotNull();
        assertThat(produto.getNome()).isEqualTo(request.getNome());
        assertThat(produto.getDescricao()).isEqualTo(request.getDescricao());
        assertThat(produto.getPreco()).isEqualTo(request.getPreco());
    }

    @Test
    void criar() {
        when(this.produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().id(1L).build());
        assertThatCode(() -> this.produtoService.criar(new Produto())).doesNotThrowAnyException();
    }

    @Test
    void buscarTodos() {
        when(this.produtoRepository.findAll()).thenReturn(ProdutoFactory.fabricarProdutosEntity());
        assertThatCode(() -> this.produtoService.buscarTodos()).doesNotThrowAnyException();
    }

    @Test
    void alterarProduto_idInexistente() {
        when(this.produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatCode(() -> this.produtoService.alterarProduto(1L, new Produto())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void alterarProduto() {
        when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(ProdutoFactory.fabricarProdutoEntity("Produto 01", ProdutoStatus.ATIVO)));
        when(this.produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().id(1L).build());
        assertThatCode(() -> this.produtoService.alterarProduto(1L, new Produto())).doesNotThrowAnyException();
    }

    @Test
    void ativarDesativarProduto_ativo() {
        when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(ProdutoFactory.fabricarProdutoEntity("Produto 01", ProdutoStatus.ATIVO)));
        when(this.produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().id(1L).status(ProdutoStatus.ATIVO).build());
        assertThatCode(() -> this.produtoService.ativarDesativarProduto(1L)).doesNotThrowAnyException();
    }

    @Test
    void ativarDesativarProduto_inativo() {
        when(this.produtoRepository.findById(1L)).thenReturn(Optional.of(ProdutoFactory.fabricarProdutoEntity("Produto 01", ProdutoStatus.INATIVO)));
        when(this.produtoRepository.save(any(ProdutoEntity.class))).thenReturn(ProdutoEntity.builder().id(1L).status(ProdutoStatus.INATIVO).build());
        assertThatCode(() -> this.produtoService.ativarDesativarProduto(1L)).doesNotThrowAnyException();
    }

    @Test
    void ativarDesativarProduto_idInexistente() {
        assertThatCode(() -> this.produtoService.ativarDesativarProduto(1L)).isInstanceOf(IllegalArgumentException.class);
    }
}
