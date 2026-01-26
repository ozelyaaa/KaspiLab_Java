package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.clients.DeliveryClient;
import kz.kaspilab.projectwebflux.domains.Product;
import kz.kaspilab.projectwebflux.exceptions.NotFoundException;
import kz.kaspilab.projectwebflux.mappers.ProductMapper;
import kz.kaspilab.projectwebflux.models.DeliveryDTO;
import kz.kaspilab.projectwebflux.models.ProductPostDTO;
import kz.kaspilab.projectwebflux.models.ProductResponseDTO;
import kz.kaspilab.projectwebflux.repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final DeliveryClient deliveryClient;

    @Override
    public Flux<ProductResponseDTO> getProducts() {
        return productRepo.findAll()
                .map(productMapper::toDto);
    }

    @Override
    public Mono<ProductResponseDTO> getProductById(int id) {
        return productRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + id)))
                .map(productMapper::toDto);
    }

    @Override
    public Mono<ProductResponseDTO> addProduct(ProductPostDTO productPostDTO) {
        Mono<Product> product = productRepo.
                save(productMapper.toEntity(productPostDTO));

        return product.flatMap(p -> {
            DeliveryDTO deliveryDTO = DeliveryDTO
                    .builder()
                    .product_id(p.getId())
                    .address(productPostDTO.getAddress())
                    .build();
            return deliveryClient
                    .createDelivery(deliveryDTO)
                    .thenReturn(p);
        }).map(productMapper::toDto);
    }

    @Override
    public Mono<ProductResponseDTO> updateProduct(int id, ProductPostDTO productPostDTO) {
        return productRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found with id: " + id)))
                .map(product -> {
                    productMapper.updateEntityFromDto(productPostDTO, product);
                    return product;
                })
                .flatMap(productRepo::save)
                .map(productMapper::toDto);
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
        return productRepo.findById(id)
                .flatMap(productRepo::delete);
    }
}
