package br.com.jardielsousa.model.enumeration;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

@Getter
public enum ProdutoStatus {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo")
    ;

    private static final Map<Integer, ProdutoStatus> map = stream(values()).collect(toMap(ProdutoStatus::getCodigo, Function.identity()));

    private final Integer codigo;

    private final String descricao;

    ProdutoStatus(final Integer codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static ProdutoStatus porCodigo(final Integer codigo) {
        if (nonNull(codigo) && map.containsKey(codigo)) {
            return map.get(codigo);
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
