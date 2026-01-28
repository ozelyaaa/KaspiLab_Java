package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.domains.Delivery;
import kz.kaspilab.projectwebflux.enums.DeliveryStatus;
import kz.kaspilab.projectwebflux.mappers.DeliveryMapper;
import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import kz.kaspilab.projectwebflux.repos.DeliveryRepo;
import kz.kaspilab.projectwebflux.utils.KeyGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepo deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final RedisService redisService;

    @Override
    public Mono<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO) {
        String key = KeyGeneratorUtil.deliveryGenerateKey(deliveryDTO);

        return redisService
                .acquire(key, Duration.ofSeconds(2))
                .flatMap(acquired -> {
                    if (!acquired) {
                        return Mono.error(new IllegalStateException("Delivery already exists"));
                    }

                    Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
                    return deliveryRepository.save(delivery).map(deliveryMapper::toDto);
                });
    }

    @Override
    public Mono<DeliveryDTO> getDeliveryByProductId(Integer product_id) {
        return deliveryRepository
                .findByProductId(product_id)
                .map(deliveryMapper::toDto)
                .defaultIfEmpty(getDefaultDelivery(product_id));
    }

    private DeliveryDTO getDefaultDelivery(Integer product_id) {
        return DeliveryDTO.builder()
                .productId(product_id)
                .status(DeliveryStatus.NOT_CREATED)
                .build();
    }
}
