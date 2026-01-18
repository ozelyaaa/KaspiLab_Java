package kz.testandpractice.project5.mappers;

import kz.testandpractice.project5.entities.Product;
import kz.testandpractice.project5.models.ProductDTO;
import kz.testandpractice.project5.models.ProductPostRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDTO);
    void updateEntityFromDto(ProductDTO dto, @MappingTarget Product product);
}
