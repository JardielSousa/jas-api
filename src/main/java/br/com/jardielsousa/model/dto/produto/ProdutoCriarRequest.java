package br.com.jardielsousa.model.dto.produto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class ProdutoCriarRequest {

    private String nome;

    private String descricao;

    private BigDecimal preco;

}