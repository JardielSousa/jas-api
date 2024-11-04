package br.com.jardielsousa.config;

import br.com.jardielsousa.entity.ProdutoEntity;
import br.com.jardielsousa.model.domain.produto.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.nonNull;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        this.typeMapProdutoToProdutoEntity(modelMapper);

        return modelMapper;
    }

    private void typeMapProdutoToProdutoEntity(final ModelMapper modelMapper) {
        modelMapper.createTypeMap(Produto.class, ProdutoEntity.class)
                .addMappings(mapper -> mapper.when(ctx -> nonNull(ctx.getSource())).map(Produto::getStatus, ProdutoEntity::setStatus));
    }
}
