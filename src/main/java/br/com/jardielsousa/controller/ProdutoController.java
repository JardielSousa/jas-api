package br.com.jardielsousa.controller;

import br.com.jardielsousa.model.dto.produto.ProdutoBuscarTodosResponse;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarResponse;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoCriarResponse> criar(@RequestBody final ProdutoCriarRequest request) {

        log.info("Criando produto {}", request);

        final var produto = this.produtoService.criarProduto(request);
        final var criado = this.produtoService.criar(produto);

        return ResponseEntity.created(URI.create("/produtos/" + criado.getId())).build();
    }

    @GetMapping
    public ResponseEntity<ProdutoBuscarTodosResponse> buscarTodos() {
        log.info("Buscando todos os produtos");
        final var produtos = this.produtoService.buscarTodos();
        return ResponseEntity.ok(new ProdutoBuscarTodosResponse(produtos));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> alterarProduto(@PathVariable(value = "id") final Long id) {
        log.info("Alterando o produto de id: {}", id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Boolean> ativarDesativarProduto(@PathVariable(value = "id") final Long id) {
        log.info("Ativando / desativando produto de id: {}", id);
        final var b = this.produtoService.ativarDesativarProduto(id);
        return ResponseEntity.ok(b);
    }
}
