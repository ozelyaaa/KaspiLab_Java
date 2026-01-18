package kz.testandpractice.project5.services;

import kz.testandpractice.project5.clients.DeliveryClient;
import kz.testandpractice.project5.exceptions.NotFoundException;
import kz.testandpractice.project5.mappers.ProductMapper;
import kz.testandpractice.project5.models.DeliveryDTO;
import kz.testandpractice.project5.models.ProductDTO;
import kz.testandpractice.project5.models.ProductPostRequestDTO;
import kz.testandpractice.project5.repos.ProductRepository;
import kz.testandpractice.project5.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DeliveryClient deliveryClient;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, DeliveryClient deliveryClient, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.deliveryClient = deliveryClient;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getProductList() {
        List<Product> products = (List<Product>) productRepository.findAll();
        System.out.println(products);
        return products.stream().map(productMapper::toDto).toList();
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
