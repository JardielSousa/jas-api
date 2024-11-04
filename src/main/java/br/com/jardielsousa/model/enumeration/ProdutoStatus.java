package br.com.jardielsousa.model.enumeration;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Getter
public enum ProdutoStatus {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo")
    ;

    static {
        map = Arrays.stream(values()).collect(Collectors.toMap(ProdutoStatus::getCodigo, Function.identity()));
    }

    private static final Map<Integer, ProdutoStatus> map;

    private final Integer codigo;

    private final String descricao;

    ProdutoStatus(final Integer codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static ProdutoStatus porCodigo(final Integer codigo) {
        final var status = map.get(codigo);
        if (nonNull(status)) {
            return status;
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
