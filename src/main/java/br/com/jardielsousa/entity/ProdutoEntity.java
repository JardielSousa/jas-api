package br.com.jardielsousa.entity;

import br.com.jardielsousa.model.enumeration.ProdutoStatus;
import br.com.jardielsousa.model.enumeration.ProdutoStatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public ProdutoEntity() {
        this.status = ProdutoStatus.ATIVO;
        this.dataCadastro = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
    }

}
