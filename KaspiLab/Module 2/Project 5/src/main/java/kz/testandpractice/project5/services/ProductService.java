package kz.testandpractice.project5.services;

import kz.testandpractice.project5.models.ProductDTO;
import kz.testandpractice.project5.models.ProductPostRequestDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductService {
    CompletableFuture<List<ProductDTO>> getProductList();
    ProductDTO getProductById(Long id);
    ProductDTO saveProduct(ProductPostRequestDTO productPostRequestDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

}
