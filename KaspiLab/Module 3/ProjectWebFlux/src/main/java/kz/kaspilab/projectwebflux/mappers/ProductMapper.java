package kz.kaspilab.projectwebflux.mappers;

import kz.kaspilab.projectwebflux.domains.Product;
import kz.kaspilab.projectwebflux.models.ProductPostDTO;
import kz.kaspilab.projectwebflux.models.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {
    ProductResponseDTO toDto(Product product);
    Product toEntity(ProductPostDTO productPostDTO);
    void updateEntityFromDto(ProductPostDTO productPostDTO, @MappingTarget Product product);
}
