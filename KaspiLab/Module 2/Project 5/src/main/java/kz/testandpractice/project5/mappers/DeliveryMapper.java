package kz.testandpractice.project5.mappers;

import kz.testandpractice.project5.entities.Delivery;
import kz.testandpractice.project5.models.DeliveryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryDTO toDto(Delivery delivery);
    Delivery toEntity(DeliveryDTO deliveryDTO);
    void updateEntityFromDto(DeliveryDTO deliveryDTO, @MappingTarget Delivery delivery);
}
