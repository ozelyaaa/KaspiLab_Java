package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import reactor.core.publisher.Mono;

public interface DeliveryService {
    Mono<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO);
}
