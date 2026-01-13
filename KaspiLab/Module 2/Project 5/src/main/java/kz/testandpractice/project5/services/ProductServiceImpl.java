package kz.testandpractice.project5.services;

import kz.testandpractice.project5.clients.DeliveryClient;
import kz.testandpractice.project5.mappers.ProductMapper;
import kz.testandpractice.project5.models.DeliveryDTO;
import kz.testandpractice.project5.models.ProductDTO;
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
        return products.stream().map(productMapper::toDto).toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Product is not found!"));
        return productMapper.toDto(product);
    }

    public ProductDTO saveProduct(ProductDTO productDTO, String address) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        DeliveryDTO deliveryDTO = new DeliveryDTO(product.getId(), address);
        deliveryClient.createDelivery(deliveryDTO);

        return productMapper.toDto(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product to update is not found!"));

        productMapper.updateEntityFromDto(productDTO, product);
        return productMapper.toDto(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product to delete is not found!"));
        productRepository.delete(product);
    }
}
