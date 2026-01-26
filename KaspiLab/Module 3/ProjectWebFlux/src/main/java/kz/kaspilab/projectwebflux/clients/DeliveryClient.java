package kz.kaspilab.projectwebflux.clients;

import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DeliveryClient {

    private final WebClient webClient;

    public DeliveryClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080/deliveries").build();
    }

    public Mono<DeliveryDTO> getDeliveryByProductId(Integer productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(DeliveryDTO.class);
    }

    public Mono<DeliveryDTO> createDelivery(DeliveryDTO deliveryDTO) {
        return webClient.post()
                .bodyValue(deliveryDTO)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> Mono.empty())
                .bodyToMono(DeliveryDTO.class);
    }
}
