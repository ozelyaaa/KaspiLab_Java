package kz.kaspilab.projectwebflux.controllers;

import kz.kaspilab.projectwebflux.models.ProductPostDTO;
import kz.kaspilab.projectwebflux.models.ProductResponseDTO;
import kz.kaspilab.projectwebflux.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductResponseDTO> getProducts() {
        //System.out.println("getProducts() on thread: " + Thread.currentThread().getName());
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductResponseDTO> getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> addProduct(@RequestBody ProductPostDTO dto) {
        return productService.addProduct(dto)
                .map(product ->
                        ResponseEntity.created(
                                UriComponentsBuilder
                                        .fromPath("/{id}")
                                        .buildAndExpand(product.getId())
                                        .toUri()
                        ).build()
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductResponseDTO>> updateProduct(@PathVariable int id, @RequestBody ProductPostDTO dto) {
        return productService.updateProduct(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}
