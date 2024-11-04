package br.com.jardielsousa.entity;

import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import br.com.jardielsousa.model.enumeration.ProdutoStatusConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @Convert(converter = ProdutoStatusConverter.class)
    private ProdutoStatus status;

    public ProdutoEntity() {
        this.status = ProdutoStatus.ATIVO;
    }

}
