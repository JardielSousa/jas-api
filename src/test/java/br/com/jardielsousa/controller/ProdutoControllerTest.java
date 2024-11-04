package br.com.jardielsousa.controller;

import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.service.base.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void criar() throws Exception {
        when(this.produtoService.criarProduto(any(ProdutoCriarRequest.class))).thenReturn(new Produto());
        when(this.produtoService.criar(any(Produto.class))).thenReturn(new Produto());

        this.mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"nome\": \"Produto 01\",\n" +
                        "  \"descricao\": \"Descrição 01\",\n" +
                        "  \"preco\": 10.0,\n" +
                        "  \"status\": \"ATIVO\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void buscarTodos() throws Exception {
        this.mockMvc.perform(get("/produtos")).andExpect(status().isOk());
    }
}