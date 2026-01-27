package kz.kaspilab.projectwebflux.clients;

import kz.kaspilab.projectwebflux.models.ProductResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8081/products").build();
    }

    public Mono<ProductResponseDTO> getProductById(Integer product_id) {
        return webClient.get()
                .uri("/{id}", product_id)
                .retrieve()
                .bodyToMono(ProductResponseDTO.class);
    }
}
