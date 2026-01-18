package kz.testandpractice.project5.services;

import kz.testandpractice.project5.clients.DeliveryClient;
import kz.testandpractice.project5.exceptions.NotFoundException;
import kz.testandpractice.project5.mappers.ProductMapper;
import kz.testandpractice.project5.models.DeliveryDTO;
import kz.testandpractice.project5.models.ProductDTO;
import kz.testandpractice.project5.models.ProductPostRequestDTO;
import kz.testandpractice.project5.repos.ProductRepository;
import kz.testandpractice.project5.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DeliveryClient deliveryClient;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, DeliveryClient deliveryClient, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.deliveryClient = deliveryClient;
        this.productMapper = productMapper;
    }

    @Async("taskExecutor")
    public CompletableFuture<List<ProductDTO>> getProductList() {
        //log.info("START getProductListAsync - thread: {}", Thread.currentThread().getName());

        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(productMapper::toDto)
                .toList();

//      For testing purposes
//        try {
//            Thread.sleep(3000); // 3 seconds
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
        //log.info("END getProductListAsync - thread: {}", Thread.currentThread().getName());

        return CompletableFuture.completedFuture(productDTOList);
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Product is not found!"));
        return productMapper.toDto(product);
    }

    public ProductDTO saveProduct(ProductPostRequestDTO productPostRequestDTO) {
        Product product = productMapper.toEntity(productPostRequestDTO.getProductDTO());
        Product savedProduct = productRepository.save(product);
        System.out.println("Saved product: " + savedProduct);

        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .productId(savedProduct.getId())
                .address(productPostRequestDTO.getAddress())
                .build();
        System.out.println("Saved delivery: " + deliveryDTO);
        deliveryClient.createDelivery(deliveryDTO);

        return productMapper.toDto(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product to update is not found!"));

        productMapper.updateEntityFromDto(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product to delete is not found!"));
        productRepository.delete(product);
    }
}
