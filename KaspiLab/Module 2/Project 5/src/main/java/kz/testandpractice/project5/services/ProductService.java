package kz.testandpractice.project5.services;

import kz.testandpractice.project5.models.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductList();
    ProductDTO getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productDTO, String address);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

}
