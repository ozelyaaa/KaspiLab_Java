package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.models.ProductPostDTO;
import kz.kaspilab.projectwebflux.models.ProductResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductResponseDTO> getProducts();
    Mono<ProductResponseDTO> getProductById(int id);
    Mono<ProductResponseDTO> addProduct(ProductPostDTO productPostDTO);
    Mono<ProductResponseDTO> updateProduct(int id, ProductPostDTO productPostDTO);
    Mono<Void> deleteProduct(Integer id);
}
