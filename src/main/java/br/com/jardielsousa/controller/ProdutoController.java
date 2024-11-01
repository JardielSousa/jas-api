package br.com.jardielsousa.controller;

import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarResponse;
import br.com.jardielsousa.service.base.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private final Logger LOG = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoCriarResponse> criar(@RequestBody ProdutoCriarRequest request) {

        LOG.info("Criando produto {}", request);

        final var produto = this.produtoService.criarProduto(request);
        final var criado = this.produtoService.criar(produto);

        return ResponseEntity.ok(new ProdutoCriarResponse(criado.getId(), criado.getNome(), criado.getDescricao(), criado.getPreco()));
    }
}
