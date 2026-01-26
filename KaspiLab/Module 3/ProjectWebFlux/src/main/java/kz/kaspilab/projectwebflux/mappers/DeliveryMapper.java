package kz.kaspilab.projectwebflux.mappers;

import kz.kaspilab.projectwebflux.domains.Delivery;
import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryMapper {
    DeliveryDTO toDto(Delivery delivery);
    Delivery toEntity(DeliveryDTO deliveryDTO);
}
