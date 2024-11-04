package br.com.jardielsousa.model.domain.produto;

import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Produto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private ProdutoStatus status;

}
