package kz.testandpractice.project5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProductDetails) {
        Product product = getProductById(id).orElseThrow(
                () -> new RuntimeException("Product to update is not found!"));
        product.setName(updatedProductDetails.getName());
        product.setPrice(updatedProductDetails.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id).orElseThrow(
                () -> new RuntimeException("Product to delete is not found!"));
        productRepository.delete(product);
    }
}
