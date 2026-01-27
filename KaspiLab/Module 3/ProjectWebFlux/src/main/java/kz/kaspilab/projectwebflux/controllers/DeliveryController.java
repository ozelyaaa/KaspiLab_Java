package kz.kaspilab.projectwebflux.controllers;

import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import kz.kaspilab.projectwebflux.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public Mono<DeliveryDTO> createDelivery(@RequestBody DeliveryDTO dto) {
        System.out.println("Delivery received: " + dto);
        return deliveryService.createDelivery(dto)
                .doOnError(Throwable::printStackTrace);
    }

    @GetMapping("/product/{id}")
    public Mono<DeliveryDTO> getDeliveryByProductId(@PathVariable int id) {
        return deliveryService.getDeliveryByProductId(id);
    }
}
