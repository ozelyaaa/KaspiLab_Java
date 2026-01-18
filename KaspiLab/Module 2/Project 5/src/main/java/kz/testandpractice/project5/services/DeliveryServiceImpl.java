package kz.testandpractice.project5.services;

import kz.testandpractice.project5.entities.Delivery;
import kz.testandpractice.project5.mappers.DeliveryMapper;
import kz.testandpractice.project5.models.DeliveryDTO;
import kz.testandpractice.project5.repos.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService{
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        System.out.println("Saved delivery: " + savedDelivery);
        return deliveryMapper.toDto(savedDelivery);
    }
}
