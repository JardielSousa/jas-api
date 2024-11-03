package br.com.jardielsousa.controller;

import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarResponse;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoCriarResponse> criar(@RequestBody ProdutoCriarRequest request) {

        log.info("Criando produto {}", request);

        final var produto = this.produtoService.criarProduto(request);
        final var criado = this.produtoService.criar(produto);

        return ResponseEntity.ok(new ProdutoCriarResponse(criado.getId(), criado.getNome(), criado.getDescricao(), criado.getPreco()));
    }
}
