package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.domains.Delivery;
import kz.kaspilab.projectwebflux.mappers.DeliveryMapper;
import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import kz.kaspilab.projectwebflux.repos.DeliveryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepo deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public Mono<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        return deliveryRepository.save(delivery).map(deliveryMapper::toDto);
    }
}
