package br.com.jardielsousa.controller;

import br.com.jardielsousa.model.domain.produto.Produto;
import br.com.jardielsousa.model.dto.produto.ProdutoCriarRequest;
import br.com.jardielsousa.service.base.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.jardielsousa.config.RestControllerMappingValueConfig.PRODUTOS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

        this.mockMvc.perform(post(PRODUTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "nome": "Produto 01",
                          "descricao": "Descrição 01",
                          "preco": 10.0,
                          "status": "ATIVO"
                        }"""))
                .andExpect(status().isCreated());
    }

    @Test
    void buscarTodos() throws Exception {
        this.mockMvc.perform(get(PRODUTOS)).andExpect(status().isOk());
    }

    @Test
    void alterar() throws Exception {
        this.mockMvc.perform(put(PRODUTOS + "/1")).andExpect(status().isOk());
    }

    @Test
    void ativarDesativarProduto() throws Exception {
        this.mockMvc.perform(patch(PRODUTOS + "/1")).andExpect(status().isOk());
    }
}