package br.com.jardielsousa.model.domain.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

}
