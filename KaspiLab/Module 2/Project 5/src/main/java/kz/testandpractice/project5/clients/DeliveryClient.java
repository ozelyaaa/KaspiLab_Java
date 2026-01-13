package kz.testandpractice.project5.clients;

import kz.testandpractice.project5.models.DeliveryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "delivery-service",
        url = "http://localhost:8080"
)
public interface DeliveryClient {
    @PostMapping("/deliveries")
    void createDelivery(@RequestBody DeliveryDTO deliveryDTO);
}
