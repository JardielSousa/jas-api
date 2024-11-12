package br.com.jardielsousa.model.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProdutoAlterarRequest {

    private String nome;

    private String descricao;

    private BigDecimal preco;
}
