package br.com.alysonrodrigo.praticaapitask.mapper;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import br.com.alysonrodrigo.praticaapitask.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
